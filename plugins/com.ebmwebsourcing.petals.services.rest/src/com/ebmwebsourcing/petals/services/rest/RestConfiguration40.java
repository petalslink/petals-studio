/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.rest;

import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RestConfiguration40 extends WizardConfiguration {

	/**
	 * Constructor.
	 */
	public RestConfiguration40() {

		this.wizardSettings.put( SuWizardSettings.WSDL_SHOW, "false" );
		this.wizardSettings.put( SuWizardSettings.WSDL_HIDDEN_VALUE, "RestService.wsdl" );
		this.wizardSettings.put( SuWizardSettings.WSDL_TOOLTIP_VALUE, "If not set, a generic WSDL is generated for the service." );

		this.wizardSettings.put( SuWizardSettings.ITF_NAME_VALUE, "RestInterface" );
		this.wizardSettings.put( SuWizardSettings.ITF_NAME_ACTIVATE, "false" );

		this.wizardSettings.put( SuWizardSettings.ITF_NS_VALUE, "http://petals.ow2.org/GenericREST/" );
		this.wizardSettings.put( SuWizardSettings.ITF_NS_ACTIVATE, "false" );
		this.wizardSettings.put( SuWizardSettings.SRV_NS_VALUE, "http://petals.ow2.org/GenericREST/" );
		this.wizardSettings.put( SuWizardSettings.SRV_NS_ACTIVATE, "false" );

		// Hide REST options
		addHciItem( "soap:mode", null, null, "REST", false );
	}
}
