/****************************************************************************
 *
 * Copyright (c) 2008-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.extensions;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;


/**
 * Settings for the SU Creation Wizards.
 * <p>
 * This could be merged into the actual {@link AbstractServiceUnitWizard}.
 * </p>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 * @author Mickael Istria - EBM WebSourcing :: Replaced properties by typed values.
 */
public class SuWizardSettings {

	public boolean activateInterfaceName = true;
	public boolean activateServiceName = true;
	public boolean activateServiceNameOnly = false;
	public boolean activateEndpointName = true;

	public boolean validateInterface = true;
	public boolean validateServiceName = true;
	public boolean validateEndpointName = true;

	public boolean showWsdl = true;
	public boolean openJbiEditor = true;
	public boolean showJbiPage = true;

	// Hack for SOAP
	public String wsdlUri;
	public String soapAddress;
	public String soapVersion;

	// Consume fields
	public QName invokedOperation;
	public Mep invocationMep;

	public boolean showOperationName = true;
	public boolean showMep = true;
}
