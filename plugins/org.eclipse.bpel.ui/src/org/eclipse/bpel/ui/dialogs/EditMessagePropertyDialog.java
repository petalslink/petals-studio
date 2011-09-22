/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.dialogs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.bpel.model.messageproperties.MessagepropertiesFactory;
import org.eclipse.bpel.model.messageproperties.Property;
import org.eclipse.bpel.model.messageproperties.PropertyAlias;
import org.eclipse.bpel.ui.BPELEditor;
import org.eclipse.bpel.ui.IHelpContextIds;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.commands.util.AutoUndoCommand;
import org.eclipse.bpel.ui.details.providers.ColumnTableProvider;
import org.eclipse.bpel.ui.details.providers.ModelLabelProvider;
import org.eclipse.bpel.ui.details.providers.PropertyAliasContentProvider;
import org.eclipse.bpel.ui.details.providers.PropertyAliasFilter;
import org.eclipse.bpel.ui.util.BrowseUtil;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.bpel.ui.util.WSDLImportHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.wst.wsdl.Definition;
import org.eclipse.wst.wsdl.Message;
import org.eclipse.wst.wsdl.internal.impl.DefinitionImpl;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * Dialog for creating or editing message properties.
 */
public class EditMessagePropertyDialog extends Dialog {

	// True if a new property is being created (as opposed to an existing being edited)
	protected boolean isNew;
	protected Property property;
	protected Object propertyType;
	protected IFile targetFile;
	protected URI propertyTypeFileURI;
	protected BPELEditor bpelEditor;

	protected List newAliasesList = new ArrayList();
	
	// widgets
	protected Text propertyNameText;
	protected Label typeNameText;
	protected Button browseTypeButton;
	protected Button newAliasButton;
	protected Button editAliasButton;
	protected Button removeAliasButton;
	protected Table aliasesTable;
	protected ColumnTableProvider aliasesTableProvider;
	protected TableViewer aliasesTableViewer;
	protected PropertyAliasFilter aliasesFilter;
	protected Set existingPropertyNames;
	protected TabbedPropertySheetWidgetFactory wf;

	/** inner classes **/

	public class MessageTypeColumn extends ColumnTableProvider.Column implements ILabelProvider {
		@Override
		public String getHeaderText() { return Messages.EditMessagePropertyDialog_1; } 
		@Override
		public String getProperty() { return "aliasMsgType"; } //$NON-NLS-1$
		@Override
		public int getInitialWeight() { return 30; }

		ModelLabelProvider labelProvider = new ModelLabelProvider();

		public String getText(Object element) {
			return labelProvider.getText(((PropertyAlias)element).getMessageType());
		}
	}

	public class MessagePartColumn extends ColumnTableProvider.Column implements ILabelProvider {
		@Override
		public String getHeaderText() { return Messages.EditMessagePropertyDialog_2; } 
		@Override
		public String getProperty() { return "aliasMsgPart"; } //$NON-NLS-1$
		@Override
		public int getInitialWeight() { return 70; }

