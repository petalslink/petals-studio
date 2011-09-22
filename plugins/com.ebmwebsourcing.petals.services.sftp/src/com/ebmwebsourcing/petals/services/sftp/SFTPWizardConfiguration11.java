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

package com.ebmwebsourcing.petals.services.sftp;

import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;
import com.ebmwebsourcing.petals.services.su.wizards.SettingConstants;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SFTPWizardConfiguration11 extends WizardConfiguration {

	/**
	 * The constructor which overrides the configuration values.
	 */
	public SFTPWizardConfiguration11 () {

		this.wizardSettings.put( SettingConstants.WSDL_TOOLTIP_VALUE, "If no WSDL is referenced, the \"sftp\" service is set" );
		this.wizardSettings.put( SettingConstants.WSDL_SHOW, "false" );
		this.wizardSettings.put( SettingConstants.WSDL_HIDDEN_VALUE, "SftpService.wsdl" );

		this.wizardSettings.put( SettingConstants.ITF_NAME_VALUE, "SftpInterface" );
		this.wizardSettings.put( SettingConstants.ITF_NAME_ACTIVATE, "false" );

		this.wizardSettings.put( SettingConstants.ITF_NS_VALUE, "http://petals.ow2.org/components/sftp/version-1" );
		this.wizardSettings.put( SettingConstants.SRV_NS_VALUE, "http://petals.ow2.org/components/sftp/version-1" );
		this.wizardSettings.put( SettingConstants.SRV_NS_ACTIVATE, "false" );
		this.wizardSettings.put( SettingConstants.ITF_NS_ACTIVATE, "false" );
	}
}
