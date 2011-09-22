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

package com.ebmwebsourcing.petals.services.jsr181.wizards;

import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;
import com.ebmwebsourcing.petals.services.su.wizards.SettingConstants;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Jsr181Configuration extends WizardConfiguration {

	/**
	 * The constructor which overrides the configuration values.
	 */
	public Jsr181Configuration () {
		this.wizardSettings.put( SettingConstants.SHOW_JBI_PAGE, "false" );
		this.wizardSettings.put( SettingConstants.OPEN_JBI_XML, "false" );
	}
}
