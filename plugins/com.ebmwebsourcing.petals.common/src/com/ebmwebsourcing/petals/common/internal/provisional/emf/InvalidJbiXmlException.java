/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.internal.provisional.emf;

/**
 * An exception thrown if the parsing of a jbi.xml file fails.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class InvalidJbiXmlException extends Exception {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -9021815401143063001L;


	/**
	 * Constructor.
	 * @param sourceException the exception that triggered the creation of this exception
	 */
	public InvalidJbiXmlException( Exception sourceException ) {
		setStackTrace( sourceException.getStackTrace());
	}
}
