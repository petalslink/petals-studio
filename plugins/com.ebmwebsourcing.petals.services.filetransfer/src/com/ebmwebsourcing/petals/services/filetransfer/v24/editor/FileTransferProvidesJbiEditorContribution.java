/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.filetransfer.v24.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsImages;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.editor.CDK5JBIEndpointUIHelper;
import com.ebmwebsourcing.petals.services.filetransfer.Messages;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class FileTransferProvidesJbiEditorContribution implements JbiEditorDetailsContribution {

	public void addMainSUContent(final AbstractEndpoint endpoint, FormToolkit toolkit, final Composite mainTab, ISharedEdition ise) {

		final EditingDomain editingDomain = ise.getEditingDomain();
		final DataBindingContext dbc = ise.getDataBindingContext();

		mainTab.setLayout(new GridLayout(1, false));
		mainTab.setLayoutData(new GridData(GridData.FILL_BOTH));

		Section identificationSection = toolkit.createSection(mainTab, Section.EXPANDED | Section.TITLE_BAR);
		identificationSection.setText(Messages.identification);
		identificationSection.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite identificationComposite = toolkit.createComposite(identificationSection);
		identificationComposite.setLayout(new GridLayout(2, false));
		identificationSection.setClient(identificationComposite);

		CDK5JBIEndpointUIHelper.createProvidesUI(endpoint, toolkit, identificationComposite, ise);
		JBIEndpointUIHelpers.createCommonEndpointUI(endpoint, toolkit, identificationComposite, ise);

		Section filetransferSection = toolkit.createSection(mainTab, Section.EXPANDED | Section.TITLE_BAR);
		filetransferSection.setText(Messages.fileTransfer);
		filetransferSection.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite fileTransferComposite = toolkit.createComposite(filetransferSection);
		fileTransferComposite.setLayout(new GridLayout(2, false));
		filetransferSection.setClient(fileTransferComposite);


		// Mode
		EObjecttUIHelper.generateWidgets(endpoint, toolkit, fileTransferComposite, editingDomain, dbc, true, FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE);
		// write
		toolkit.createLabel(fileTransferComposite, Messages.writeDirectory);
		Composite writeDirectoryComposite = toolkit.createComposite(fileTransferComposite);
		writeDirectoryComposite.setLayout(new GridLayout(2, false));
		writeDirectoryComposite.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		Text writeDirectoryText = new Text(writeDirectoryComposite, SWT.BORDER);
		writeDirectoryText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		Button browseWriteButton = toolkit.createButton(writeDirectoryComposite, Messages.browse, SWT.PUSH);
		browseWriteButton.setImage(PetalsImages.getBrowse());

		// read
		toolkit.createLabel(fileTransferComposite, Messages.readDirectory);
		Composite readDirectoryComposite = toolkit.createComposite(fileTransferComposite);
		readDirectoryComposite.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		readDirectoryComposite.setLayout(new GridLayout(2, false));
		Text readDirectoryText = new Text(readDirectoryComposite, SWT.BORDER);
		readDirectoryText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		Button browseReadButton = toolkit.createButton(readDirectoryComposite, Messages.browse, SWT.PUSH);
		browseReadButton.setImage(PetalsImages.getBrowse());

		dbc.bindValue(
				SWTObservables.observeDelayedValue(200, SWTObservables.observeText(readDirectoryText, SWT.Modify)),
				EMFEditObservables.observeValue( editingDomain, endpoint, FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__READ_DIRECTORY));
		dbc.bindValue(
				SWTObservables.observeDelayedValue(200, SWTObservables.observeText(writeDirectoryText, SWT.Modify)),
				EMFEditObservables.observeValue( editingDomain, endpoint, FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY));


		browseWriteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog directoryDialog = new DirectoryDialog(mainTab.getShell());
				String directory = directoryDialog.open();
				if (directory != null) {
					SetCommand setCommand = new SetCommand( editingDomain, endpoint, FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY, directory);
					editingDomain.getCommandStack().execute(setCommand);

				}
			}
		});
		browseReadButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog directoryDialog = new DirectoryDialog(mainTab.getShell());
				String directory = directoryDialog.open();
				if (directory != null) {
					SetCommand setCommand = new SetCommand( editingDomain, endpoint, FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__READ_DIRECTORY, directory);
					editingDomain.getCommandStack().execute(setCommand);
				}
			}
		});
	}

	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedTab, ISharedEdition ise) {
		advancedTab.setLayout(new GridLayout(1, false));
		advancedTab.setLayoutData(new GridData(GridData.FILL_BOTH));

		Section identificationSection = toolkit.createSection(advancedTab, Section.EXPANDED | Section.TITLE_BAR);
		identificationSection.setText("CDK");
		identificationSection.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite cdkComposite = toolkit.createComposite(identificationSection);
		cdkComposite.setLayout(new GridLayout(2, false));
		identificationSection.setClient(cdkComposite);

		JBIEndpointUIHelpers.createDefaultWidgetsByEIntrospection(endpoint, toolkit, cdkComposite, ise, Cdk5Package.Literals.CDK5_PROVIDES);
	}

}
