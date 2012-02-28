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

package com.ebmwebsourcing.petals.services.soap.v40;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.soap.soap.SoapMode;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SoapConsumesWizard40 extends AbstractServiceUnitWizard {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new SoapDescription40();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #presetServiceValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	protected void presetServiceValues( AbstractEndpoint endpoint ) {
		Cdk5Utils.setInitialConsumesValues((Consumes) endpoint);
		getNewlyCreatedEndpoint().eSet( SoapPackage.Literals.SOAP_CONSUMES__SOAP_SERVICE_NAME, "" );
		getNewlyCreatedEndpoint().eSet( SoapPackage.Literals.SOAP_CONSUMES__MODE, SoapMode.SOAP );
		getNewlyCreatedEndpoint().eSet( SoapPackage.Literals.SOAP_CONSUMES__SYNCHONOUS_TIMEOUT, 0 );
		getNewlyCreatedEndpoint().eSet( SoapPackage.Literals.SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT, true );
		getNewlyCreatedEndpoint().eSet( SoapPackage.Literals.SOAP_CONSUMES__ENABLE_JMS_TRANSPORT, false );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getCustomWizardPagesAfterJbi()
	 */
	@Override
	protected AbstractSuWizardPage[] getLastCustomWizardPages() {
		return new AbstractSuWizardPage[] { new SoapConsumesWizardPage40()};
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus performLastActions( IFolder resourceDirectory, AbstractEndpoint ae, IProgressMonitor monitor ) {

		ae.eSet( Cdk5Package.Literals.CDK5_CONSUMES__OPERATION, this.settings.invokedOperation );
		Cdk5Utils.setMep( ae, this.settings.invocationMep );

		return Status.OK_STATUS;
	}
}
