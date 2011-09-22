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
package org.eclipse.bpel.ui.preferences;


import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.bpel.model.resource.BPELResourceSetImpl;
import org.eclipse.bpel.ui.BPELUIPlugin;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.IHelpContextIds;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.details.providers.ColumnTableProvider;
import org.eclipse.bpel.ui.details.providers.WSILContentProvider;
import org.eclipse.bpel.ui.util.NamespaceUtils;
import org.eclipse.bpel.wsil.model.inspection.Inspection;
import org.eclipse.bpel.wsil.model.inspection.InspectionFactory;
import org.eclipse.bpel.wsil.model.inspection.InspectionPackage;
import org.eclipse.bpel.wsil.model.inspection.Link;
import org.eclipse.bpel.wsil.model.inspection.TypeOfAbstract;
import org.eclipse.bpel.wsil.model.inspection.WSILDocument;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;


/**
 * 
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date May 2, 2007
 *
 */


@SuppressWarnings({"nls","boxing","unchecked"})

public class WSILPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	BPELResourceSetImpl resourceSet = new BPELResourceSetImpl();

	Text wsilURL;

	Table linkTable;
	ColumnTableProvider tableProvider;

	TableViewer linkTableViewer;

	Link fLinkSelection;

	WSILDocument fWsilDocument;

	Button removeButton;
	Button moveUpButton;
	Button moveDownButton;
	Button openInBrowserButton;

	// Track the  modification of any element in the WSIL model.
	// we don't use commands and stacks here.
	EContentAdapter fContentAdapter = new EContentAdapter() {

		/**
		 * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */

		@Override
		public void notifyChanged(Notification arg0) {

			super.notifyChanged(arg0);
			int eventId = arg0.getEventType();
			if (eventId == Notification.ADD ||  eventId == Notification.SET ||
						eventId == Notification.MOVE || eventId == Notification.REMOVE ) {

				WSILPreferencePage.this.linkTableViewer.refresh();
				updateButtons();
			}
		}
	};



	@Override
	protected Control createContents (Composite parent) {

		Composite result = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout( );
		layout.numColumns = 3;
		layout.verticalSpacing = 15;
		layout.makeColumnsEqualWidth = false;

		result.setLayout(layout);
		GridData data = new GridData(GridData.FILL_BOTH);
		// result.setLayoutData(data);

		// WSIL directory
		Label wsilLabel = new Label(result, SWT.NONE);
		wsilLabel.setText(Messages.BPELPreferencePage_WSIL_1);
		wsilLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

		this.wsilURL = new Text(result, SWT.BORDER);
		this.wsilURL.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.wsilURL.addFocusListener( new FocusListener () {


			public void focusGained(FocusEvent e) {
				WSILPreferencePage.this.wsilURL.setData("lastValue",WSILPreferencePage.this.wsilURL.getText());
			}

			public void focusLost(FocusEvent e) {
				String url = WSILPreferencePage.this.wsilURL.getText();

				// no change.
				if (url.equals(WSILPreferencePage.this.wsilURL.getData("lastValue"))) {
					return ;
				}
				attemptLoad( url );
			}

		});

		Button browse = new Button(result, SWT.NONE);
		browse.setText(Messages.BPELPreferencePage_WSIL_2);
		data = new GridData(SWT.RIGHT);
		data.widthHint = 100;

		browse.setLayoutData(data);
		browse.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
				String fileName = fd.open();
				if ((fileName != null) && (fileName.length() > 0)) {
					// parse to file url
					File file = new File(fileName);
					String uri = file.toURI().toString();
					WSILPreferencePage.this.wsilURL.setText( uri );
					attemptLoad(uri);

				}
			}
		}
		);


		// 2nd row of the 3 cell grid

		Label txt = new Label ( result, SWT.NO_BACKGROUND | SWT.WRAP );

		txt.setText( Messages.BPELPreferencePage_WSIL_Description );
		data = new GridData( GridData.GRAB_HORIZONTAL );
		data.horizontalSpan = 3;
		txt.setLayoutData(data);

		//
		//

		// create table
		this.linkTable = new Table(result, SWT.FULL_SELECTION | SWT.V_SCROLL	| SWT.BORDER  );

		data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 2;

		this.linkTable.setLayoutData(data);

		// set up table
		this.linkTable.setLinesVisible(true);
		this.linkTable.setHeaderVisible(true);

		this.tableProvider = new ColumnTableProvider();
		this.tableProvider.add(new IndexColumn());
		this.tableProvider.add(new AbstractColumn());
		this.tableProvider.add(new LocationColumn());
		// tableProvider.add(new NamespaceColumn());

		this.linkTableViewer = new TableViewer(this.linkTable);
		this.tableProvider.createTableLayout(this.linkTable);
		this.linkTableViewer.setLabelProvider(this.tableProvider);
		this.linkTableViewer.setCellModifier(this.tableProvider);

		WSILContentProvider wsilContentProvider = new WSILContentProvider();
		wsilContentProvider.setMode( WSILContentProvider.FLATTEN );

		this.linkTableViewer.setContentProvider( wsilContentProvider );

		this.linkTableViewer.addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return element instanceof Link;
			}
		});

		this.linkTableViewer.setColumnProperties(this.tableProvider.getColumnProperties());
		this.linkTableViewer.setCellEditors(this.tableProvider.createCellEditors(this.linkTable));

		layout = new GridLayout();
		layout.numColumns = 1;
		layout.verticalSpacing = 5;

		Composite buttonList = new Composite ( result , SWT.NONE );
		buttonList.setLayout(layout);

		data = new GridData( GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_CENTER );
		buttonList.setLayoutData( data );

		Button add = new Button(buttonList, SWT.NONE);
		add.setText(Messages.BPELPreferencePage_WSIL_Add);
		data = new GridData(  GridData.FILL_HORIZONTAL );

		add.setLayoutData(data);
		add.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Link link = InspectionFactory.eINSTANCE.createLink();

				link.setLocation(Messages.BPELPreferencePage_WSIL_EnterLocation);
				link.setReferencedNamespace( InspectionPackage.eNS_URI );

				TypeOfAbstract toa = InspectionFactory.eINSTANCE.createTypeOfAbstract();
				toa.setValue(Messages.BPELPreferencePage_WSIL_EnterDescription);
				link.getAbstract().add( toa );

				// modify the document
				EList links = WSILPreferencePage.this.fWsilDocument.getInspection().getLinks();
				links.add ( link );

				// notifications of modifications are sent back to us via the EContentAdapter
				// on the WSIL Document resource.
			}
		});


		this.removeButton = new Button(buttonList, SWT.NONE);
		this.removeButton.setText(Messages.BPELPreferencePage_WSIL_Remove);
		data = new GridData( GridData.FILL_HORIZONTAL );

		this.removeButton.setLayoutData(data);

		this.removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (WSILPreferencePage.this.fLinkSelection == null) {
					return ;
				}
				// modify the document
				EList links = WSILPreferencePage.this.fWsilDocument.getInspection().getLinks();
				links.remove(WSILPreferencePage.this.fLinkSelection);
			}
		});



		this.moveUpButton = new Button(buttonList, SWT.NONE);
		this.moveUpButton.setText(Messages.BPELPreferencePage_WSIL_MoveUp);
		data = new GridData( GridData.FILL_HORIZONTAL );

		this.moveUpButton.setLayoutData(data);

		this.moveUpButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (WSILPreferencePage.this.fLinkSelection == null) {
					return ;
				}
				// modify the document
				EList links = WSILPreferencePage.this.fWsilDocument.getInspection().getLinks();
				int idx = links.indexOf(WSILPreferencePage.this.fLinkSelection);
				if (idx < 0) {
					return ;
				}
				links.move(idx,idx-1);
			}
		});


		this.moveDownButton = new Button(buttonList, SWT.NONE);
		this.moveDownButton.setText(Messages.BPELPreferencePage_WSIL_MoveDown);
		data = new GridData( GridData.FILL_HORIZONTAL );

		this.moveDownButton.setLayoutData(data);

		this.moveDownButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (WSILPreferencePage.this.fLinkSelection == null) {
					return ;
				}
				// modify the document
				EList links = WSILPreferencePage.this.fWsilDocument.getInspection().getLinks();
				int idx = links.indexOf(WSILPreferencePage.this.fLinkSelection);
				if (idx < 0) {
					return ;
				}
				links.move(idx, idx+1);
			}
		});

		this.openInBrowserButton = new Button(buttonList, SWT.NONE);
		this.openInBrowserButton.setText(Messages.BPELPreferencePage_WSIL_OpenInBrowser);
		data = new GridData( GridData.FILL_HORIZONTAL );

		this.openInBrowserButton.setLayoutData(data);

		this.openInBrowserButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (WSILPreferencePage.this.fLinkSelection == null) {
					return ;
				}

				// TODO:
			}
		});



		this.linkTableViewer.addPostSelectionChangedListener( new ISelectionChangedListener () {


			public void selectionChanged (SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection ss = (IStructuredSelection) selection;
					Object obj = ss.getFirstElement();
					WSILPreferencePage.this.fLinkSelection = obj instanceof Link ? (Link) obj : null;
				}
				updateButtons();
			}

		});

		updateButtons();

		initializeValues();

		PlatformUI.getWorkbench().getHelpSystem().setHelp(
					parent, IHelpContextIds.PREFERENCES_PAGE);

		return result;
	}


	/**
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {

	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		initializeDefaults();
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		storeValues();
		return true;
	}

	@Override
	protected void performApply() {
		performOk();
	}

	/**
	 * Initializes states of the controls using default values in the preference store.
	 */
	private void initializeDefaults() {

	}

	/**
	 * Initializes states of the controls from the preference store.
	 */
	private void initializeValues() {
		IPreferenceStore store = BPELUIPlugin.getDefault().getPreferenceStore();

		this.wsilURL.setText(store.getString(IBPELUIConstants.PREF_WSIL_URL));

		attemptLoad (this.wsilURL.getText() );
	}



	/**
	 * Stores the values of the controls back to the preference store.
	 */
	private void storeValues() {
		IPreferenceStore store = BPELUIPlugin.getDefault().getPreferenceStore();

		store.setValue(IBPELUIConstants.PREF_WSIL_URL, this.wsilURL.getText());

		if (this.fWsilDocument != null) {

			Resource resource = this.fWsilDocument.eResource();

			try {
				resource.save(null);
			} catch (IOException e) {
				BPELUIPlugin.log(e);
			}
		}
	}


	void updateButtons ( ) {

		List linkList = (this.fWsilDocument == null ? Collections.EMPTY_LIST : this.fWsilDocument.getInspection().getLinks());

		int idx = linkList.indexOf(this.fLinkSelection);
		// -1 if not found ...
		this.moveUpButton.setEnabled(idx > 0);
		this.moveDownButton.setEnabled(idx >= 0 && idx < linkList.size() - 1);
		this.removeButton.setEnabled( this.fLinkSelection != null );
		this.openInBrowserButton.setEnabled( this.fLinkSelection != null );
	}



	/**
	 * @see org.eclipse.jface.dialogs.DialogPage#dispose()
	 */
	@Override
	public void dispose() {

		Iterator it = this.resourceSet.getResources().iterator();
		while (it.hasNext()) {
			Resource r = (Resource) it.next();
			r.eAdapters().clear();
		}
		// TODO Auto-generated method stub
		super.dispose();
	}


	void attemptLoad ( String url ) {

		url = url.trim();

		if (url.length() < 1) {
			return ;
		}

		if (this.fWsilDocument != null) {
			this.fWsilDocument.eResource().eAdapters().remove( this.fContentAdapter );
			this.fWsilDocument = null;
		}

		org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createURI(url);

		Resource resource = null;
		try {

			resource = this.resourceSet.getResource(uri, true, "wsil");

			List contents = resource.getContents();

			if (contents.size() > 0) {
				if (contents.get(0) instanceof WSILDocument ) {
					this.fWsilDocument = (WSILDocument ) contents.get(0);
				}
			}
			setMessage(null);

		} catch (Throwable t) {
			setMessage(Messages.BPELPreferencePage_WSIL_DocumentNotLoaded, ERROR);
			BPELUIPlugin.log(t);
		}

		this.linkTableViewer.setInput( this.fWsilDocument );

		// Track the  modification of any element in the WSIL model.
		// we don't use commands and stacks here.
		if (this.fWsilDocument != null) {
			this.fWsilDocument.eResource().eAdapters().add( this.fContentAdapter );
		}
	}



	/**
	 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
	 * @date May 4, 2007
	 *
	 */
	public class AbstractColumn extends ColumnTableProvider.Column implements
	ILabelProvider, ICellModifier {

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getHeaderText()
		 */
		@Override
		public String getHeaderText() {
			return Messages.BPELPreferencePage_WSIL_Abstract;
		}

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getProperty()
		 */
		@Override
		public String getProperty() {
			return "abstract"; //$NON-NLS-1$
		}

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getInitialWeight()
		 */
		@Override
		public int getInitialWeight() {
			return 50;
		}


		/**
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		public String getText (Object element) {
			if (element instanceof Link) {
				Link link = (Link) element;
				List abs = link.getAbstract();
				// TODO: Do this based on language ?
				if (abs.size() > 0) {
					TypeOfAbstract absType = (TypeOfAbstract) abs.get(0);
					return absType.getValue();
				}
			}
			return "";
		}

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#createCellEditor(org.eclipse.swt.widgets.Composite)
		 */
		@Override
		public CellEditor createCellEditor (Composite parent) {
			return new TextCellEditor(parent, SWT.NO_BACKGROUND );
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
		 */
		public boolean canModify(Object element, String property) {
			return true;
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
		 */
		public Object getValue(Object element, String property) {
			return getText(element);
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
		 */
		public void modify(Object element, String property, Object value) {
			if (element instanceof Link) {
				Link link = (Link) element;
				List abs = link.getAbstract();
				// TODO: Do this based on language ?
				if (abs.size() > 0) {
					TypeOfAbstract absType = (TypeOfAbstract) abs.get(0);

					// noop, do not needlessly modify
					if (value.equals(absType.getValue())) {
						return ;
					}

					absType.setValue( (String) value);
				}
			}
		}
	}



	/**
	 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
	 * @date May 4, 2007
	 *
	 */
	public class LocationColumn extends ColumnTableProvider.Column
	implements ILabelProvider, ICellModifier {

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getHeaderText()
		 */
		@Override
		public String getHeaderText() {
			return Messages.BPELPreferencePage_WSIL_Location;
		}

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getProperty()
		 */
		@Override
		public String getProperty() {
			return "location"; //$NON-NLS-1$
		}


		/** (non-Javadoc)
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getInitialWeight()
		 */
		@Override
		public int getInitialWeight() {
			return 50;
		}

		/**
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		public String getText (Object element) {
			if (element instanceof Link) {
				Link link = (Link) element;
				return link.getLocation();
			}
			return "";
		}

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#createCellEditor(org.eclipse.swt.widgets.Composite)
		 */
		@Override
		public CellEditor createCellEditor (Composite parent) {
			return new TextCellEditor(parent, SWT.NO_BACKGROUND );
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
		 */
		public boolean canModify(Object element, String property) {
			return true;
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
		 */
		public Object getValue(Object element, String property) {
			return getText(element);
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
		 */
		public void modify (Object element, String property, Object value) {

			if (element instanceof Link) {
				Link link = (Link) element;

				//noop, do not needlessly modify
				if (value.equals(link.getLocation())) {
					return ;
				}

				org.eclipse.emf.common.util.URI linkURI = null;
				try {

					linkURI = org.eclipse.emf.common.util.URI.createURI( (String) value );
					if (linkURI.isRelative()) {
						// path is relative to me ...
						URI parentURI = link.eResource().getURI();
						linkURI = linkURI.resolve(parentURI);
					}

					// so far, so good !
					link.setLocation( linkURI.toString());

					// all goes well
					setMessage(null);

				} catch (java.lang.IllegalArgumentException ex) {

					setMessage(ex.getLocalizedMessage(), ERROR);

				}


			}
		}

	}

	/**
	 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
	 * @date May 4, 2007
	 */
	public class NamespaceColumn extends ColumnTableProvider.Column implements
	ILabelProvider {

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getHeaderText()
		 */

		@Override
		public String getHeaderText() {
			return Messages.BPELPreferencePage_WSIL_Namespace;
		}


		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getProperty()
		 */
		@Override
		public String getProperty() {
			return "namespace"; //$NON-NLS-1$
		}

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getInitialWeight()
		 */
		@Override
		public int getInitialWeight() {
			return 30;
		}

		/**
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		public String getText (Object element) {

			if (element instanceof Link) {
				Link link = (Link) element;
				return  NamespaceUtils.convertUriToNamespace( link.getReferencedNamespace() );
			}
			return "";
		}

	}


	/**
	 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
	 * @date May 4, 2007
	 */
	public class IndexColumn extends ColumnTableProvider.Column implements
	ILabelProvider {

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getHeaderText()
		 */

		@Override
		public String getHeaderText() {
			return Messages.BPELPreferencePage_WSIL_Index;
		}


		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getProperty()
		 */
		@Override
		public String getProperty() {
			return "index"; //$NON-NLS-1$
		}

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getInitialWeight()
		 */
		@Override
		public int getInitialWeight() {
			return 5;
		}

		/**
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		public String getText (Object element) {

			if (element instanceof Link) {
				Link link = (Link) element;
				Inspection insp = (Inspection) link.eContainer();
				return Integer.toString( insp.getLinks().indexOf(link) + 1 );
			}

			return "";
		}
	}


}
