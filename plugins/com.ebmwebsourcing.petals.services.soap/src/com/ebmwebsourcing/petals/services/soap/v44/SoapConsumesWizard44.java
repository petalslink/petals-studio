/******************************************************************************
 * Copyright (c) 2016, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.soap.v44;

import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.soap.soap.SoapMode;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.soap.v40.SoapConsumesWizard40;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;

/**
 * @author Victor Noel - Linagora
 */
public class SoapConsumesWizard44 extends SoapConsumesWizard40 {

	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new SoapDescription44();
	}

	@Override
	protected void presetServiceValues( AbstractEndpoint endpoint ) {
		Cdk5Utils.setInitialConsumesValues((Consumes) endpoint);
		getNewlyCreatedEndpoint().eSet( SoapPackage.Literals.SOAP_CONSUMES__SOAP_SERVICE_NAME, "" );
		getNewlyCreatedEndpoint().eSet( SoapPackage.Literals.SOAP_CONSUMES__MODE, SoapMode.SOAP );
		getNewlyCreatedEndpoint().eSet( SoapPackage.Literals.SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT, true );
		getNewlyCreatedEndpoint().eSet( SoapPackage.Literals.SOAP_CONSUMES__ENABLE_JMS_TRANSPORT, false );
	}

	@Override
	protected AbstractSuWizardPage[] getLastCustomWizardPages() {
		return new AbstractSuWizardPage[] { new SoapConsumesWizardPage44()};
	}
}
