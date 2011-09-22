/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.dialogs;

import java.util.Collections;
import java.util.List;

import org.eclipse.bpel.model.util.BPELUtils;
import org.eclipse.bpel.ui.BPELUIPlugin;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.commands.AddImportCommand;
import org.eclipse.bpel.ui.details.providers.ModelTreeLabelProvider;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;

/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Jul 18, 2006
 *
 */
public class BrowseSelectorDialog extends ListAndViewDialog {

	protected static final int BID_ADD_IMPORT = IDialogConstants.CLIENT_ID + 1;
	protected static final int BID_FROM_IMPORTS_ONLY = IDialogConstants.CLIENT_ID + 7;
	protected static final int BID_FROM_PROJECT = IDialogConstants.CLIENT_ID + 8;
	protected static final int BID_FROM_WORKSPACE = IDialogConstants.CLIENT_ID + 9;
	protected static final int BID_FROM_XML_CATALOG = IDialogConstants.CLIENT_ID + 10;
	protected static final int BID_FROM_REPOSITORY = IDialogConstants.CLIENT_ID + 11;
	protected static final int BID_DUPLICATES = IDialogConstants.CLIENT_ID + 20;

	protected boolean showDuplicates = false;
	protected EObject modelObject;
	protected Text filterText;

	protected IStructuredContentProvider contentProvider = null;
	protected IStructuredContentProvider resourceContentProvider = null;
	protected ITreeContentProvider treeContentProvider = null;

	protected Tree fTree;
	protected TreeViewer fTreeViewer;

	private Button fFromImportsRadio;
	private Button fFromProjectRadio;
	private Button fFromWorkspaceRadio;

	private Object[] fWorkspaceObjects;
	private Object[] fProjectObjects;

	private Label filterLabel;

	/* View types dialog preference setting */
	static final String VIEW_FROM_KEY = "ViewFrom"; //$NON-NLS-1$


	/* Show duplicates key in dialog settings */
	static final String SHOW_DUPLICATES_KEY = "ShowDuplicates"; //$NON-NLS-1$


	/* by default, view types from imports only */
	protected int VIEW_FROM = BID_FROM_IMPORTS_ONLY;


	/** The browse from label */
	String fBrowseFromLabel;



	/**
	 * @param parent
	 * @param elementRenderer
	 */
	public BrowseSelectorDialog(Shell parent, ILabelProvider elementRenderer) {
		super(parent, elementRenderer);

		//	Restore some dialog settings ...
		IDialogSettings settings = getDialogSettings();

		setMessage(Messages.BrowseSelectorDialog_0);
		setUpperListLabel(Messages.BrowseSelectorDialog_1);
		setBrowseFromLabel (Messages.BrowseSelectorDialog_2);

		try {
			this.VIEW_FROM = settings.getInt(VIEW_FROM_KEY);
		} catch (Exception ex) {
			this.VIEW_FROM = BID_FROM_IMPORTS_ONLY;
		}

		try {
			this.showDuplicates = settings.getBoolean(SHOW_DUPLICATES_KEY);
		} catch (Exception ex) {
			this.showDuplicates = false;
		}
	}


	/**
	 * @param string
	 */
	protected void setBrowseFromLabel(String label) {
		this.fBrowseFromLabel = label;
	}



