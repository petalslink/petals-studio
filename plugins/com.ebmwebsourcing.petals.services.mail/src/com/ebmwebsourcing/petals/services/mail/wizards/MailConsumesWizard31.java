/******************************************************************************
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.mail.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.mail.MailDescription31;
import com.ebmwebsourcing.petals.services.mail.generated.MailService;
import com.ebmwebsourcing.petals.services.mail.mail.MailPackage;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MailConsumesWizard31 extends ComponentCreationWizard {

	public MailConsumesWizard31() {
		getDialogSettings().put( SuWizardSettings.WSDL_SHOW, "false" );
		getDialogSettings().put( SuWizardSettings.ITF_NAME_ACTIVATE, "false" );
	}
	
	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new MailDescription31();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues( AbstractEndpoint ae ) {
		ae.eSet(MailPackage.Literals.MAIL_SERVICE_COMMON__PORT, 25);
		ae.eSet(MailPackage.Literals.MAIL_CONSUMES__DELETE, false);
		ae.eSet(MailPackage.Literals.MAIL_CONSUMES__ISXMLCONTENT, false);
		ae.eSet(MailPackage.Literals.MAIL_CONSUMES__PERIOD, 60000);
		ae.eSet(MailPackage.Literals.MAIL_CONSUMES__FOLDER, "INBOX");
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {

		IFile wsdlFile = resourceFolder.getFile( "MailService.wsdl" );
		createFile( wsdlFile, new MailService().generate( abstractEndpoint ), monitor );
		return Status.OK_STATUS;
	}

	@Override
	protected AbstractSuPage[] getCustomWizardPagesAfterJbi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractSuPage[] getCustomWizardPagesAfterProject() {
		return new AbstractSuPage[] { new MailProvideWizardPage() };
	}

	@Override
	protected AbstractSuPage[] getCustomWizardPagesBeforeProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IStatus importAdditionalFiles(IFolder resourceDirectory, IProgressMonitor monitor) {
		return Status.OK_STATUS;
	}

	@Override
	protected boolean isJavaProject() {
		// TODO Auto-generated method stub
		return false;
	}
}
