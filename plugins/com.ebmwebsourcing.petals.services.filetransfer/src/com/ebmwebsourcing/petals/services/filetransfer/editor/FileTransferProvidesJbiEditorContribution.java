package com.ebmwebsourcing.petals.services.filetransfer.editor;

import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.ebmwebsourcing.petals.services.PetalsImages;
import com.ebmwebsourcing.petals.services.cdk.editor.CDK5JBIEndpointUIHelper;
import com.ebmwebsourcing.petals.services.filetransfer.Messages;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.jbi.editor.JbiFormEditor;
import com.ebmwebsourcing.petals.services.jbi.editor.common.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.su.JBIEndpointUIHelpers;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

public class FileTransferProvidesJbiEditorContribution implements JbiEditorDetailsContribution {

	public void addMainSUContent(final AbstractEndpoint endpoint, FormToolkit toolkit, final Composite generalDetails, final JbiFormEditor editor) {
		generalDetails.setLayout(new GridLayout(1, false));
		generalDetails.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Section identificationSection = toolkit.createSection(generalDetails, Section.EXPANDED | Section.TITLE_BAR);
		identificationSection.setText(Messages.identification);
		identificationSection.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite identificationComposite = toolkit.createComposite(identificationSection);
		identificationComposite.setLayout(new GridLayout(2, false));
		identificationSection.setClient(identificationComposite);
		
		CDK5JBIEndpointUIHelper.createProvidesUI(endpoint, toolkit, identificationComposite, editor);
		JBIEndpointUIHelpers.createCommonEndpointUI(endpoint, toolkit, identificationComposite, editor);
		
		Section filetransferSection = toolkit.createSection(generalDetails, Section.EXPANDED | Section.TITLE_BAR);
		filetransferSection.setText(Messages.fileTransfer);
		filetransferSection.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite fileTransferComposite = toolkit.createComposite(filetransferSection);
		fileTransferComposite.setLayout(new GridLayout(2, false));
		filetransferSection.setClient(fileTransferComposite);
		 
		toolkit.createLabel(fileTransferComposite, Messages.contractType);
		Combo contractCombo = new Combo(fileTransferComposite, SWT.READ_ONLY);
		contractCombo.add(Messages.getFiles);
		contractCombo.add(Messages.writeFiles);
		
		toolkit.createLabel(fileTransferComposite, Messages.writeDirectory);
		Composite writeDirectoryComposite = toolkit.createComposite(fileTransferComposite);
		// write
		writeDirectoryComposite.setLayout(new GridLayout(2, false));
		writeDirectoryComposite.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		Text writeDirectoryText = new Text(writeDirectoryComposite, SWT.BORDER);
		writeDirectoryText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		Button browseWriteButton = toolkit.createButton(writeDirectoryComposite, Messages.browse, SWT.PUSH);
		browseWriteButton.setImage(PetalsImages.getBrowse());
		
		// Mode
		EObjecttUIHelper.generateWidgets(endpoint, toolkit, fileTransferComposite, editor, new EStructuralFeature[] {
			FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE
		});
		// read
		toolkit.createLabel(fileTransferComposite, Messages.readDirectory);
		Composite readDirectoryComposite = toolkit.createComposite(fileTransferComposite);
		readDirectoryComposite.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		readDirectoryComposite.setLayout(new GridLayout(2, false));
		Text readDirectoryText = new Text(readDirectoryComposite, SWT.BORDER);
		readDirectoryText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		Button browseReadButton = toolkit.createButton(readDirectoryComposite, Messages.browse, SWT.PUSH);
		browseReadButton.setImage(PetalsImages.getBrowse());
		
		editor.getDataBindingContext().bindValue(
				SWTObservables.observeDelayedValue(200, SWTObservables.observeText(readDirectoryText)),
				EMFEditObservables.observeValue(editor.getEditingDomain(), endpoint, FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__READ_DIRECTORY));
		editor.getDataBindingContext().bindValue(
				SWTObservables.observeDelayedValue(200, SWTObservables.observeText(writeDirectoryText)),
				EMFEditObservables.observeValue(editor.getEditingDomain(), endpoint, FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY));
				
		
		browseWriteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog directoryDialog = new DirectoryDialog(generalDetails.getShell());
				String directory = directoryDialog.open();
				if (directory != null) {
					SetCommand setCommand = new SetCommand(editor.getEditingDomain(), endpoint, FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY, directory);
					editor.getEditingDomain().getCommandStack().execute(setCommand);
					
				}
			}
		});
		browseReadButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog directoryDialog = new DirectoryDialog(generalDetails.getShell());
				String directory = directoryDialog.open();
				if (directory != null) {
					SetCommand setCommand = new SetCommand(editor.getEditingDomain(), endpoint, FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__READ_DIRECTORY, directory);
					editor.getEditingDomain().getCommandStack().execute(setCommand);
				}
			}
		});
	}

	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedDetails, JbiFormEditor editor) {
		// TODO Auto-generated method stub

	}

}
