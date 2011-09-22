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
package com.ebmwebsourcing.petals.services.soap;

import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SoapConfigurationConsumes40 extends WizardConfiguration {

	/**
	 * Constructor.
	 */
	public SoapConfigurationConsumes40() {

		// Hide REST options
		addHciItem( "soap:mode", null, null, "SOAP", false );
	}
}
