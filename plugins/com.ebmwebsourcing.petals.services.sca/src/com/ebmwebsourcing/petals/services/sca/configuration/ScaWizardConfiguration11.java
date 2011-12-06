/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.sca.configuration;

import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;

/**
 * The wizard configuration for the version 1.0 of the SCA component.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ScaWizardConfiguration11 extends WizardConfiguration {

	public ScaWizardConfiguration11 () {
		this.wizardSettings.put( SuWizardSettings.CREATE_JAVA_PROJECT, "true" );
		this.wizardSettings.put( SuWizardSettings.SHOW_JBI_PAGE, "false" );
		this.wizardSettings.put( SuWizardSettings.FILL_AND_OPEN_JBI_XML, "false" );
	}
}
