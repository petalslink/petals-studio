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

package com.ebmwebsourcing.petals.services.quartz;

import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;
import com.ebmwebsourcing.petals.services.su.wizards.SettingConstants;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class QuartzWizardConfigurationConsume extends WizardConfiguration {

	/**
	 * The constructor which overrides the configuration values.
	 */
	public QuartzWizardConfigurationConsume () {
		this.wizardSettings.put( SettingConstants.SUPPORTED_MEP, "InOnly" );
	}
}
