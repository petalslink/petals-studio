/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
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
