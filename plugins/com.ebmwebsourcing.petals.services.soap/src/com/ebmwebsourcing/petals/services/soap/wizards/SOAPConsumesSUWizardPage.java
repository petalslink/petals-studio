/****************************************************************************
 * 
 * Copyright (c) 2008-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.soap.wizards;

import org.eclipse.emf.common.notify.Notification;

import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;
import com.sun.java.xml.ns.jbi.JbiPackage;

/**
 * @author Mickael Istria (EBM Websourcing)
 *
 */
public class SOAPConsumesSUWizardPage extends SimpleFeatureListSuWizardPage {

	public SOAPConsumesSUWizardPage() {
		super(
			SoapPackage.Literals.SOAP_CONSUMES__SOAP_SERVICE_NAME,
			SoapPackage.Literals.SOAP_CONSUMES__SOAP_ACTION,
			SoapPackage.Literals.SOAP_CONSUMES__SYNCHONOUS_TIMEOUT,
			SoapPackage.Literals.SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT,
			SoapPackage.Literals.SOAP_CONSUMES__ENABLE_JMS_TRANSPORT);
				
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		if (notification.getFeature() == JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME) {
			getNewlyCreatedEndpoint().eSet(SoapPackage.Literals.SOAP_CONSUMES__SOAP_SERVICE_NAME, getNewlyCreatedEndpoint().getServiceName().getLocalPart());
		}
	}
}
