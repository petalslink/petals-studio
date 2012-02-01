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

package com.ebmwebsourcing.petals.services.filetransfer.v3.editor;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.editor.CDK5JBIEndpointUIHelper;
import com.ebmwebsourcing.petals.services.filetransfer.Messages;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class FileTransfer3ConsumesJbiEditorContribution implements JbiEditorDetailsContribution {

	public void addMainSUContent(final AbstractEndpoint endpoint, FormToolkit toolkit, final Composite mainTab, ISharedEdition ise) {
		mainTab.setLayout(new GridLayout(1, false));
		mainTab.setLayoutData(new GridData(GridData.FILL_BOTH));

		Section identificationSection = toolkit.createSection(mainTab, Section.EXPANDED | Section.TITLE_BAR);
		identificationSection.setText(Messages.identification);
		identificationSection.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite identificationComposite = toolkit.createComposite(identificationSection);
		identificationComposite.setLayout(new GridLayout(2, false));
		identificationSection.setClient(identificationComposite);

		CDK5JBIEndpointUIHelper.createConsumesUI(endpoint, toolkit, identificationComposite, ise);
	}

	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedTab, ISharedEdition ise) {
		advancedTab.setLayout(new GridLayout(1, false));
		advancedTab.setLayoutData(new GridData(GridData.FILL_BOTH));

		Section ftSection = toolkit.createSection(advancedTab, Section.EXPANDED | Section.TITLE_BAR);
		ftSection.setText(Messages.fileTransfer);
		ftSection.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite ftComposite = toolkit.createComposite(ftSection);
		ftComposite.setLayout(new GridLayout(2, false));
		ftSection.setClient(ftComposite);
		JBIEndpointUIHelpers.createDefaultWidgetsByEIntrospection(endpoint, toolkit, ftComposite, ise, Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES);
		
		Section cdkSection = toolkit.createSection(advancedTab, Section.EXPANDED | Section.TITLE_BAR);
		cdkSection.setText("CDK");
		cdkSection.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite cdkComposite = toolkit.createComposite(cdkSection);
		cdkComposite.setLayout(new GridLayout(2, false));
		cdkSection.setClient(cdkComposite);
		JBIEndpointUIHelpers.createDefaultWidgetsByEIntrospection(endpoint, toolkit, cdkComposite, ise, Cdk5Package.Literals.CDK5_CONSUMES);
	}

}
