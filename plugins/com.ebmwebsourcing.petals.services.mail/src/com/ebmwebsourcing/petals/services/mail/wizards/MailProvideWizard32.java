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

package com.ebmwebsourcing.petals.services.mail.wizards;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.mail.MailDescription31;
import com.ebmwebsourcing.petals.services.mail.generated.MailService32;
import com.ebmwebsourcing.petals.services.mail.mail.MailPackage;
import com.ebmwebsourcing.petals.services.mail.mail.Scheme;
import com.ebmwebsourcing.petals.services.mail.mail.SendMode;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MailProvideWizard32 extends AbstractServiceUnitWizard {

	public MailProvideWizard32() {
		super();
		settings.showWsdl = false;
		settings.activateInterfaceName = false;
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
		ae.setInterfaceName( new QName( "http://petals.ow2.org/components/mail/version-3", "Mail" ));
		ae.setServiceName( new QName( "http://petals.ow2.org/components/mail/version-3", "change-it" ));
		Cdk5Utils.setInitialProvidesValues((Provides)ae);
		ae.eSet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL, "MailService.wsdl");
		ae.eSet(MailPackage.Literals.MAIL_SERVICE_COMMON__SCHEME, Scheme.SMTP);
		ae.eSet(MailPackage.Literals.MAIL_SERVICE_COMMON__PORT, 25);
		ae.eSet(MailPackage.Literals.MAIL_PROVIDES__SEND_MODE, SendMode.CONTENT_AND_ATTACHMENTS);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {
		IFile wsdlFile = resourceFolder.getFile( "MailService.wsdl" );
		createFile( wsdlFile, new MailService32().generate( abstractEndpoint ), monitor );
		return Status.OK_STATUS;
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterJbi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterProject() {
		return new AbstractSuWizardPage[] { new SimpleFeatureListSuWizardPage(
				MailPackage.Literals.MAIL_SERVICE_COMMON__SCHEME,
				MailPackage.Literals.MAIL_SERVICE_COMMON__HOST,
				MailPackage.Literals.MAIL_SERVICE_COMMON__PORT,
				MailPackage.Literals.MAIL_SERVICE_COMMON__USER,
				MailPackage.Literals.MAIL_SERVICE_COMMON__PASSWORD,
				MailPackage.Literals.MAIL_PROVIDES__FROM,
				MailPackage.Literals.MAIL_PROVIDES__REPLY,
				MailPackage.Literals.MAIL_PROVIDES__TO,
				MailPackage.Literals.MAIL_PROVIDES__SUBJECT,
				MailPackage.Literals.MAIL_PROVIDES__HELOHOST,
				MailPackage.Literals.MAIL_PROVIDES__SEND_MODE,
				MailPackage.Literals.MAIL_PROVIDES__CONTENT_TYPE)
		};
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesBeforeProject() {
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
