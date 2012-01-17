package com.ebmwebsourcing.petals.services.filetransfer.v24.wizard;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.swt.TextWithButtonComposite;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.TransferMode;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

public class FiletransferConsumesPage extends AbstractSuWizardPage {

	private DataBindingContext dbc;

	public void createControl(Composite parent) {
		this.dbc = new DataBindingContext();
		// Create the composite container and define its layout.
		final Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginLeft = layout.marginRight = 15;
		layout.marginTop = 20;
		container.setLayout(layout);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		// Directories first
		String[] labels = { "Backup Directory:" };
		String[] tooltips = {};
		final Text[] texts = new Text[2];

		Listener listener = new Listener() {
			public void handleEvent( Event event ) {
				validate();
			}
		};

		SwtFactory.createLabel( container, "Read Directory:", "The directory to read");
		TextWithButtonComposite tbc = SwtFactory.createDirectoryBrowser( container );
		tbc.getText().addListener( SWT.Modify, listener );
		this.dbc.bindValue(
				SWTObservables.observeText( tbc.getText(), SWT.Modify),
				EMFObservables.observeValue(
						getNewlyCreatedEndpoint(),
						FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__READ_DIRECTORY ));

		SwtFactory.createLabel( container, "Backup Directory:", "The directory into which read files are moved (the temporary directory by default)" );
		tbc = SwtFactory.createDirectoryBrowser( container );
		tbc.getText().addListener( SWT.Modify, listener );
		this.dbc.bindValue(
				SWTObservables.observeText( tbc.getText(), SWT.Modify),
				EMFObservables.observeValue(
						getNewlyCreatedEndpoint(),
						FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY ));

		// Other details
		SwtFactory.createLabel( container, "Transfer Mode:", "The way files are sent into Petals ESB" );
		final ComboViewer viewer = new ComboViewer(container, SWT.SINGLE | SWT.BORDER);
		viewer.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		viewer.setInput(TransferMode.values());
		this.dbc.bindValue(
				ViewersObservables.observeSingleSelection(viewer),
				EMFObservables.observeValue(
						getNewlyCreatedEndpoint(),
						FileTransferPackage.Literals.FILE_TRANSFER_CONSUMES__TRANSFER_MODE));

		SwtFactory.createLabel( container, "File Pattern:", "The file name pattern to filter the resources to process");
		Text text = new Text(container, SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.dbc.bindValue(SWTObservables.observeText(text),
				EMFObservables.observeValue(getNewlyCreatedEndpoint(), FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__FILE_PATTERN));

		SwtFactory.createLabel( container, "Polling Period:", "The time between each polling");
		final Spinner pollingSpinner = new Spinner(container, SWT.BORDER);
		pollingSpinner.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		pollingSpinner.setMaximum(Integer.MAX_VALUE);
		pollingSpinner.setMinimum(0);
		pollingSpinner.setIncrement(100);
		pollingSpinner.setPageIncrement(1000);
		pollingSpinner.setDigits(0);
		this.dbc.bindValue(
				SWTObservables.observeSelection(pollingSpinner),
				EMFObservables.observeValue(
						getNewlyCreatedEndpoint(),
						FileTransferPackage.Literals.FILE_TRANSFER_CONSUMES__POLLING_PERIOD ));

		setControl(container);
	}


	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dispose() {
		this.dbc.dispose();
		super.dispose();
	}
}
