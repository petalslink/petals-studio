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

package com.ebmwebsourcing.petals.services.su.wizards.legacy.validation;

import java.net.URL;

/**
 * Validate an URL from a String value.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class URLValidator extends AbstractWizardValidator<String> {

	@Override
	public String validate( String value ) {
		if( value == null )
			return "The value is null and can not be a valid URL.";

		try {
			new URL( value );
		} catch( Exception e ) {
			return e.getMessage();
		}

		return null;
	}
}
