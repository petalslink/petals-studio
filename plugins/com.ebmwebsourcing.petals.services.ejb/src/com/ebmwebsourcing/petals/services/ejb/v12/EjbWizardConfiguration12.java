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

package com.ebmwebsourcing.petals.services.ejb.v12;

import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;
import com.ebmwebsourcing.petals.services.su.wizards.SettingConstants;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EjbWizardConfiguration12 extends WizardConfiguration {

	public EjbWizardConfiguration12() {

		//	this.wizardSettings.put( SettingConstants.ITF_NAME_ACTIVATE, "false" );
		//	this.wizardSettings.put( SettingConstants.ITF_NS_ACTIVATE, "false" );
		//	this.wizardSettings.put( SettingConstants.SRV_NS_ACTIVATE, "false" );
		//	this.wizardSettings.put( SettingConstants.ITF_VALIDATE, "false" );
		//	this.wizardSettings.put( SettingConstants.WSDL_SHOW, "false" );

		this.wizardSettings.put( SettingConstants.SHOW_JBI_PAGE, "false" );
	}
}
