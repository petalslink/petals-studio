/******************************************************************************
 * Copyright (c) 2012-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.sftp.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.services.cdk.editor.CDK5JBIEndpointUIHelper;
import com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class SftpProvidesEditorContribution extends JbiEditorDetailsContribution {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addMainSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addMainSUContent( final AbstractEndpoint endpoint, FormToolkit toolkit, final Composite mainTab, ISharedEdition ise ) {
		Composite composite = createCommonProvideSection( mainTab, toolkit );
		CDK5JBIEndpointUIHelper.createProvidesUI(endpoint, toolkit, composite, ise);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addAdvancedSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedTab, ISharedEdition ise) {

		Composite composite = createEditorSection( advancedTab, toolkit, "Server Parameters" );
		EObjecttUIHelper.generateEditorWidgets(endpoint, toolkit, composite, ise.getEditingDomain(), ise.getDataBindingContext(), true,
				SftpPackage.Literals.SFTP_PROVIDES__SERVER,
				SftpPackage.Literals.SFTP_PROVIDES__PORT,
				SftpPackage.Literals.SFTP_PROVIDES__USER,
				SftpPackage.Literals.SFTP_PROVIDES__PASSWORD,
				SftpPackage.Literals.SFTP_PROVIDES__PRIVATEKEY,
				SftpPackage.Literals.SFTP_PROVIDES__PASSPHRASE );

		composite = createEditorSection( advancedTab, toolkit, "SFTP Parameters" );
		EObjecttUIHelper.generateEditorWidgets(endpoint, toolkit, composite, ise.getEditingDomain(), ise.getDataBindingContext(), true,
				SftpPackage.Literals.SFTP_PROVIDES__FOLDER,
				SftpPackage.Literals.SFTP_PROVIDES__MAX_IDLE_TIME,
				SftpPackage.Literals.SFTP_PROVIDES__OVERWRITE,
				SftpPackage.Literals.SFTP_PROVIDES__MAX_CONNECTION );

		composite = createEditorSection( advancedTab, toolkit, "CDK Parameters" );
		CDK5JBIEndpointUIHelper.generateDefaultCdkWidgetsForProvidesEditor( endpoint, toolkit, composite, ise );
	}
}
