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

package com.ebmwebsourcing.petals.common.internal.provisional.ui.validation;

import javax.xml.xpath.XPathFactory;

/**
 * Validate an XPath expression from a String value.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XPathValidator extends AbstractWizardValidator<String> {

	private static XPathFactory factory = XPathFactory.newInstance();

	@Override
	public String validate( String value ) {

		if( value == null )
			return "The value is null and can not be a valid XPath expression.";

		try {
			factory.newXPath().compile( value );

		} catch( Exception e ) {
			String cause = e.getCause().getMessage();
			cause = cause == null ? "" : (" " + cause);
			return "Invalid XPath expression." + cause;
		}

		return null;
	}
}
