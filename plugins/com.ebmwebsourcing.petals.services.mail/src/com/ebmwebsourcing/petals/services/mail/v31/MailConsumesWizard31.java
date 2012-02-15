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

package com.ebmwebsourcing.petals.services.mail.v31;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.mail.mail.MailPackage;
import com.ebmwebsourcing.petals.services.mail.mail.Scheme;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 * @author Mickaël Istria - EBM WebSourcing
 */
public class MailConsumesWizard31 extends AbstractServiceUnitWizard {

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
		Cdk5Utils.setInitialConsumesValues((Consumes) ae);

		ae.eSet(MailPackage.Literals.MAIL_CONSUMES__SCHEME, Scheme.SMTP);
		ae.eSet(MailPackage.Literals.MAIL_CONSUMES__HOST, "");
		ae.eSet(MailPackage.Literals.MAIL_CONSUMES__PORT, 25);
		ae.eSet(MailPackage.Literals.MAIL_CONSUMES__USER, "");
		ae.eSet(MailPackage.Literals.MAIL_CONSUMES__PASSWORD, "");

		ae.eSet(MailPackage.Literals.MAIL_CONSUMES__FOLDER, "INBOX");
		ae.eSet(MailPackage.Literals.MAIL_CONSUMES__DELETE, false);
		ae.eSet(MailPackage.Literals.MAIL_CONSUMES__PERIOD, 60000);
		ae.eSet(MailPackage.Literals.MAIL_CONSUMES__ISXMLCONTENT, false);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint,
	 * org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint ae, IProgressMonitor monitor) {

		// MEP + operations
		ae.eSet( Cdk5Package.Literals.CDK5_CONSUMES__OPERATION, this.settings.invokedOperation );
		Cdk5Utils.setMep( ae, this.settings.invocationMep );

		// Do not write useless values
		hackEmfModel( ae,
				MailPackage.Literals.MAIL_CONSUMES__USER,
				MailPackage.Literals.MAIL_CONSUMES__PASSWORD,
				MailPackage.Literals.MAIL_CONSUMES__FOLDER );

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
				MailPackage.Literals.MAIL_CONSUMES__SCHEME,
				MailPackage.Literals.MAIL_CONSUMES__HOST,
				MailPackage.Literals.MAIL_CONSUMES__PORT,
				MailPackage.Literals.MAIL_CONSUMES__USER,
				MailPackage.Literals.MAIL_CONSUMES__PASSWORD,
				MailPackage.Literals.MAIL_CONSUMES__FOLDER,
				MailPackage.Literals.MAIL_CONSUMES__DELETE,
				MailPackage.Literals.MAIL_CONSUMES__PERIOD,
				MailPackage.Literals.MAIL_CONSUMES__ISXMLCONTENT)
		};
	}
}