	@Override
	protected void saveSettings() {

		IDialogSettings settings = getDialogSettings();

		settings.put ( VIEW_FROM_KEY, this.VIEW_FROM );
		settings.put ( SHOW_DUPLICATES_KEY, this.showDuplicates );
	}


	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == BID_ADD_IMPORT) {
			handleAddImport();
			return ;
		}
		super.buttonPressed(buttonId);
	}

	/**
	 * Check to make sure that the mappings for the XML namespace exist
	 */
	@Override
	protected void okPressed() {
		computeResult();
		Object obj[] = getResult();

		if (obj != null && obj.length > 0) {
			if (ensureXSDTypeNamespaceMappings ( obj[0] ) == false) {
				return ;
			}

			handleAddImport ( obj[0] );

			// only if we have a mapping do we dismiss the dialog
			super.okPressed();
		}

	}

	/**
	 * Ensure that the prefix mapping exists for the given namespace
	 * in the BPEL source.
	 * 
	 * @param the schema component for which to create a BPEL prefix mappings.
	 */
	private boolean ensureXSDTypeNamespaceMappings(Object obj) {

		String targetNamespace = null;

		if (obj instanceof XSDNamedComponent ) {
			XSDNamedComponent namedComponent = (XSDNamedComponent) obj;
			targetNamespace = namedComponent.getTargetNamespace();
		}

		if (targetNamespace == null) {
			return true;
		}

		// Now check if the target namespace has a prefix mappings.
		String prefix = BPELUtils.getNamespacePrefix (this.modelObject, targetNamespace);
		if (prefix != null) {
			return true;
		}

		// We have to map the namespace to a prefix.
		NamespaceMappingDialog dialog = new NamespaceMappingDialog(getShell(),this.modelObject);
		dialog.setNamespace( targetNamespace );

		if (dialog.open() == Window.CANCEL) {
			return false;
		}

		// define the prefix
		BPELUtils.setPrefix( ModelHelper.getProcess(this.modelObject), targetNamespace, dialog.getPrefix());

		return true;
	}

	/**
	 * Handle the check button and radio button callbacks.
	 * 
	 * @param id
	 * @param checked
	 * @param refresh unless this is set, no refresh is done.
	 */

	protected void buttonPressed(int id, boolean checked, boolean bRefresh) {

		switch (id) {

		case BID_DUPLICATES :
			this.showDuplicates = checked;
			break;


		case BID_FROM_WORKSPACE :
		case BID_FROM_XML_CATALOG :
		case BID_FROM_PROJECT :
		case BID_FROM_REPOSITORY :
		case BID_FROM_IMPORTS_ONLY :
			if (!checked) {
				return ;
			}
			if (checked) {
				this.VIEW_FROM = id;
			}
			break;

		default :
			break;
		}


		if (bRefresh) {
			refresh();
		}
	}

	/**
	 * Add an import using an explicit import dialog selection.
	 * We safeguard against adding duplicate types to the BPEL model here as well.
	 */
	protected void handleAddImport() {

		SchemaImportDialog dialog = new SchemaImportDialog(getShell(),this.modelObject);
		if( dialog.open() == Window.OK ) {
			Object obj = dialog.getFirstResult();
			if( obj != null && handleAddImport ( obj )) {
				showImportedTypes();
				refresh();
			}
		}
	}


	protected boolean handleAddImport(Object obj) {

		// Do not import schema for schemas
		if (obj instanceof XSDSimpleTypeDefinition) {
			if (((XSDSimpleTypeDefinition) obj).getTargetNamespace().equals(
						XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001)) {
				return false;
			}
		}

		AddImportCommand cmd = new AddImportCommand(ModelHelper
					.getProcess(this.modelObject), obj);

		if (cmd.canDoExecute() && cmd.wouldCreateDuplicateImport() == false) {
			ModelHelper.getBPELEditor(this.modelObject).getCommandStack().execute(
						cmd);
			// Now refresh the view to imported types.
			return true;
		}
		return false;
	}


	protected Button createRadioButton(Composite parent, String label, int id, boolean checked) {

		Button button = new Button(parent,SWT.RADIO);
		button.setText(label);
		button.setFont(JFaceResources.getDialogFont());
		button.setData(Integer.valueOf(id));
		button.setSelection( checked );

		button.addSelectionListener (new SelectionAdapter() {
			@Override
			public void widgetSelected (SelectionEvent event) {
				Button b = (Button) event.widget;
				int val = ((Integer) b.getData()).intValue();

				buttonPressed(val, b.getSelection(), true );
			}
		});

		return button;

	}

	protected Button createCheckButton(Composite parent, String label, int id, boolean checked) {

		Button button = new Button(parent,SWT.CHECK);
		button.setText(label);
		button.setFont(JFaceResources.getDialogFont());
		button.setData(Integer.valueOf(id));
		button.setSelection( checked );

		button.addSelectionListener (new SelectionAdapter() {
			@Override
			public void widgetSelected (SelectionEvent event) {
				Button b = (Button) event.widget;
				int val = ((Integer) b.getData()).intValue();

				buttonPressed(val, b.getSelection(), true);
			}
		});

		return button;

	}

	@Override
	protected Text createFilterText(Composite parent) {
		this.filterText = super.createFilterText(parent);

		createBrowseFromGroup ( parent );
		createBrowseFilterGroup( parent );

		return this.filterText;
	}


	@Override
	protected Label createMessageArea(Composite composite) {
		this.filterLabel = super.createMessageArea(composite);
		return this.filterLabel;
	}



	protected void createBrowseFromGroup(Composite parent) {
		Group group = new Group(parent,SWT.SHADOW_ETCHED_IN);
		group.setText( this.fBrowseFromLabel );

		GridLayout layout = new GridLayout();
		layout.makeColumnsEqualWidth = true;
		layout.numColumns = 3;
		group.setLayout(layout);
		GridData data = new GridData();
		data.grabExcessVerticalSpace = false;
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		group.setLayoutData(data);

		createBrowseFromGroupButtons ( group );

	}



	protected void createBrowseFromGroupButtons ( Group group ) {

		this.fFromImportsRadio = createRadioButton(group,Messages.TypeSelectorDialog_10, BID_FROM_IMPORTS_ONLY,
					this.VIEW_FROM ==  BID_FROM_IMPORTS_ONLY);
		this.fFromProjectRadio = createRadioButton(group,Messages.TypeSelectorDialog_11, BID_FROM_PROJECT,
					this.VIEW_FROM == BID_FROM_PROJECT );
		this.fFromWorkspaceRadio = createRadioButton(group,Messages.TypeSelectorDialog_12, BID_FROM_WORKSPACE,
					this.VIEW_FROM == BID_FROM_WORKSPACE );


		//		fFromCatalogRadio = createRadioButton(group,"From XML Catalog", BID_FROM_XML_CATALOG,
		//				VIEW_TYPES == BID_FROM_XML_CATALOG );
		//		// TODO: Fix this or remove at some point
		//		fFromCatalogRadio.setVisible(false);
		//
		//		fFromRepositoryRadio = createRadioButton(group,"From Repository", BID_FROM_REPOSITORY,
		//				VIEW_TYPES == BID_FROM_REPOSITORY );
		//		// TODO: Fix this or remove at some point.
		//		fFromRepositoryRadio.setVisible(false);


	}

	protected void createBrowseFilterGroup(Composite parent) {
		Group group = new Group(parent,SWT.SHADOW_ETCHED_IN);
		group.setText( Messages.BrowseSelectorDialog_4 );

		GridLayout layout = new GridLayout();
		layout.makeColumnsEqualWidth = true;
		layout.numColumns = 3;
		group.setLayout(layout);
		GridData data = new GridData();
		data.grabExcessVerticalSpace = false;
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		group.setLayoutData(data);

		createBrowseFilterGroupButtons ( group );
	}



	protected void createBrowseFilterGroupButtons ( Group group ) {
		createCheckButton(group,Messages.TypeSelectorDialog_19, BID_DUPLICATES,
					this.showDuplicates);

	}


	@Override
	protected Object createLowerView(Composite parent) {
		//	Tree viewer for variable structure ...
		this.fTree = new Tree(parent, SWT.BORDER );

		this.fTreeViewer = new TreeViewer(this.fTree);
		this.fTreeViewer.setContentProvider( this.treeContentProvider );
		this.fTreeViewer.setLabelProvider(new ModelTreeLabelProvider());
		this.fTreeViewer.setInput ( null );
		this.fTreeViewer.setAutoExpandLevel( getAutoExpandLevel() );
		// end tree viewer for variable structure
		GridData data = new GridData();
		data.grabExcessVerticalSpace = true;
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		data.minimumHeight = 200;
		this.fTree.setLayoutData(data);

		return this.fTree;
	}


	protected int getAutoExpandLevel () {
		return 3;
	}


	@Override
	protected void updateLowerViewWidget(Object[] elements) {

		if (elements == null || elements.length < 1) {
			this.fTreeViewer.setInput(null);
			return;
		}

		this.fTreeViewer.setInput ( elements[0] );

		ScrollBar bar = this.fTree.getVerticalBar();
		if (bar != null) {
			bar.setSelection(0);
		}
	}


	protected Object[] merge(Object[] a1, Object a2[]) {
		Object[] result = new Object[ a1.length + a2.length];
		System.arraycopy(a1, 0, result, 0, a1.length);
		System.arraycopy(a2, 0, result, a1.length, a2.length);
		return result;
	}


	protected void refresh() {

		if (this.VIEW_FROM == BID_FROM_IMPORTS_ONLY) {
			refreshFromImports ();
		} else if (this.VIEW_FROM == BID_FROM_PROJECT) {
			refreshFromProject();
		} else if (this.VIEW_FROM == BID_FROM_WORKSPACE) {
			refreshFromWorkspace();
		} else if (this.VIEW_FROM == BID_FROM_XML_CATALOG) {
			refreshFromXMLCatalog ();
		} else if (this.VIEW_FROM == BID_FROM_REPOSITORY ) {
			refreshFromRepository ();
		} else {
			return ;
		}

		if (this.fFilteredList != null) {
			if (this.fFilteredList.isEmpty()) {
				handleEmptyList();
			} else {
				handleNonEmptyList();
			}
		}
	}

	/**
	 * Refresh the available types from the BPEL imports list. This is the
	 * list of types/elements visible in the process so far (the working set so to speak).
	 */
	protected void refreshFromImports() {

		List<?> elements = collectItemsFromImports();

		if (this.fFilteredList != null) {
			this.fFilteredList.setAllowDuplicates(this.showDuplicates);
			this.fFilteredList.setElements( this.contentProvider.getElements(elements) );
			this.fFilteredList.setEnabled(true);
		}
	}


	protected List<?> collectItemsFromImports () {
		return Collections.emptyList();
	}

	/**
	 * Show the types which are available in the current workspace.
	 * This includes all the open projects ...
	 */
	protected void refreshFromWorkspace() {

		if (this.fTreeViewer != null) {
			this.fTreeViewer.setInput(null);
		}

		if (this.fWorkspaceObjects == null ) {
			this.fWorkspaceObjects = this.resourceContentProvider.getElements( ResourcesPlugin.getWorkspace().getRoot() );
		}

		if (this.fFilteredList != null) {

			this.fFilteredList.setEnabled(true);
			this.fFilteredList.setAllowDuplicates(this.showDuplicates);
			this.fFilteredList.setElements(this.contentProvider.getElements( this.fWorkspaceObjects ));
		}
	}

	/**
	 * Show the types which are available in the current project only.
	 * 
	 */
	protected void refreshFromProject() {

		if (this.fTreeViewer != null) {
			this.fTreeViewer.setInput(null);
		}

		if (this.fProjectObjects == null ) {
			Resource resource = this.modelObject.eResource();
			IFile file = BPELUtil.getFileFromURI(resource.getURI());
			this.fProjectObjects = this.resourceContentProvider.getElements( file.getProject() );
		}

		if (this.fFilteredList != null) {

			this.fFilteredList.setEnabled(true);
			this.fFilteredList.setAllowDuplicates(this.showDuplicates);
			this.fFilteredList.setElements( this.contentProvider.getElements( this.fProjectObjects ) );
		}
	}

	protected void refreshFromXMLCatalog() {

		// TODO: At some point I had the idea to gather types from
		// XML catalog. But not sure if this is such a good idea anymore

		Object[] elements = new Object[]{};
		this.modelObject.eContainer();

		if (this.fFilteredList != null) {

			this.fFilteredList.setAllowDuplicates(this.showDuplicates);
			this.fFilteredList.setElements(elements);
			this.fFilteredList.setEnabled(true);

		}

		if (this.fTreeViewer != null) {
			this.fTreeViewer.setInput(null);
		}
	}

	protected void refreshFromRepository() {

		// TODO: Some repository of some kind ?

		Object[] elements = new Object[]{};
		this.modelObject.eContainer();

		if (this.fFilteredList != null) {

			this.fFilteredList.setAllowDuplicates(this.showDuplicates);
			this.fFilteredList.setElements(elements);
			this.fFilteredList.setEnabled(true);

		}

		if (this.fTreeViewer != null) {
			this.fTreeViewer.setInput(null);
		}
	}

	protected void showImportedTypes() {

		this.fFromImportsRadio.setSelection(true);
		this.fFromProjectRadio.setSelection(false);
		this.fFromWorkspaceRadio.setSelection(false);

		//	fFromRepositoryRadio.setSelection(false);
		//	fFromCatalogRadio.setSelection(false);

		this.fFromImportsRadio.forceFocus();
		buttonPressed(BID_FROM_IMPORTS_ONLY, true, false);
	}

	@Override
	protected void handleEmptyList() {
		this.fTreeViewer.setInput ( null );
		this.fTreeViewer.refresh();

		// VZ: PETALSSTUD-127
		this.fFilteredList.setSelection( new Object[ 0 ]);
		// VZ: PETALSSTUD-127

		try {
			super.handleEmptyList();

		} catch( Exception e ) {
			BPELUIPlugin.log( e, IStatus.WARNING );
		}
	}

	protected void handleNonEmptyList() {

		setEnabled( this.filterText, true);
		setEnabled( this.filterLabel, true);
		setEnabled( this.fFilteredList, true);
	}

	/**
	 * @param
	 * @param b
	 */
	void setEnabled(Control control, boolean b) {
		if (control != null) {
			control.setEnabled(b);
		}
	}

}