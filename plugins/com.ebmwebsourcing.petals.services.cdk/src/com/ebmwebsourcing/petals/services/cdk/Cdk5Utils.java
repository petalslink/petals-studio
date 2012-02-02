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
package com.ebmwebsourcing.petals.services.cdk;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Mickael Istria (EBM WebSourcing)
 */
public class Cdk5Utils {

	public static void setInitialProvidesValues(Provides provides) {
		provides.eSet(Cdk5Package.Literals.CDK_SERVICE__TIMEOUT, 30000);
		provides.eSet(Cdk5Package.Literals.CDK5_PROVIDES__VALIDATE_WSDL, true);
		provides.eSet(Cdk5Package.Literals.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT, false);
		provides.eSet(Cdk5Package.Literals.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES, false);
		provides.eSet(Cdk5Package.Literals.CDK5_PROVIDES__FORWARD_ATTACHMENTS, false);
		provides.eSet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL, null);
	}


	public static void setInitialConsumesValues(Consumes consumes) {
		consumes.eSet(Cdk5Package.Literals.CDK_SERVICE__TIMEOUT, 30000);
		consumes.eSet(Cdk5Package.Literals.CDK5_CONSUMES__OPERATION, null);
		consumes.eSet(Cdk5Package.Literals.CDK5_CONSUMES__MEP, null);
	}
}
