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
import com.ebmwebsourcing.petals.services.soap.soap.SoapVersion;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class SoapProvidesWizardPage40 extends SimpleFeatureListSuWizardPage {

	private String oldSoapVersion, oldSoapAddress;


	/**
	 * Constructor.
	 */
	public SoapProvidesWizardPage40() {
		super(
			SoapPackage.Literals.SOAP_PROVIDES__ADDRESS,
			SoapPackage.Literals.SOAP_PROVIDES__SOAP_VERSION,
			SoapPackage.Literals.SOAP_PROVIDES__CHUNKED_MODE,
			SoapPackage.Literals.SOAP_PROVIDES__SYNCHONOUS_TIMEOUT,
			SoapPackage.Literals.SOAP_PROVIDES__CLEANUP_TRANSPORT,
			SoapPackage.Literals.SOAP_PROVIDES__SYNCHONOUS_TIMEOUT
			);
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #setVisible(boolean)
	 */
	@Override
	public void setVisible( boolean visible ) {

		if( visible ) {
			boolean refresh = false;
			SuWizardSettings settings = getWizard().getSettings();
			if( this.oldSoapAddress == null || ! this.oldSoapAddress.equals( settings.soapAddress )) {
				this.oldSoapAddress = settings.soapAddress;
				refresh = true;
			}

			if( this.oldSoapVersion == null || ! this.oldSoapVersion.equals( settings.soapVersion )) {
				this.oldSoapVersion = settings.soapVersion;
				refresh = true;
			}

			if( refresh ) {
				getNewlyCreatedEndpoint().eSet( SoapPackage.Literals.SOAP_PROVIDES__ADDRESS, settings.soapAddress );
				SoapVersion soapVersion = null;
				if( "1.1".equals( settings.soapVersion ))
					soapVersion = SoapVersion.SOAP_11;
				else if( "1.2".equals( settings.soapVersion ))
					soapVersion = SoapVersion.SOAP_12;

				getNewlyCreatedEndpoint().eSet( SoapPackage.Literals.SOAP_PROVIDES__SOAP_VERSION, soapVersion );
			}
		}

		super.setVisible( visible );
	}
}
