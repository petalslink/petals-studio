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

package com.ebmwebsourcing.petals.services.bpel;

import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;
import com.ebmwebsourcing.petals.services.su.wizards.SettingConstants;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BpelWizardConfiguration10 extends WizardConfiguration {

	/**
	 * The constructor which overrides the configuration values.
	 */
	public BpelWizardConfiguration10 () {
		this.wizardSettings.put( SettingConstants.WSDL_ACTIVATE, "false" );
		this.wizardSettings.put( SettingConstants.WSDL_HIDDEN_VALUE, "process.wsdl" );
		this.wizardSettings.put( SettingConstants.SHOW_JBI_PAGE, "false" );
		this.wizardSettings.put( SettingConstants.SHOW_PAGES_AFTER_PROJECT_PAGE, "false" );
		this.wizardSettings.put( SettingConstants.FILL_AND_OPEN_JBI_XML, "false" );
	}
}
