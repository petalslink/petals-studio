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

/**
 * Check that a String value verifies a regular expression.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RegexValidator extends AbstractWizardValidator<String> {

	private String pattern;


	@Override
	public String validate( String value ) {
		if( this.pattern == null )
			return null;

		if( value == null )
			return "The value is null and can not match the regex pattern";

		if( !value.matches( this.pattern ))
			return "The value does not match the pattern " + this.pattern + ".";

		return null;
	}


	/**
	 * @param pattern the regex pattern to use for validation
	 */
	public void setPattern( String pattern ) {
		this.pattern = pattern;
	}
}
