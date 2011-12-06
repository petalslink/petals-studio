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

package com.ebmwebsourcing.petals.services.su.extensions;

import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuWizardSettings {

	public static final String ITF_NAME_ACTIVATE = "petals_activate_interface_name";
	public static final String ITF_VALIDATE = "petals_validate_interface";

	public static final String SRV_NAME_ACTIVATE = "petals_activate_service_name";
	public static final String SRV_VALIDATE = "petals_validate_service";

	public static final String EDPT_NAME_ACTIVATE = "petals_activate_endpoint_name";
	public static final String EDPT_VALIDATE = "petals_validate_endpoint";

	public static final String WSDL_SHOW = "petals_show_wsdl";
	public static final String OPEN_JBI_XML = "petals_open_jbi_xml";
	public static final String SHOW_JBI_PAGE = "petals_show_jbi_page";

	public static final String CONSUMED_WSDL_URI = "petals_wsdl_uri_of_a_consumed_service";
	public static final String CONSUMED_COMPONENT_NAME = "petals_consumed_component_name";


	/**
	 * Creates a dialog settings object and puts the default values in it.
	 * @return a dialog settings object
	 */
	public static IDialogSettings createDefaultSettings() {

		IDialogSettings settings = new DialogSettings( "PetalsSection" );

		settings.put( SuWizardSettings.ITF_NAME_ACTIVATE, "true" );
		settings.put( SuWizardSettings.ITF_VALIDATE, "true" );

		settings.put( SuWizardSettings.SRV_NAME_ACTIVATE, "true" );
		settings.put( SuWizardSettings.SRV_VALIDATE, "true" );

		settings.put( SuWizardSettings.EDPT_NAME_ACTIVATE, "true" );
		settings.put( SuWizardSettings.EDPT_VALIDATE, "true" );

		settings.put( SuWizardSettings.SHOW_JBI_PAGE, "true" );
		settings.put( SuWizardSettings.OPEN_JBI_XML, "true" );
		settings.put( SuWizardSettings.WSDL_SHOW, "true" );
		settings.put( SuWizardSettings.CONSUMED_WSDL_URI, "" );

		return settings;
	}
}
