/****************************************************************************
 *
 * Copyright (c) 2010-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.su.extensions;

import org.eclipse.jface.resource.ImageDescriptor;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public enum PetalsKeyWords {

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
			result = "Communication Protocol";
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
	 * @return an image descriptor associated with this key word
	 */
	public ImageDescriptor getImageDescriptor() {
		String path = "icons/obj16/choice_" + super.toString() + "_16x16.png";
		return PetalsServicesPlugin.getImageDescriptor( path );
	}


	/**
	 * Resolves a use case from its string value.
	 * @param s a string
	 * @return a Petals use case, or null if the string does not match anything
	 */
	public static PetalsKeyWords resolveString( String s ) {

		PetalsKeyWords result = null;
		for( PetalsKeyWords mode : values()) {
			if( mode.toString().equals( s )) {
				result = mode;
				break;
			}
		}

		return result;
	}
}
