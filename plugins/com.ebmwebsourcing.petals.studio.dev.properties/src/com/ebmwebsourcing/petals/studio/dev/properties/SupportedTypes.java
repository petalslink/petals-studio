/******************************************************************************
 * Copyright (c) 2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.studio.dev.properties;

/**
 * @author Vincent Zurczak - Linagora
 */
public enum SupportedTypes {

	INTEGER( "integer" ),
	LONG( "long" ),
	FLOAT( "float" ),
	DOUBLE( "double" ),
	BOOLEAN( "boolean" ),
	STRING( "string" ),
	ENUMERATION( "enumeration" ),
	LIST( "list" ),
	QNAME( "qname" );


	private String s;


	/**
	 * Constructor.
	 * @param s
	 */
	private SupportedTypes( String s ) {
		this.s = s;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.s;
	}

	/**
	 * Finds (smartly) the type from a string.
	 * @param s any string
	 * @return the type associated with this string
	 */
	public static SupportedTypes findType( String s ) {

		SupportedTypes result = STRING;
		for( SupportedTypes type : values()) {
			if( type.toString().equalsIgnoreCase( s )) {
				result = type;
				break;
			}
		}

		return result;
	}
}
