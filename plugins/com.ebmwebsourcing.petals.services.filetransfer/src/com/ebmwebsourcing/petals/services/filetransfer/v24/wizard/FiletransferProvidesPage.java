package com.ebmwebsourcing.petals.services.filetransfer.v24.wizard;

import javax.xml.namespace.QName;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.swt.TextWithButtonComposite;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.CopyMode;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

public class FiletransferProvidesPage extends AbstractSuWizardPage implements Adapter {

	protected Contract contract;
	private DataBindingContext dbc;
	
	public void createControl(Composite parent) {
		getNewlyCreatedEndpoint().eAdapters().add(this);
		
		this.dbc = new DataBindingContext();
		// Create the composite container and define its layout.
		final Composite container = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout(2, false);
		layout.marginLeft = layout.marginRight = 15;
		layout.marginTop = 20;
		container.setLayout(layout);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		// Select the service to consume
		Composite subContainer = new Composite(container, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.marginBottom = 19;
		subContainer.setLayout(layout);
		GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
		layoutData.horizontalSpan = 2;
		subContainer.setLayoutData(layoutData);

		Label l = new Label(subContainer, SWT.NONE);
		// l.setImage( this.contractImage );

		l = new Label(subContainer, SWT.NONE);
		l.setText("Select the service contrat to use:");
		l.setToolTipText("The contract to use and configure");

		ComboViewer contractCombo = new ComboViewer(subContainer, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		contractCombo.setLabelProvider(new ContractLabelProvider());
		contractCombo.setContentProvider(new ArrayContentProvider());
		contractCombo.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				FiletransferProvidesPage.this.contract = (Contract) ((IStructuredSelection) event.getSelection()).getFirstElement();
				updateSubWidgets(container);
				validate();
			}
		});

		contractCombo.setInput(Contract.values());
		contractCombo.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				Object sel = ((IStructuredSelection)event.getSelection()).getFirstElement();
				String wsdl = null;
				if (sel == Contract.WRITE_FILES) {
					wsdl = "WriteFiles.wsdl";
					getNewlyCreatedEndpoint().setInterfaceName( new QName( "http://petals.ow2.org/components/filetransfer/version-2", "WriteFiles" ));

				} else if (sel == Contract.READ_FILES) {
					wsdl = "GetFiles.wsdl";
					getNewlyCreatedEndpoint().setInterfaceName( new QName( "http://petals.ow2.org/components/filetransfer/version-2", "GetFiles" ));
				}

				getNewlyCreatedEndpoint().eSet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL, wsdl);
			}
		});

		layoutData = new GridData(GridData.FILL_HORIZONTAL);
		layoutData.horizontalSpan = 2;
		contractCombo.getControl().setLayoutData(layoutData);

		// Add the default widgets
		contractCombo.setSelection(new StructuredSelection(Contract.WRITE_FILES));
		setControl(container);
	}


	/*
	 *
	 */
	@Override
	public boolean validate() {
		return true;
	}
	
	@Override
	public boolean isPageComplete() {
		boolean valid = false;
		if( this.contract == Contract.READ_FILES) {
			Object readDirectory = getNewlyCreatedEndpoint().eGet(FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__READ_DIRECTORY);
			valid = readDirectory != null && ! ((String)readDirectory).trim().isEmpty();

		} else if (this.contract == Contract.WRITE_FILES) {
			Object writeDirectory = getNewlyCreatedEndpoint().eGet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY);
			Object mode = getNewlyCreatedEndpoint().eGet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE);
			valid = writeDirectory != null && ! ((String)writeDirectory).trim().isEmpty() && mode != null;
		}
		return valid;
	}

	/**
	 * Create widgets in function of the contract (provides mode).
	 *
	 * @param container
	 */
	private void updateSubWidgets(Composite container) {

		// Remove children - except the first one
		int childCpt = 0;
		for (Control c : container.getChildren()) {
			childCpt++;
			if (childCpt > 1 && !c.isDisposed())
				c.dispose();
		}

		// Add the new children: "write" mode first
		if (this.contract == Contract.WRITE_FILES) {
			SwtFactory.createLabel( container, "Write Directory * :", "The directory in which the message content will be written");
			TextWithButtonComposite tbc = SwtFactory.createDirectoryBrowser( container );
			this.dbc.bindValue(
					SWTObservables.observeText( tbc.getText(), SWT.Modify),
					EMFObservables.observeValue(
							getNewlyCreatedEndpoint(),
							FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY ));

			SwtFactory.createLabel( container, "Write Mode * :", "What part(s) of the message should be written");

			final ComboViewer viewer = new ComboViewer(container, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
			GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
			viewer.getCombo().setLayoutData(layoutData);
			viewer.setContentProvider(new ArrayContentProvider());
			viewer.setLabelProvider(new LabelProvider());
			viewer.setInput(CopyMode.values());
			this.dbc.bindValue(
					ViewersObservables.observeSingleSelection(viewer),
					EMFObservables.observeValue(
							getNewlyCreatedEndpoint(),
							FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE));

			SwtFactory.createLabel( container, "File Name:", "The base name of the file to write (will be appended the system date)" );
			final Text patterntext = new Text(container, SWT.BORDER);
			patterntext.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));

			this.dbc.bindValue(
					SWTObservables.observeText(patterntext, SWT.Modify),
					EMFObservables.observeValue(
							getNewlyCreatedEndpoint(),
							FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__FILE_PATTERN ));

			viewer.addSelectionChangedListener(new ISelectionChangedListener() {
				public void selectionChanged(SelectionChangedEvent event) {
					Object sel = ((IStructuredSelection)event.getSelection()).getFirstElement();
					patterntext.setEnabled(sel == CopyMode.CONTENT_ONLY);
				}
			});
			
			if (viewer.getSelection().isEmpty()) {
				viewer.setSelection(new StructuredSelection(CopyMode.CONTENT_AND_ATTACHMENTS));
			}
			patterntext.setEnabled(getNewlyCreatedEndpoint().eGet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE) == CopyMode.CONTENT_ONLY);
		}

		// "Get files" mode then
		else {
			SwtFactory.createLabel( container, "Read Directory * :", "The directory to read");
			TextWithButtonComposite tbc = SwtFactory.createDirectoryBrowser( container );
			this.dbc.bindValue(
					SWTObservables.observeText( tbc.getText(), SWT.Modify),
					EMFObservables.observeValue(
							getNewlyCreatedEndpoint(),
							FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__READ_DIRECTORY ));

			SwtFactory.createLabel( container, "Backup Directory:", "The directory into which read files are moved (the temporary directory by default)");
			tbc = SwtFactory.createDirectoryBrowser( container );
			this.dbc.bindValue(
					SWTObservables.observeText( tbc.getText(), SWT.Modify),
					EMFObservables.observeValue(
							getNewlyCreatedEndpoint(),
							FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY ));
		}

		validate();
		container.layout();
	}


	/*
	 *
	 */
	@Override
	public void dispose() {
		if( this.dbc != null ) {
			this.dbc.dispose();
		}
		super.dispose();
		getNewlyCreatedEndpoint().eAdapters().remove(this);
	}

	

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		setPageComplete(isPageComplete());
	}


	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#getTarget()
	 */
	public Notifier getTarget() {
		return getNewlyCreatedEndpoint();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	public void setTarget(Notifier newTarget) {
	}


	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		return true;
	}
}