		public String getText(Object element) {
			PropertyAlias alias = (PropertyAlias)element;
			String s = (alias.getPart()==null? Messages.EditMessagePropertyDialog_3:alias.getPart()); 
			if (alias.getQuery() != null) {
				String query = alias.getQuery().getValue();
				if (query != null && !"".equals(query)) { //$NON-NLS-1$
					s += NLS.bind(Messages.EditMessagePropertyDialog_4, (new Object[] { query })); 
				}
			}
			return s;
		}
	}

	
	public EditMessagePropertyDialog(Shell parentShell, Property property, String newPropertyName, BPELEditor bpelEditor, TabbedPropertySheetWidgetFactory wf) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		this.bpelEditor = bpelEditor;
		this.property = property;
		this.wf = wf;
		isNew = (property == null);
		if (isNew) {
			targetFile = bpelEditor.getEditModelClient().getArtifactsResourceInfo().getFile();
			this.property = MessagepropertiesFactory.eINSTANCE.createProperty();
			this.property.setName(newPropertyName);
		} else {
			Object type = property.getType();
			if (type instanceof XSDTypeDefinition) {
				this.propertyType = type;
			}
		}
	}

	/**
	 * @return Returns the property.
	 */
	public Property getProperty() {
		return property;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		GridLayout layout = (GridLayout) composite.getLayout();
		layout.makeColumnsEqualWidth = false;
		layout.numColumns = 3;

		Listener enablementListener = new Listener() {
			public void handleEvent(Event e) {
				updateEnablement();
			}
		};

		// create widgets
		Label topLabel = new Label(composite, SWT.NONE);
		if (isNew) {
			topLabel.setText(Messages.EditMessagePropertyDialog_9); 
		} else {
			topLabel.setText(Messages.EditMessagePropertyDialog_10); 
		}
		Label propertyNameLabel = new Label(composite, SWT.NONE);
		propertyNameLabel.setText(Messages.EditMessagePropertyDialog_14); 
		propertyNameText = new Text(composite, SWT.BORDER);
		if (property != null) {
			String s = property.getName();
			propertyNameText.setText((s == null)? "" : s); //$NON-NLS-1$
		}
		// TODO: if the property name changes we need to update the aliases as well
		propertyNameText.addListener(SWT.Modify, enablementListener);
		
		Label typeNameLabel = new Label(composite, SWT.NONE);
		typeNameLabel.setText(Messages.EditMessagePropertyDialog_Type_1); 
		typeNameText = new Label(composite, SWT.NONE);
		browseTypeButton = new Button(composite, SWT.PUSH);
		browseTypeButton.setText(Messages.EditMessagePropertyDialog_18); 
		browseTypeButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				browsePropertyType();
			}
		});
		
		Label separator = new Label(composite, SWT.SEPARATOR|SWT.HORIZONTAL|SWT.BOLD);
		Label aliasesLabel = new Label(composite, SWT.NONE);
		aliasesLabel.setText(Messages.EditMessagePropertyDialog_20); 
		Composite c2 = new Composite(composite, SWT.NONE);
		newAliasButton = new Button(c2, SWT.PUSH);
		newAliasButton.setText(Messages.EditMessagePropertyDialog_21); 
		newAliasButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				createAlias();
			}
		});
		editAliasButton = new Button(c2, SWT.PUSH);
		editAliasButton.setText(Messages.EditMessagePropertyDialog_22); 
		editAliasButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				IStructuredSelection sel = (IStructuredSelection)aliasesTableViewer.getSelection();
				PropertyAlias alias = (PropertyAlias) sel.getFirstElement();
				editAlias(alias);
			}
		});
		removeAliasButton = new Button(c2, SWT.PUSH);
		removeAliasButton.setText(Messages.EditMessagePropertyDialog_23); 
		removeAliasButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				IStructuredSelection sel = (IStructuredSelection)aliasesTableViewer.getSelection();
				PropertyAlias alias = (PropertyAlias) sel.getFirstElement();
				Definition definition = property.getEnclosingDefinition();
				if (definition != null) {
					definition.getEExtensibilityElements().remove(alias);
				} else {
					newAliasesList.remove(alias);
				}
				updatePropertyAliasTable();
			}
		});
		
		aliasesTable = new Table(composite, SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.BORDER);
		aliasesTable.setLinesVisible(true);
		aliasesTable.setHeaderVisible(true);
		aliasesTableProvider = new ColumnTableProvider();
		aliasesTableProvider.add(new MessageTypeColumn());
		aliasesTableProvider.add(new MessagePartColumn());
		aliasesTableViewer = new TableViewer(aliasesTable);
		aliasesTableProvider.createTableLayout(aliasesTable);
		aliasesTableViewer.setLabelProvider(aliasesTableProvider);
		// Content provider that combines aliases from the actual model and newAliasesList.
		aliasesTableViewer.setContentProvider(new PropertyAliasContentProvider() {
			@Override
			public Object[] getElements(Object input) {
				Object[] superResult = super.getElements(input);
				Object[] result = new Object[superResult.length + newAliasesList.size()];
				System.arraycopy(superResult, 0, result, 0, superResult.length);
				int i = superResult.length;
				for (Iterator it = newAliasesList.iterator(); it.hasNext(); ) {
					result[i++] = it.next();
				}
				return result;
			}
		});
		aliasesTableViewer.setColumnProperties(aliasesTableProvider.getColumnProperties());
		aliasesFilter = new PropertyAliasFilter();
		aliasesTableViewer.addFilter(aliasesFilter);
		aliasesTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateEnablement();
			}
		});
		
		// update type widgets
		updateTypeWidgets();
		updatePropertyAliasTable();
		updateTypeFileText();

		// layout widgets
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
		topLabel.setLayoutData(data);

		data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		propertyNameLabel.setLayoutData(data);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		propertyNameText.setLayoutData(data);

		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		separator.setLayoutData(data);

		
		data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		typeNameLabel.setLayoutData(data);
		data = new GridData(GridData.FILL_HORIZONTAL);
		typeNameText.setLayoutData(data);
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		browseTypeButton.setLayoutData(data);
		
		data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		aliasesLabel.setLayoutData(data);
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		data.horizontalSpan = 2;
		c2.setLayoutData(data);
		layout = new GridLayout(3, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		c2.setLayout(layout);
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		newAliasButton.setLayoutData(data);
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		editAliasButton.setLayoutData(data);
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		removeAliasButton.setLayoutData(data);
		
		data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 3;
		data.verticalSpan = 3;
		data.heightHint = 70;
		aliasesTable.setLayoutData(data);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(
			parent, IHelpContextIds.PROPERTY_DIALOG);	
		
		return composite;
	}

	protected void updatePropertyAliasTable() {
		aliasesFilter.setProperty(property);
		//aliasesTableViewer.setInput(definition);
		aliasesTableViewer.setInput(bpelEditor.getProcess());
	}

	protected void updateTypeFileText() {
		if (property != null || propertyType != null) {
			Object type = this.propertyType;
			if (type == null) {
				type = property.getType();
			}
			if (type instanceof XSDTypeDefinition) {
				XSDTypeDefinition typeDefinition = (XSDTypeDefinition) type;
				typeNameText.setText(typeDefinition.getName());
			} else if (type instanceof XSDElementDeclaration) {
				XSDElementDeclaration element = (XSDElementDeclaration) type;
				typeNameText.setText(element.getName());
			}
		}
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (isNew) {
			newShell.setText(Messages.EditMessagePropertyDialog_26); 
		} else {
			newShell.setText(Messages.EditMessagePropertyDialog_27); 
		}
	}

	@Override
	protected Control createContents(Composite parent) {
		Control result = super.createContents(parent);
		updateEnablement();
		return result;
	}

	protected void updateEnablement() {
		// update the OK button
		boolean isOK = true;
		if ((targetFile == null && property.eResource() == null)
			|| !isPropertyNameValid()
			|| propertyType == null) {
				isOK = false;
		}
		Button okButton = getButton(IDialogConstants.OK_ID);
		if (okButton != null) {
			okButton.setEnabled(isOK);
		}
		newAliasButton.setEnabled(isOK);
		isOK &= !aliasesTableViewer.getSelection().isEmpty();
		editAliasButton.setEnabled(isOK);
		removeAliasButton.setEnabled(isOK);
	}

	protected boolean isPropertyNameValid() {
		String name = propertyNameText.getText();
		if (name == null) return false;
		if ("".equals(name)) return false; //$NON-NLS-1$
		if (propertyNameExists(name)) return false;
		return true;
	}
	
	protected boolean propertyNameExists(String name) {
		if (!isNew && name.equals(property.getName())) return false;
		return getExistingPropertyNames().contains(name);
	}
	
	protected Set getExistingPropertyNames() {
		if (existingPropertyNames == null) {
			Set properties = ModelHelper.getAvailableProperties(bpelEditor.getProcess());
			existingPropertyNames = new HashSet();
			for (Iterator iter = properties.iterator(); iter.hasNext();) {
				Property prop = (Property) iter.next();
				existingPropertyNames.add(prop.getName());
			}
		}
		return existingPropertyNames;
	}
	
	protected void updateTypeWidgets() {
		// update type from file
		Object type = (property == null) ? propertyType : property.getType();
		if (type instanceof XSDTypeDefinition) {
			updateTypeFileText();
		}
	}

	/**
	 * Opens a dialog and let the user browse for an XSD type or element.
	 * Updates the property type according to the user choice.
	 */
	protected void browsePropertyType() {
		Object xsdType = BrowseUtil.browseForXSDTypeOrElement(bpelEditor.getProcess(), getShell());
		if (xsdType != null) {
			propertyType = xsdType;
			updateTypeFileText();
	    	updateEnablement();
	    }
	}

	protected URI getTargetFileURI() {
		if (targetFile != null) {
			return URI.createPlatformResourceURI(targetFile.getFullPath().toString());
		}
		return property.eResource().getURI();
	}
	
	@Override
	protected void okPressed() {
		createProperty();
		super.okPressed();
	}

	/**
	 * Creates the necessary property (in the Resource in memory only).
	 */
	protected void createProperty() {
		URI uri = getTargetFileURI();
		Resource resource = bpelEditor.getResourceSet().getResource(uri, true);
		final Definition definition = (Definition) resource.getContents().get(0);

		bpelEditor.getCommandFramework().execute(new AutoUndoCommand(definition) {
			@Override
			public void doExecute() {
				if (isNew) {
					definition.getEExtensibilityElements().add(property);
					property.setEnclosingDefinition(definition);
					// add any aliases we're creating too.
					for (Iterator it = newAliasesList.iterator(); it.hasNext(); ) {
						PropertyAlias alias = (PropertyAlias)it.next(); 
						definition.getEExtensibilityElements().add(alias);
						alias.setEnclosingDefinition(definition);
					}
					newAliasesList.clear();
				}
				property.setName(propertyNameText.getText());
				property.setType(propertyType);
				
				WSDLImportHelper.addAllImportsAndNamespaces(definition,
					bpelEditor.getEditModelClient().getPrimaryResourceInfo().getFile());
				// This property must be a simple type. Make sure the namespace is already in the wsdl file.
				if (definition.getPrefix(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001) == null) {
					// TODO: what if it already had this prefix??
					definition.addNamespace("xs", XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001); //$NON-NLS-1$
				}
			}
		});
	}

	/**
	 * Opens a dialog and let the user create a new property alias.
	 */
	protected void createAlias() {
		EditPropertyAliasDialog dialog = new EditPropertyAliasDialog(getShell(), property, null, bpelEditor, wf);
		if (dialog.open() == Window.OK) {
			final PropertyAlias alias = dialog.getPropertyAlias();
			if (alias != null) {
				URI uri = getTargetFileURI();
				Resource resource = bpelEditor.getResourceSet().getResource(uri, true);
				final Definition definition = (Definition) resource.getContents().get(0);
				bpelEditor.getCommandFramework().execute(new AutoUndoCommand(definition) {
					@Override
					public void doExecute() {
						if (isNew) {
							// save the alias and add them later
							newAliasesList.add(alias);
						} else {
							// add the alias now
							alias.setEnclosingDefinition(definition);
							definition.getEExtensibilityElements().add(alias);
						}

						Object messageTypeObject = alias.getMessageType();
						if (messageTypeObject instanceof Message) {
							WSDLImportHelper.addImportAndNamespace(definition, ((Message)messageTypeObject).getEnclosingDefinition());
						}
						// This doesn't seem to introduce an updateElement automatically,
						// so do it manually now, so that RolePortTypes (for example) who
						// are affected by the new namespace will know about it.
						((DefinitionImpl)definition).updateElement();
					}
				});
				
				updatePropertyAliasTable();
			}
		}
	}

	/**
	 * Opens a dialog and let the user edit an existing property alias.
	 */
	protected void editAlias(PropertyAlias alias) {
		if (alias != null) {
			EditPropertyAliasDialog dialog = new EditPropertyAliasDialog(getShell(), property, alias, bpelEditor, wf);
			if (dialog.open() == Window.OK) {
				updatePropertyAliasTable();
			}
		}
	}
}
