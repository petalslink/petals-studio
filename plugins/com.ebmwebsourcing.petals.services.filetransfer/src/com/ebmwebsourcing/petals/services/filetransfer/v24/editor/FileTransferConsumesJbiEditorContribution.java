/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.filetransfer.v24.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjectUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.services.cdk.editor.CDK5JBIEndpointUIHelper;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers.CommonUIBean;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class FileTransferConsumesJbiEditorContribution extends JbiEditorDetailsContribution {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addMainSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addMainSUContent(final AbstractEndpoint endpoint, FormToolkit toolkit, final Composite mainTab, ISharedEdition ise) {
		Composite composite = createCommonConsumeSection( mainTab, toolkit );
		CommonUIBean commonUiBean = JBIEndpointUIHelpers.createCommonEndpointUI( endpoint, toolkit, composite, ise );

		composite = createEditorSection( mainTab, toolkit, CDK5JBIEndpointUIHelper.CONSUME_TITLE, CDK5JBIEndpointUIHelper.CONSUME_DESC, true );
		CDK5JBIEndpointUIHelper.createConsumesUI( endpoint, toolkit, composite, ise, commonUiBean );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addAdvancedSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedTab, ISharedEdition ise) {

		Composite composite = createEditorSection( advancedTab, toolkit, "File Transfer Parameters", true );
		EObjectUIHelper.generateWidgets(
				endpoint, toolkit, composite, ise.getEditingDomain(), ise.getDataBindingContext(), true,
				Filetransfer2xPackage.Literals.FILE_TRANSFER_CONSUMES__READ_DIRECTORY,
				Filetransfer2xPackage.Literals.FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY,
				Filetransfer2xPackage.Literals.FILE_TRANSFER_CONSUMES__FILE_PATTERN,
				Filetransfer2xPackage.Literals.FILE_TRANSFER_CONSUMES__TRANSFER_MODE,
				Filetransfer2xPackage.Literals.FILE_TRANSFER_CONSUMES__POLLING_PERIOD );

		composite = createEditorSection( advancedTab, toolkit, "CDK Parameters" );
		CDK5JBIEndpointUIHelper.generateDefaultCdkWidgetsForConsumesEditor( endpoint, toolkit, composite, ise );
	}
}
