/****************************************************************************
 * 
 * Copyright (c) 2009-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

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
