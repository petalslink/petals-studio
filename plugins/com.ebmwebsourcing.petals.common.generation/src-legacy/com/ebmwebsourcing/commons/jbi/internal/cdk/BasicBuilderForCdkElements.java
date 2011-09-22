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

package com.ebmwebsourcing.commons.jbi.internal.cdk;

import java.util.ArrayList;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;

/**
 * A set of static methods to create an ArrayList of XmlElements for CDKs.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BasicBuilderForCdkElements {

	/** The usual name space prefix for CDK elements. */
	private static final String CDK_NS_PREFIX = "petalsCDK";


	/**
	 * Create an ArrayList of XmlElements for CDK version 3.0.
	 * This list if incomplete because it does not handle interceptors and complex properties.
	 * Null or empty string values are skipped.
	 * 
	 * Here again, this class does not make validation of inputs.
	 * It is the responsibility of the client (the one using this class) to make sure the
	 * arguments are valid values.
	 * 
	 * @param suMode {@linkplain SuMode}
	 * @param wsdl the WSDL file name.
	 * @param timeout the value for timeout.
	 * @param operation the operation name.
	 * @param mep the MEP.
	 * @return the associated ArrayList of XmlElements.
	 */
	public static ArrayList<XmlElement> getCdkElements30( SuMode suMode, String wsdl, String timeout, String operation, String mep ) {
		XmlElement element;
		ArrayList<XmlElement> cdkElements = new ArrayList<XmlElement> ();

		switch( suMode ) {
		case consume:
			if( timeout != null && !"".equals( timeout )) {
				element = new XmlElement();
				element.setName( CDK_NS_PREFIX + ":timeout" );
				element.setValue( timeout );
				cdkElements.add( element );
			}

			if( operation != null && !"".equals( operation )) {
				element = new XmlElement();
				element.setName( CDK_NS_PREFIX + ":operation" );
				element.setValue( operation );
				cdkElements.add( element );
			}

			if( mep != null && !"".equals( mep )) {
				element = new XmlElement();
				element.setName( CDK_NS_PREFIX + ":mep" );
				element.setValue( mep );
				cdkElements.add( element );
			}
			break;

		case provide:
			if( timeout != null && !"".equals( timeout )) {
				element = new XmlElement();
				element.setName( CDK_NS_PREFIX + ":timeout" );
				element.setValue( timeout );
				cdkElements.add( element );
			}

			if( wsdl != null && !"".equals( wsdl )) {
				element = new XmlElement();
				element.setName( CDK_NS_PREFIX + ":wsdl" );
				element.setValue( wsdl );
				cdkElements.add( element );
			}
			break;
		}

		return cdkElements;
	}
}
