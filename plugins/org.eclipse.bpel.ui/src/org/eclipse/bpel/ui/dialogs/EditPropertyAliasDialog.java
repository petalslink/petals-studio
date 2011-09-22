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

import java.util.Iterator;

import org.eclipse.bpel.model.messageproperties.MessagepropertiesFactory;
import org.eclipse.bpel.model.messageproperties.Property;
import org.eclipse.bpel.model.messageproperties.PropertyAlias;
import org.eclipse.bpel.model.messageproperties.Query;
import org.eclipse.bpel.ui.BPELEditor;
import org.eclipse.bpel.ui.IHelpContextIds;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.details.providers.ModelTreeContentProvider;
import org.eclipse.bpel.ui.details.providers.ModelTreeLabelProvider;
import org.eclipse.bpel.ui.details.providers.PartTreeContentProvider;
import org.eclipse.bpel.ui.details.tree.ITreeNode;
import org.eclipse.bpel.ui.details.tree.PartTreeNode;
import org.eclipse.bpel.ui.properties.DialogVariableTypeSelector;
import org.eclipse.bpel.ui.properties.VariableTypeSelector;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.wst.wsdl.Definition;
import org.eclipse.wst.wsdl.Message;
import org.eclipse.wst.wsdl.Part;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDTypeDefinition;


/**
 * Dialog for creating or editing a property alias.
 */
public class EditPropertyAliasDialog extends Dialog {

	protected BPELEditor bpelEditor;
	protected Definition wsdlDefinition;
	protected Property property;
	protected PropertyAlias alias;

	protected DialogVariableTypeSelector variableTypeSelector;
	protected Tree messagePartTree;
	protected TreeViewer messagePartViewer;
	protected boolean createAlias;

	protected TabbedPropertySheetWidgetFactory wf;

	protected class PropertyAliasDialogCallback implements VariableTypeSelector.Callback {
		public void selectRadioButton(int index) {}
		public void selectXSDType(XSDTypeDefinition xsdType) {}
		public void selectXSDElement(XSDElementDeclaration xsdElement) {}
		public void selectMessageType(Message message) {
			EditPropertyAliasDialog.this.variableTypeSelector.setVariableType(message);
			EditPropertyAliasDialog.this.messagePartViewer.setInput(message);
			updateWidgets();
		}
	}

	public EditPropertyAliasDialog(Shell parentShell, Property property, PropertyAlias alias, BPELEditor bpelEditor, TabbedPropertySheetWidgetFactory wf) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		this.createAlias = (alias == null);
		this.alias = alias;
		this.property = property;
		this.bpelEditor = bpelEditor;
		this.wf = wf;
		if (alias != null && alias.getMessageType() != null) {
			Resource resource = ((EObject)alias.getMessageType()).eResource();
			this.wsdlDefinition = (Definition) resource.getContents().get(0);
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		GridLayout layout = (GridLayout) composite.getLayout();
		layout.makeColumnsEqualWidth = false;
		layout.numColumns = 2;

		// create widgets
		Label topLabel = new Label(composite, SWT.NONE);
		if (this.createAlias) {
			topLabel.setText(Messages.EditPropertyAliasDialog_3);
		} else {
			topLabel.setText(Messages.EditPropertyAliasDialog_4);
		}
		this.variableTypeSelector = new DialogVariableTypeSelector(composite, SWT.NONE, this.bpelEditor, getShell(), new PropertyAliasDialogCallback(), this.wf);
		this.variableTypeSelector.setVariableType(null);
		Label partLabel = new Label(composite, SWT.NONE);
		partLabel.setText(Messages.EditPropertyAliasDialog_8);
		this.messagePartTree = new Tree(composite, SWT.BORDER);
		this.messagePartViewer = new TreeViewer(this.messagePartTree);
		this.messagePartViewer.setContentProvider(new PartTreeContentProvider(true));
		this.messagePartViewer.setLabelProvider(new ModelTreeLabelProvider());
		this.messagePartViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateEnablement();
			}
		});

		// layout widgets
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
		topLabel.setLayoutData(data);

		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		this.variableTypeSelector.setLayoutData(data);

		data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		partLabel.setLayoutData(data);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.verticalSpan = 3;
		data.heightHint = 70;
		this.messagePartTree.setLayoutData(data);

		updateWidgets();

		PlatformUI.getWorkbench().getHelpSystem().setHelp(
				parent, IHelpContextIds.PROPERTY_ALIAS_DIALOG);

		return composite;
	}

	protected void updateWidgets() {
		if (this.wsdlDefinition != null) {
			Message message = (Message)this.alias.getMessageType();
			this.messagePartViewer.setInput(message);
			this.variableTypeSelector.setVariableType(message);
			String partName = this.alias.getPart();
			if (partName != null) {
				Part part = null;
				for (Iterator<?> iter = message.getEParts().iterator(); iter.hasNext();) {
					Part temp = (Part) iter.next();
					if (partName.equals(temp.getName())) {
						part = temp;
						break;
					}
				}
				if (part != null) {
					PartTreeNode node = new PartTreeNode(part, false);
					this.messagePartViewer.setSelection(new StructuredSelection(node), true);
				}
			}
		} else {
			//variableTypeSelector.setVariableType(null);
		}
		updateEnablement();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (this.createAlias) {
			newShell.setText(Messages.EditPropertyAliasDialog_9);
		} else {
			newShell.setText(Messages.EditPropertyAliasDialog_10);
		}
	}

	/**
	 * @return Returns the alias.
	 */
	public PropertyAlias getPropertyAlias() {
		return this.alias;
	}

	@Override
	protected void okPressed() {
		if (this.alias == null) {
			this.alias = MessagepropertiesFactory.eINSTANCE.createPropertyAlias();
		}
		Message message = (Message)this.variableTypeSelector.getVariableType();

		ModelTreeContentProvider tcp = (ModelTreeContentProvider)this.messagePartViewer.getContentProvider();
		String part = null;
		String query = ""; //$NON-NLS-1$
		ITreeNode result = (ITreeNode)((IStructuredSelection) this.messagePartViewer.getSelection()).getFirstElement();
		while (result != null) {
			if (result instanceof PartTreeNode) {
				part = result.getLabel();
				break;
			}
			if (!query.equals("")) { //$NON-NLS-1$
				query = result.getLabel() + "/" + query; //$NON-NLS-1$
			} else {
				query = result.getLabel();
			}
			result = (ITreeNode)tcp.getParent(result);
		}
		if (query.length() > 0)
			query = "/" + query; //$NON-NLS-1$
		else
			query = null;

		this.alias.setPropertyName(this.property);
		this.alias.setMessageType(message);
		this.alias.setPart(part);
		Query queryObject = this.alias.getQuery();
		if (queryObject == null) {
			queryObject = MessagepropertiesFactory.eINSTANCE.createQuery();
		}
		queryObject.setValue(query);
		this.alias.setQuery(queryObject);
		super.okPressed();
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
		if (this.variableTypeSelector.getVariableType() == null
				|| (this.messagePartViewer.getSelection() == null) || (this.messagePartViewer.getSelection().isEmpty())) {
			isOK = false;
		}
		Button okButton = getButton(IDialogConstants.OK_ID);
		if (okButton != null) {
			okButton.setEnabled(isOK);
		}
	}
}