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

import com.ebmwebsourcing.petals.services.filetransfer.Messages;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.jbi.editor.JbiFormEditor;
import com.ebmwebsourcing.petals.services.jbi.editor.common.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.su.JBIEndpointUIHelpers;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

public class FileTransferProvidesJbiEditorContribution implements JbiEditorDetailsContribution {

	public void addMainSUContent(final AbstractEndpoint endpoint, FormToolkit toolkit, final Composite generalDetails, final JbiFormEditor editor) {
		toolkit.createLabel(generalDetails, Messages.contractType);
		Combo contractCombo = new Combo(generalDetails, SWT.READ_ONLY);
		contractCombo.add(Messages.getFiles);
		contractCombo.add(Messages.writeFiles);
		
		JBIEndpointUIHelpers.createCommonEndpointUI(endpoint, toolkit, generalDetails, editor);
		toolkit.createLabel(generalDetails, Messages.fileTransfer).setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, true, false, 2, 1));
		toolkit.createLabel(generalDetails, Messages.writeDirectory);
		Composite writeDirectoryComposite = toolkit.createComposite(generalDetails);
		// write
		writeDirectoryComposite.setLayout(new GridLayout(2, false));
		writeDirectoryComposite.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		Text writeDirectoryText = new Text(writeDirectoryComposite, SWT.BORDER);
		writeDirectoryText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		Button browseWriteButton = toolkit.createButton(writeDirectoryComposite, Messages.browse, SWT.PUSH);

		// Mode
		EObjecttUIHelper.generateWidgets(endpoint, toolkit, generalDetails, editor, new EStructuralFeature[] {
			FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE
		});
		// read
		toolkit.createLabel(generalDetails, Messages.readDirectory);
		Composite readDirectoryComposite = toolkit.createComposite(generalDetails);
		readDirectoryComposite.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		readDirectoryComposite.setLayout(new GridLayout(2, false));
		Text readDirectoryText = new Text(readDirectoryComposite, SWT.BORDER);
		readDirectoryText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		Button browseReadButton = toolkit.createButton(readDirectoryComposite, Messages.browse, SWT.PUSH);
		
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
