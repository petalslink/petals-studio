/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.sca.wizards;

/**
 * A specialized exception for the "new Petals reference" wizard.
 * @author Vincent Zurczak - EBM WebSourcing
 */
class ScaReferenceException extends Exception {

	/**
	 * The serial ID.
	 */
	private static final long serialVersionUID = 2188052572070469483L;

	/**
	 * Constructor.
	 */
	public ScaReferenceException() {
		super();
	}

	/**
	 * Constructor.
	 * @param message
	 * @param cause
	 */
	public ScaReferenceException( String message, Throwable cause ) {
		super( message, cause );
	}

	/**
	 * Constructor.
	 * @param message
	 */
	public ScaReferenceException( String message ) {
		super( message );
	}

	/**
	 * Constructor.
	 * @param cause
	 */
	public ScaReferenceException( Throwable cause ) {
		super( cause );
	}
}
