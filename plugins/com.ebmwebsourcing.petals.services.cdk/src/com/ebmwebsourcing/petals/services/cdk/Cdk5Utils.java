/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.cdk;

import org.eclipse.emf.ecore.xml.type.internal.QName;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class Cdk5Utils {

	/**
	 * Initializes a provide block.
	 * @param provides
	 */
	public static void setInitialProvidesValues(Provides provides) {
		provides.eSet(Cdk5Package.Literals.CDK5_PROVIDES__TIMEOUT, 30000);
		provides.eSet(Cdk5Package.Literals.CDK5_PROVIDES__VALIDATE_WSDL, true);
		provides.eSet(Cdk5Package.Literals.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT, false);
		provides.eSet(Cdk5Package.Literals.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES, false);
		provides.eSet(Cdk5Package.Literals.CDK5_PROVIDES__FORWARD_ATTACHMENTS, false);
		provides.eSet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL, null);
	}


	/**
	 * Initializes a consume block.
	 * <p>
	 * Be careful, the operation is set here.
	 * If it should not be written, it should be set to null.
	 * </p>
	 *
	 * @param consumes
	 */
	public static void setInitialConsumesValues(Consumes consumes) {
		consumes.eSet( Cdk5Package.Literals.CDK5_CONSUMES__TIMEOUT, 30000 );
		consumes.eSet( Cdk5Package.Literals.CDK5_CONSUMES__OPERATION, new QName( "workaround" ));
		consumes.eSet( Cdk5Package.Literals.CDK5_CONSUMES__MEP, null );
	}


	/**
	 * Sets the appropriate MEP value.
	 * @param ae
	 * @param mep
	 */
	public static void setMep( AbstractEndpoint ae, Mep mep ) {
		if( mep == null || mep == Mep.UNKNOWN )
			ae.eSet( Cdk5Package.Literals.CDK5_CONSUMES__MEP, null );
		else
			ae.eSet( Cdk5Package.Literals.CDK5_CONSUMES__MEP, String.valueOf( mep ));
	}
}
