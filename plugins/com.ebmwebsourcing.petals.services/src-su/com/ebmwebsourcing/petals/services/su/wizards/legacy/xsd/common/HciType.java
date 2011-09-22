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

package com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;

/**
 * The types defined for abstract HCI.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public enum HciType {
	STRING, INTEGER, DOUBLE, BOOLEAN, ENUM, FILE, URI, QNAME, UNRESOLVED;

	/**
	 * @param type a type name.
	 * @return the associated HciType.
	 */
	public static HciType resolveType( String type ) {

		if( type == null )
			return UNRESOLVED;

		type = NamespaceUtils.removeNamespaceElements( type.toLowerCase());
		if( type.equals( "file" ))  //$NON-NLS-1$
			return FILE;

		if( type.equals( "string" ))  //$NON-NLS-1$
			return STRING;

		if( type.equals( "settablestring" ))  //$NON-NLS-1$
			return STRING;

		if( type.equals( "token" ))  //$NON-NLS-1$
			return STRING;

		if( type.equals( "qname" ))  //$NON-NLS-1$
			return QNAME;

		if( type.equals( "int" ))  //$NON-NLS-1$
			return INTEGER;

		if( type.equals( "long" ))  //$NON-NLS-1$
			return INTEGER;

		if( type.equals( "integer" ))  //$NON-NLS-1$
			return INTEGER;

		if( type.equals( "anyuri" ))  //$NON-NLS-1$
			return URI;

		if( type.equals( "boolean" ))  //$NON-NLS-1$
			return BOOLEAN;

		if( type.equals( "double" ))  //$NON-NLS-1$
			return DOUBLE;

		if( type.equals( "float" ))  //$NON-NLS-1$
			return DOUBLE;

		if( type.equals( "decimal" ))  //$NON-NLS-1$
			return DOUBLE;

		return UNRESOLVED;
	}
}
