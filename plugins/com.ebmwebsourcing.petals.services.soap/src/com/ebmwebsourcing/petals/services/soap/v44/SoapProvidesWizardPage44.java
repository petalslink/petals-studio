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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjectUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.soap.soap.SoapVersion;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SimpleFeatureListSuWizardPage;

/**
 * @author Victor Noel - Linagora
 */
public class SoapProvidesWizardPage44 extends SimpleFeatureListSuWizardPage {

	private String oldSoapVersion, oldSoapAddress;


	/**
	 * Constructor.
	 */
	public SoapProvidesWizardPage44() {
		super(
			SoapPackage.Literals.SOAP_PROVIDES__WSA_TO,
			SoapPackage.Literals.SOAP_PROVIDES__SOAP_VERSION,
			SoapPackage.Literals.SOAP_PROVIDES__CHUNKED_MODE,
			SoapPackage.Literals.SOAP_PROVIDES__MODE
			);
	}

	@Override
	public void createControl(Composite parent) {
		this.dbc = new DataBindingContext();

		Composite res = new Composite(parent, SWT.NONE);
		SwtFactory.applyNewGridLayout( res, 2, false, 15, 15, 0, 15 );
		setControl(res);

		FormToolkit tk = new FormToolkit(getShell().getDisplay());
		EObjectUIHelper.generateWidgets(getNewlyCreatedEndpoint(), tk, res, null, this.dbc, false,
			SoapPackage.Literals.SOAP_PROVIDES__WSA_TO,
			SoapPackage.Literals.SOAP_PROVIDES__SOAP_VERSION,
			SoapPackage.Literals.SOAP_PROVIDES__CHUNKED_MODE);
	}

	@Override
	public void setVisible( boolean visible ) {

		if( visible ) {
			boolean refresh = false;
			SuWizardSettings settings = getWizard().getSettings();
			if( this.oldSoapAddress == null || this.oldSoapAddress.equals( settings.soapAddress )) {
				this.oldSoapAddress = settings.soapAddress;
				refresh = true;
			}

			if( this.oldSoapVersion == null || this.oldSoapVersion.equals( settings.soapVersion )) {
				this.oldSoapVersion = settings.soapVersion;
				refresh = true;
			}

			if( refresh ) {
				getNewlyCreatedEndpoint().eSet( SoapPackage.Literals.SOAP_PROVIDES__WSA_TO, settings.soapAddress );
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
