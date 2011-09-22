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
package com.ebmwebsourcing.petals.services.su.extensions;


/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public enum PetalsUseCase {

	communication, composition, code, miscellaneous, integration, soa;


	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {

		String result;
		switch( this ) {
		case code:
			result = "Manual Implementation";
			break;
		case communication:
			result = "Communication";
			break;
		case composition:
			result = "Service Composition";
			break;
		case integration:
			result = "Integration Project";
			break;
		case miscellaneous:
			result = "Miscellaneous";
			break;
		case soa:
			result = "SOA Project";
			break;
		default:
			result = "";
		}

		return result;
	}


	/**
	 * Resolves a use case from its string value.
	 * @param s a string
	 * @return a Petals use case, or null if the string does not match anything
	 */
	public static PetalsUseCase resolveString( String s ) {

		PetalsUseCase result = null;
		for( PetalsUseCase mode : values()) {
			if( mode.toString().equals( s )) {
				result = mode;
				break;
			}
		}

		return result;
	}
}
