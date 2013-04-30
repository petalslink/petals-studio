/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.internal.provisional.refactoring;

/**
 * A bean that can be used to define an element to refactor inside a same file.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsRefactoringBean {

	/**
	 * The regular expression to search.
	 */
	private String regex;

	/**
	 * The regular expression that defines a left delimiter (can be null).
	 */
	private String leftRegex;

	/**
	 * The regular expression that defines a right delimiter (can be null).
	 */
	private String rightRegex;

	/**
	 * The replacement value.
	 */
	private String newValue;


	/**
	 * @return the regex
	 */
	public String getRegex() {
		return this.regex;
	}


	/**
	 * @param regex the regex to set
	 */
	public void setRegex( String regex ) {
		this.regex = regex;
	}


	/**
	 * @return the leftRegex
	 */
	public String getLeftRegex() {
		return this.leftRegex;
	}


	/**
	 * @param leftRegex the leftRegex to set
	 */
	public void setLeftRegex( String leftRegex ) {
		this.leftRegex = leftRegex;
	}


	/**
	 * @return the rightRegex
	 */
	public String getRightRegex() {
		return this.rightRegex;
	}


	/**
	 * @param rightRegex the rightRegex to set
	 */
	public void setRightRegex( String rightRegex ) {
		this.rightRegex = rightRegex;
	}


	/**
	 * @return the newValue
	 */
	public String getNewValue() {
		return this.newValue;
	}


	/**
	 * @param newValue the newValue to set
	 */
	public void setNewValue( String newValue ) {
		this.newValue = newValue;
	}
}
