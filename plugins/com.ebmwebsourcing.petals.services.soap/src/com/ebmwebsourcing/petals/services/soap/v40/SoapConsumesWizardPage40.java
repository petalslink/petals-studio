/****************************************************************************
 *
 * Copyright (c) 2008-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/
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
