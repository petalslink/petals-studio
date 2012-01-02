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

import java.net.URI;

/**
 * Validate an URI from a String value.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class URIValidator extends AbstractWizardValidator<String> {

	@Override
	public String validate( String value ) {

		if( value == null || value.trim().length() == 0 )
			return "The value is not a valid URI.";

		try {
			new URI( value );
		} catch( Exception e ) {
			return e.getMessage();
		}

		return null;
	}
}
