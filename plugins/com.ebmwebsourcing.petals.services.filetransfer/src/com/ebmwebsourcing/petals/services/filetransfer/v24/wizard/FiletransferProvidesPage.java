package com.ebmwebsourcing.petals.services.filetransfer.v24.wizard;

import javax.xml.namespace.QName;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.CopyMode;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

public class FiletransferProvidesPage extends AbstractSuWizardPage {

	protected Contract contract;
	private DataBindingContext dbc;

	public void createControl(Composite parent) {
		dbc = new DataBindingContext();
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

		ComboViewer contractCombo = new ComboViewer(subContainer, SWT.DROP_DOWN
				| SWT.BORDER | SWT.READ_ONLY);
		contractCombo.setLabelProvider(new ContractLabelProvider());
		contractCombo.setContentProvider(new ArrayContentProvider());
		contractCombo.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				contract = (Contract) ((IStructuredSelection) event.getSelection()).getFirstElement();
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

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
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
			// UI
			Label l = new Label(container, SWT.NONE);
			l.setText("Write Directory * :");
			l.setToolTipText("The directory in which the message content will be written");
			final Text createText = createFileBrowser(container, FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY);
			
			l = new Label(container, SWT.NONE);
			l.setText("Write Mode * :");
			l.setToolTipText("What part(s) of the message should be written");

			final ComboViewer viewer = new ComboViewer(container, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
			GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
			viewer.getCombo().setLayoutData(layoutData);
			viewer.setContentProvider(new ArrayContentProvider());
			viewer.setLabelProvider(new LabelProvider());
			viewer.setInput(CopyMode.values());
			
			l = new Label(container, SWT.NONE);
			l.setText("File Name:");
			l.setToolTipText("The base name of the file to write (will be appended the system date)");

			final Text patterntext = new Text(container, SWT.BORDER);
			patterntext.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
			
			// dynamics
			createText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					setPageComplete(isPageComplete());
				}
			});
			dbc.bindValue(ViewersObservables.observeSingleSelection(viewer),
					EMFObservables.observeValue(getNewlyCreatedEndpoint(), FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE));
			viewer.addSelectionChangedListener(new ISelectionChangedListener() {
				public void selectionChanged(SelectionChangedEvent event) {
					setPageComplete(isPageComplete());
				}
			});
			dbc.bindValue(SWTObservables.observeText(patterntext, SWT.Modify),
					EMFObservables.observeValue(getNewlyCreatedEndpoint(), FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__FILE_PATTERN));
			
			viewer.addSelectionChangedListener(new ISelectionChangedListener() {
				public void selectionChanged(SelectionChangedEvent event) {
					Object sel = ((IStructuredSelection)event.getSelection()).getFirstElement();
					patterntext.setEnabled(sel == CopyMode.CONTENT_ONLY);
				}
			});

			// init
			Object copyMode = getNewlyCreatedEndpoint().eGet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE);
			if (copyMode == null) {
				getNewlyCreatedEndpoint().eSet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE, CopyMode.CONTENT_ONLY);
			}
			patterntext.setEnabled(getNewlyCreatedEndpoint().eGet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE) == CopyMode.CONTENT_ONLY);
		}

		// "Get files" mode then
		else {
			Label readLabel = new Label(container, SWT.NONE);
			readLabel.setText("Read Directory * :");
			readLabel.setToolTipText("The directory to read");
			final Text readText = createFileBrowser(container, FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__READ_DIRECTORY);
			readText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					setPageComplete(readText.getText() != null && !readText.getText().trim().isEmpty());
				}
			});
			
			Label backupLabel = new Label(container, SWT.NONE);
			backupLabel.setText("Backup Directory:");
			backupLabel.setToolTipText("The directory into which read files are moved (the temporary directory by default)");
			createFileBrowser(container, FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY);
			
		}

		setPageComplete(isPageComplete());
		container.layout();
	}

	/**
	 * Creates a text with a browse button to select a directory on the disk.
	 * 
	 * @param container
	 *            the parent
	 * @return the created text
	 */
	private Text createFileBrowser(Composite container, final EStructuralFeature feature) {
		
		Composite subContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		subContainer.setLayout(layout);
		subContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Text text = new Text(subContainer, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		dbc.bindValue(SWTObservables.observeText(text, SWT.Modify),
				EMFObservables.observeValue(getNewlyCreatedEndpoint(), feature));
		
		Button browseButton = new Button(subContainer, SWT.PUSH);
		browseButton.setText("Browse...");
		browseButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				DirectoryDialog dlg = new DirectoryDialog(getShell());
				dlg.setMessage("Select a directory");
				dlg.setText("Directory Selection");

				String value = text.getText().trim();
				if (!StringUtils.isEmpty(value))
					dlg.setFilterPath(value);

				value = dlg.open();
				if (value != null) {
					getNewlyCreatedEndpoint().eSet(feature, value);
				}
			}
		});
		
		return text;
	}
	
	@Override
	public void dispose() {
		if (dbc != null) {
			dbc.dispose();
		}
		super.dispose();
	}
	
	@Override
	public boolean isPageComplete() {
		if (contract == Contract.READ_FILES) {
			Object readDirectory = getNewlyCreatedEndpoint().eGet(FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__READ_DIRECTORY);
			return readDirectory != null && ! ((String)readDirectory).trim().isEmpty();
		} else if (contract == Contract.WRITE_FILES) {
			Object writeDirectory = getNewlyCreatedEndpoint().eGet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY);
			Object mode = getNewlyCreatedEndpoint().eGet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE);
			return writeDirectory != null && ! ((String)writeDirectory).trim().isEmpty() && mode != null;
		}
		return true;
	}

}
