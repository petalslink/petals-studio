/****************************************************************************
 *
 * Copyright (c) 2008-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.extensions;

import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;


/**
 * Settings for the SU Creation Wizards.
 * This could be merged into the actual {@link AbstractServiceUnitWizard}
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 * @author Mickael Istria - EBM WebSourcing. Replaced properties by typed values
 */
public class SuWizardSettings {
	
	public boolean activateInterfaceName = true;
	public boolean validateInterface = true;
	public boolean activateServiceName = true;
	public boolean validateServiceName = true;
	public boolean activateEndpointName = true;
	public boolean validateEndpointName = true;
	public boolean showWsdl = true;
	public boolean openJbiEditor = true;
	public boolean showJbiPage = true;
	public String consumedWsdlUri = "";
	public String consumedComponentName = "";
}
