package com.ebmwebsourcing.petals.services.filetransfer.v24.wizard;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.TransferMode;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

public class FiletransferConsumesPage extends AbstractSuWizardPage {

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
		
		// Directories first
		String[] labels = { "Backup Directory:" };
		String[] tooltips = {
				};
		final Text[] texts = new Text[2];

		Label readLabel = new Label(container, SWT.NONE);
		readLabel.setText("Read Directory:");
		readLabel.setToolTipText("The directory to read");
		createFileBrowser(container, FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__READ_DIRECTORY);

		Label backupLabel = new Label(container, SWT.NONE);
		readLabel.setText("Backup Directory:");
		readLabel.setToolTipText("The directory into which read files are moved (the temporary directory by default)" );
		createFileBrowser(container, FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY);		

		// Other details
		Label l = new Label(container, SWT.NONE);
		l.setText("Transfer Mode:");
		l.setToolTipText("The way files are sent into Petals ESB");
		final ComboViewer viewer = new ComboViewer(container, SWT.SINGLE
				| SWT.BORDER);
		viewer.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		viewer.setInput(TransferMode.values());
		dbc.bindValue(ViewersObservables.observeSingleSelection(viewer),
				EMFObservables.observeValue(getNewlyCreatedEndpoint(), FileTransferPackage.Literals.FILE_TRANSFER_CONSUMES__TRANSFER_MODE));

		l = new Label(container, SWT.NONE);
		l.setText("File Pattern:");
		l.setToolTipText("The file name pattern to filter the resources to process");
		Text text = new Text(container, SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dbc.bindValue(SWTObservables.observeText(text),
				EMFObservables.observeValue(getNewlyCreatedEndpoint(), FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__FILE_PATTERN));

		l = new Label(container, SWT.NONE);
		l.setText("Polling Period:");
		l.setToolTipText("The time between each polling");
		final Spinner pollingSpinner = new Spinner(container, SWT.BORDER);
		pollingSpinner.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		pollingSpinner.setMaximum(Integer.MAX_VALUE);
		pollingSpinner.setMinimum(0);
		pollingSpinner.setIncrement(100);
		pollingSpinner.setPageIncrement(1000);
		pollingSpinner.setDigits(0);
		dbc.bindValue(SWTObservables.observeSelection(pollingSpinner),
				EMFObservables.observeValue(getNewlyCreatedEndpoint(), FileTransferPackage.Literals.FILE_TRANSFER_CONSUMES__POLLING_PERIOD));
		
		setControl(container);
	}

	/**
	 * Creates a text with a browse button to select a directory on the disk.
	 * 
	 * @param container
	 *            the parent
	 * @return the created text
	 */
	private void createFileBrowser(Composite container, final EStructuralFeature feature) {

		Composite subContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		subContainer.setLayout(layout);
		subContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Text text = new Text(subContainer, SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dbc.bindValue(SWTObservables.observeVisible(text), EMFObservables
				.observeValue(getNewlyCreatedEndpoint(), feature));

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
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void dispose() {
		dbc.dispose();
		super.dispose();
	}
}
