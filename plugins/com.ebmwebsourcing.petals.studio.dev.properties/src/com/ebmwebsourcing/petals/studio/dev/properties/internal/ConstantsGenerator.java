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

package com.ebmwebsourcing.petals.studio.dev.properties.internal;

import java.util.Collection;
import java.util.Iterator;

import com.ebmwebsourcing.petals.studio.dev.properties.AbstractModel;
import com.ebmwebsourcing.petals.studio.dev.properties.SupportedTypes;

/**
 * A class that generates a Java class with constants and important meta information.
 * @author Vincent Zurczak - Linagora
 */
public class ConstantsGenerator {

	/**
	 * Generates a simple Java class with constants to easily access model properties.
	 * @param javaPackage the Java package
	 * @param className the class name
	 * @param model the abstract model
	 * @return the Java class' content
	 */
	public String generateJavaClass( String javaPackage, String className, AbstractModel model ) {

		StringBuilder sb = new StringBuilder();
		sb.append( "package " + javaPackage + ";\n\n" );
		sb.append( "public class " + className + " {\n" );

		// Generate the constants
		for( String key : model.getPropertyKeys()) {
			if( AbstractModel.PROPERTY_VERSION.equals( key ))
				continue;

			sb.append( "\n\t/**\n" );
			String doc = model.getDocumentation( key );
			int indexDot = doc.indexOf( '.' );
			int indexBl = doc.indexOf( '\n' );
			int index = indexDot == -1 ? indexBl : indexBl == -1 ? indexDot : Math.min( indexDot, indexBl );

			boolean multiLine = index > 0 && index < doc.length() - 1;
			if( multiLine) {
				index ++;
				sb.append( "\t * " + doc.substring( 0, index ).trim() + "\n" );
				doc = "<p>\n" + doc.substring( index ).trim().replaceAll( "((\n\r)|(\n)){2,}", "\n</p>\n<p>\n" );
			}

			for( String s : doc.split( "\n" ))
				sb.append( "\t * " + s + "\n" );

			if( multiLine )
				sb.append( "\t * </p>\n" );

			sb.append( "\t */\n" );
			sb.append( "\tpublic static final String " );
			sb.append( key.toUpperCase().replaceAll( "\\.|-", "_" ));
			sb.append( " = \"" );
			sb.append( key );
			sb.append( "\";\n" );
		}

		// Generate enumerations
		for( String key : model.getPropertyKeys()) {
			if( AbstractModel.PROPERTY_VERSION.equals( key ))
				continue;

			if( model.getType( key ) != SupportedTypes.ENUMERATION )
				continue;

			Collection<String> values = model.getEnumeration( key );
			if( values.isEmpty())
				continue;

			String enumName = Utils.capitalize( key.replaceAll( "-|_", " " ));
			enumName = enumName.replaceAll( "\\s", "" );

			sb.append( "\n\t/**\n" );
			sb.append( "\t * Enumeration values for the '" + key + "' property.\n" );
			sb.append( "\t */\n" );

			sb.append( "\tpublic enum " );
			sb.append( enumName );
			sb.append( " {\n\t\t" );
			for( Iterator<String> it = values.iterator(); it.hasNext(); ) {
				String string = it.next();
				sb.append( string.toUpperCase().replaceAll( "-|\\.|\\s+", "_" ));
				sb.append( "(\"" + string + "\")" );
				sb.append( it.hasNext() ? ",\n\t\t" : ";\n\n" );
			}

			sb.append( "\t\tprivate String value;\n" );
			sb.append( "\t\tprivate " + enumName + "( String value ) {\n\t\t\tthis.value = value;\n\t\t}\n\t\n" );
			sb.append( "\t\tpublic String toString() {\n\t\t\treturn value;\n\t\t}\n\t\n" );
			sb.append( "\t\tpublic static " + enumName + " parse( String s ) {\n" );
			sb.append( "\t\t\tfor( " + enumName + " val : values())\n" );
			sb.append( "\t\t\t\tif( val.toString().equalsIgnoreCase( s ))\n" );
			sb.append( "\t\t\t\t\treturn val;\n" );
			sb.append( "\t\t\t\n\t\t\treturn null;\n" );
			sb.append( "\t\t}\n\t}\n" );
		}

		sb.append( "}\n" );
		return sb.toString().replaceAll( "\t \\* <p>\n\t \\* </p>\n", "" );
	}
}
