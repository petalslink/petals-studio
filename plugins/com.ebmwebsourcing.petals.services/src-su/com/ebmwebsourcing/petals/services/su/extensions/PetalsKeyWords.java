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
