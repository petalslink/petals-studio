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
 
package com.ebmwebsourcing.petals.services.mail.v31;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.mail.generated.MailService;
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
 * @author Micka�l Istria - EBM WebSourcing
 */
public class MailProvideWizard31 extends AbstractServiceUnitWizard {

	/**
	 * Constructor.
	 */
	public MailProvideWizard31() {
		super();
		this.settings.showWsdl = false;
		this.settings.activateInterfaceName = false;
		this.settings.activateServiceNameOnly = true;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new MailDescription31();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues( AbstractEndpoint ae ) {
		ae.setInterfaceName( new QName( "http://petals.ow2.org/components/mail/version-3", "Mail" ));
		ae.setServiceName( new QName( "http://petals.ow2.org/components/mail/version-3", "change-it" ));

		Cdk5Utils.setInitialProvidesValues((Provides)ae);
		ae.eSet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL, "MailService.wsdl");

		ae.eSet(MailPackage.Literals.MAIL_PROVIDES__SCHEME, Scheme.SMTP);
		ae.eSet(MailPackage.Literals.MAIL_PROVIDES__HOST, "");
		ae.eSet(MailPackage.Literals.MAIL_PROVIDES__PORT, 25);
		ae.eSet(MailPackage.Literals.MAIL_PROVIDES__TO, "");
		ae.eSet(MailPackage.Literals.MAIL_PROVIDES__FROM, "");
		ae.eSet(MailPackage.Literals.MAIL_PROVIDES__SEND_MODE, SendMode.CONTENT_AND_ATTACHMENTS);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint,
	 * org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {
		IFile wsdlFile = resourceFolder.getFile( "MailService.wsdl" );
		createFile( wsdlFile, new MailService().generate( abstractEndpoint ), monitor );
		return Status.OK_STATUS;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getLastCustomWizardPages()
	 */
	@Override
	protected AbstractSuWizardPage[] getLastCustomWizardPages() {
		return new AbstractSuWizardPage[] { new SimpleFeatureListSuWizardPage(
				MailPackage.Literals.MAIL_PROVIDES__SCHEME,
				MailPackage.Literals.MAIL_PROVIDES__HOST,
				MailPackage.Literals.MAIL_PROVIDES__PORT,
				MailPackage.Literals.MAIL_PROVIDES__USER,
				MailPackage.Literals.MAIL_PROVIDES__PASSWORD,
				MailPackage.Literals.MAIL_PROVIDES__FROM,
				MailPackage.Literals.MAIL_PROVIDES__TO,
				MailPackage.Literals.MAIL_PROVIDES__REPLY,
				MailPackage.Literals.MAIL_PROVIDES__SUBJECT,
				MailPackage.Literals.MAIL_PROVIDES__HELOHOST,
				MailPackage.Literals.MAIL_PROVIDES__SEND_MODE )
		};
	}
}
