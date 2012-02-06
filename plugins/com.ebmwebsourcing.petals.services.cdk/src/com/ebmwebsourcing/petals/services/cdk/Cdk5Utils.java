/****************************************************************************
 *
 * Copyright (c) 2011-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.cdk;

import org.eclipse.emf.ecore.xml.type.internal.QName;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
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
		provides.eSet(Cdk5Package.Literals.CDK_SERVICE__TIMEOUT, 30000);
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
		consumes.eSet( Cdk5Package.Literals.CDK_SERVICE__TIMEOUT, 30000 );
		consumes.eSet( Cdk5Package.Literals.CDK5_CONSUMES__OPERATION, new QName( "workaround" ));
		consumes.eSet( Cdk5Package.Literals.CDK5_CONSUMES__MEP, null );
	}


	/**
	 * Generates the default contributions for the CDK section.
	 * @param ae the abstract end-point
	 * @param toolkit the form toolkit
	 * @param parent the parent
	 * @param domain the editing domain
	 * @param dbc the data-binding context
	 */
	public static void generateDefaultCdkWidgetsForProvidesEditor(
			AbstractEndpoint ae,
			FormToolkit toolkit,
			Composite parent,
			ISharedEdition ise ) {

		EObjecttUIHelper.generateWidgets( ae, toolkit, parent, ise.getEditingDomain(), ise.getDataBindingContext(), true,
				Cdk5Package.Literals.CDK_SERVICE__TIMEOUT,
				Cdk5Package.Literals.CDK5_PROVIDES__VALIDATE_WSDL,
				Cdk5Package.Literals.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT,
				Cdk5Package.Literals.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES,
				Cdk5Package.Literals.CDK5_PROVIDES__FORWARD_ATTACHMENTS );
	}
}
