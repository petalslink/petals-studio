/******************************************************************************
 * Copyright (c) 2008-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.soap.v40;

import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class SoapConsumesWizardPage40 extends SimpleFeatureListSuWizardPage {

	private String oldServiceName;


	/**
	 * Constructor.
	 */
	public SoapConsumesWizardPage40() {
		super(
			SoapPackage.Literals.SOAP_CONSUMES__SOAP_SERVICE_NAME,
			SoapPackage.Literals.SOAP_CONSUMES__SOAP_ACTION,
			SoapPackage.Literals.SOAP_CONSUMES__SYNCHONOUS_TIMEOUT,
			SoapPackage.Literals.SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT,
			SoapPackage.Literals.SOAP_CONSUMES__ENABLE_JMS_TRANSPORT);
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #setVisible(boolean)
	 */
	@Override
	public void setVisible( boolean visible ) {

		if( visible ) {
			AbstractEndpoint ae = getNewlyCreatedEndpoint();
			String newServiceName = null;
			if( ae.getServiceName() != null )
				newServiceName = ae.getServiceName().getLocalPart();
			else if( ae.getInterfaceName() != null )
				newServiceName = ae.getInterfaceName().getLocalPart();

			if( newServiceName == null )
				newServiceName = "";

			if( this.oldServiceName == null || ! this.oldServiceName.equals( newServiceName )) {
				this.oldServiceName = newServiceName;
				ae.eSet( SoapPackage.Literals.SOAP_CONSUMES__SOAP_SERVICE_NAME, newServiceName );
			}
		}

		super.setVisible( visible );
	}
}
