/******************************************************************************
 * Copyright (c) 2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.studio.dev.properties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import asia.redact.bracket.properties.Properties;

import com.ebmwebsourcing.petals.studio.dev.properties.internal.Utils;

/**
 * The handler for configuration properties.
 * @author Vincent Zurczak - Linagora
 */
public class AbstractModel {

	/**
	 * A keyword to indicate the empty string.
	 */
	public static final String VALUE_EMPTY = "EMPTY STRING";

	/**
	 * Use {@link AbstractModel#getModelVersion()}.
	 */
	public static final String PROPERTY_VERSION = "version";

	private static final String TYPE_PREFIX = "Type: ";
	private static final String TYPE_NULLABLE = "nullable";
	private static final String TYPE_REQUIRED = "required";
	private static final String TYPE_PATTERN_RANGE_1 = "(\\[|\\])\\s*(\\d+)\\s*,\\s*(\\d*)\\s*(\\[|\\])"; // [ min, optional-max ]
	private static final String TYPE_PATTERN_RANGE_2 = "(\\[|\\])\\s*(\\d*)\\s*,\\s*(\\d+)\\s*(\\[|\\])"; // [ optional-min, max ]
	private static final String TYPE_PATTERN_LIST = "\\{([^}]+)\\}";
	private static final String TYPE_PATTERN =
			AbstractModel.TYPE_PREFIX
			+ "((" + SupportedTypes.BOOLEAN + ")|"
			+ "(" + SupportedTypes.DOUBLE + ")|"
			+ "(" + SupportedTypes.FLOAT + ")|"
			+ "(" + SupportedTypes.STRING + ")|"
			+ "(" + SupportedTypes.LIST + ")|"
			+ "(" + SupportedTypes.INTEGER + "\\s*(" + TYPE_PATTERN_RANGE_1 +  ")*)|"
			+ "(" + SupportedTypes.INTEGER + "\\s*(" + TYPE_PATTERN_RANGE_2 +  ")*)|"
			+ "(" + SupportedTypes.ENUMERATION + "\\s*(" + TYPE_PATTERN_LIST + ")*)|"
			+ "(" + SupportedTypes.LONG + "))"
			+ "(\\s*,\\s*" + TYPE_REQUIRED + ""
			+ "(\\s*,\\s*" + TYPE_NULLABLE + ")?)?";

	private final Properties properties;



	/**
	 * Empty constructor (for tests).
	 */
	protected AbstractModel() {
		this.properties = Properties.Factory.getInstance();
	}


	/**
	 * Constructor which reads properties from a file.
	 * @param propertiesFile a properties file
	 * @throws IOException if something went wrong
	 */
	public AbstractModel( File propertiesFile ) throws IOException {
		Reader reader = new FileReader( propertiesFile );
        this.properties = Properties.Factory.getInstance( reader );
	}


	/**
	 * Constructor which reads properties from an input stream.
	 * <p>
	 * The input stream is not closed by this method.
	 * </p>
	 *
	 * @param input an input stream for a properties file
	 * @throws IOException if something went wrong
	 */
	public AbstractModel( InputStream input ) throws IOException {
        this.properties = Properties.Factory.getInstance( input );
	}


	/**
	 * Insert properties from a map.
	 * @param map a map with the properties
	 * @throws IOException if something went wrong
	 */
	public final void insert( Map<String,Object> map ) throws IOException {
        for( Map.Entry<String,Object> entry : map.entrySet()) {
        	String value = entry.getValue() == null ? null : String.valueOf( entry.getValue());
        	this.properties.put( entry.getKey(), value );
        }
	}


	/**
	 * @return the model version (only mandatory property)
	 */
	public String getModelVersion() {
		return this.properties.get( PROPERTY_VERSION ).trim();
	}


	/**
	 * Returns a trimmed property.
	 * @param property a property
	 * @return a trimmed property value, or null if the value was null
	 */
	public String getTrimmedProperty( String property ) {
		String s =  this.properties.get( property );
		return s == null ? null : s.trim();
	}


	/**
	 * Gets an integer range associated with a property.
	 * @param property a property
	 * @return null if the property is not an integer or did not have a range, a range otherwise
	 */
	public IntegerRange getRange( String property ) {

		IntegerRange result = null;
		if( getType( property ) == SupportedTypes.INTEGER ) {

			String typeDef = findTypeDeclaration( property );
			if( typeDef != null ) {

				boolean found = false;
				Matcher m = Pattern.compile( TYPE_PATTERN_RANGE_1 ).matcher( typeDef );
				if( ! ( found = m.find())) {
					m = Pattern.compile( TYPE_PATTERN_RANGE_2 ).matcher( typeDef );
					found = m.find();
				}

				if( found ) {
					boolean includeMin = m.group( 1 ).charAt( 0 ) == '[';
					boolean includeMax = m.group( 4 ).charAt( 0 ) == ']';

					String startAS = m.group( 2 );
					int min = Utils.isEmpty( startAS ) ? -1 : Integer.valueOf( startAS );

					String endAS = m.group( 3 );
					int max = Utils.isEmpty( endAS ) ? -1 : Integer.valueOf( endAS );

					result = new IntegerRange( min, max, includeMin, includeMax );
				}
			}
		}

		return result;
	}


	/**
	 * Gets the enumeration items.
	 * @param property a property
	 * @return null if the property is not an enumeration, a list of items otherwise (possibly empty)
	 */
	public Collection<String> getEnumeration( String property ) {

		Collection<String> result = null;
		if( getType( property ) == SupportedTypes.ENUMERATION ) {
			result = new HashSet<String> ();
			String typeDef = findTypeDeclaration( property );
			Matcher m = Pattern.compile( TYPE_PATTERN_LIST ).matcher( typeDef );
			if( m.find()) {
				for( String s : m.group( 1 ).split( ";" ))
					result.add( s.trim());
			}
		}

		return result;
	}


	/**
	 * Gets the type of a property.
	 * @param property a property
	 * @return the associated type (String by default)
	 */
	public SupportedTypes getType( String property ) {

		SupportedTypes result = SupportedTypes.STRING;
		String typeDef = findTypeDeclaration( property );
		if( typeDef != null ) {
			typeDef = typeDef.substring( TYPE_PREFIX.length()).toLowerCase();

			for( SupportedTypes type : SupportedTypes.values()) {
				if( typeDef.startsWith( type.toString())) {
					result = type;
					break;
				}
			}
		}

		return result;
	}


	/**
	 * Determines whether a property is required.
	 * @param property a property
	 * @return true if it is required, false otherwise (false by default)
	 */
	public boolean isRequired( String property ) {

		boolean required = false;
		String typeDef = findTypeDeclaration( property );
		if( typeDef != null ) {
			for( String decl : typeDef.split( "," )) {
				if( decl.trim().endsWith( TYPE_REQUIRED )
						&& ! decl.contains( "}" )
						&& ! decl.contains( "{" )
						&& ! decl.contains( "[" )
						&& ! decl.contains( "]" )) {
					required = true;
					break;
				}
			}
		}

		return required;
	}


	/**
	 * Determines whether a property is nullable.
	 * @param property a property
	 * @return true if it is nullable, false otherwise (false by default)
	 */
	public boolean isNullable( String property ) {

		boolean nullable = false;
		String typeDef = findTypeDeclaration( property );
		if( typeDef != null )
			nullable = typeDef.endsWith( TYPE_NULLABLE );

		return nullable;
	}


	/**
	 * Checks the type definition of a property is valid.
	 * @return true if the syntax is correct, false otherwise
	 */
	public boolean isTypeDefinitionValid( String property ) {

		boolean valid = true;
		String typeDef = findTypeDeclaration( property );
		if( typeDef != null )
			valid = checkTypePattern( typeDef );

		return valid;
	}


	/**
	 * Gets the documentation associated with a property
	 * @param property a property
	 * @return a non-null string
	 */
	public String getDocumentation( String property ) {

		StringBuilder sb = new StringBuilder();
		for( String comment : this.properties.getComments( property )) {
			comment = comment.replaceAll( "#", "" ).trim();
			sb.append( comment + "\n" );
		}

		return sb.toString().trim();
	}


	/**
	 * Gets the value of a "list" property as a list of strings.
	 * @param property a property
	 * @return null if the property is not of type LIST, a list otherwise
	 */
	public List<String> getValueAsList( String property ) {

		List<String> result = null;
		if( getType( property ) == SupportedTypes.LIST ) {
			result = new ArrayList<String> ();
			for( String s : this.properties.get( property ).split( "\\|" ))
				result.add( s.trim());
		}

		return result;
	}


	/**
	 * @param property a property
	 * @return true if the property was defined in the model, false otherwise
	 */
	public boolean isValidProperty( String property ) {
		return this.properties.containsKey( property );
	}


	/**
	 * Validates the hold properties.
	 * @return null if no error was found, an error message otherwise
	 */
	public String validatePropertyValue( String property ) {

		String result = null;
		String value = this.properties.get( property );
		if( value == null ) {
			if( isRequired( property )
					&& ! isNullable( property ))
				result = "Property " + property + " must be set.";

		} else {
			value = value.trim();
			switch( getType( property )) {
			case BOOLEAN:
				if( ! value.equalsIgnoreCase( "true" )
						&& ! value.equalsIgnoreCase( "false" ))
					result = "Property " + property + " must be a boolean.";
				break;

			case DOUBLE:
				try {
					Double.valueOf( value );
				} catch( NumberFormatException e ) {
					result = "Property " + property + " must be a double.";
				}
				break;

			case FLOAT:
				try {
					Float.valueOf( value );
				} catch( NumberFormatException e ) {
					result = "Property " + property + " must be a float.";
				}
				break;

			case INTEGER:
				try {
					int t = Integer.valueOf( value );
					IntegerRange range = getRange( property );
					if( range != null ) {

						// Both min and max have not be set?
						if( range.getMin() == -1 && range.getMax() == -1 ) {
							result = "Property " + property + " defines an invalid range.";
						}

						// Min must be less than Max
						else if( range.getMin() >= range.getMax() && range.getMax() != -1 ) {
							result = "Property " + property + " defines an invalid range. 'Min' must be less than 'max'.";
						}

						// Check the min?
						else if( range.getMin() != -1 ) {
							if( range.isMinIncluded() && t < range.getMin())
								result = "Property " + property + " must be greater or equal than " + range.getMin() + ".";
							else if( ! range.isMinIncluded() && t <= range.getMin())
								result = "Property " + property + " must be strictly greater than " + range.getMin() + ".";
						}

						// Check the max?
						else if( range.getMax() != -1 ) {
							if( range.isMaxIncluded() && t > range.getMax())
								result = "Property " + property + " must be less or equal than " + range.getMax() + ".";
							else if( ! range.isMaxIncluded() && t >= range.getMax())
								result = "Property " + property + " must be strictly less than " + range.getMax() + ".";
						}
					}

				} catch( NumberFormatException e ) {
					result = "Property " + property + " must be an integer.";
				}
				break;

			case LONG:
				try {
					Long.valueOf( value );
				} catch( NumberFormatException e ) {
					result = "Property " + property + " must be a long.";
				}
				break;

			case STRING:
			case LIST:
				// nothing
				break;

			case ENUMERATION:
				Collection<String> items = getEnumeration( property );
				if( items != null && ! items.contains( value ))
					result = "Property " + property + " has an invalid value (not in the enumeration).";
				break;
			}
		}

		return result;
	}


	/**
	 * Validates the model (properties, types definition and check the specified values, if any).
	 * @return a map (key: property names, or "" for the whole file ; value : a list of error messages)
	 */
	public Map<String,List<String>> validateAbstractModel() {

		Map<String,List<String>> result = new HashMap<String,List<String>> ();
		if( ! this.properties.containsKey( PROPERTY_VERSION )) {
			recordEntry( result, "", "The property " + PROPERTY_VERSION + " was not found." );

		} else {
			for( String property : this.properties.getPropertyMap().keySet()) {
				String s;
				if( ! property.toLowerCase().equals( property ))
					recordEntry( result, property, "The property " + PROPERTY_VERSION + " must be in lower case." );

				else if( ! isTypeDefinitionValid( property ))
					recordEntry( result, property, "The type definition of the property " + PROPERTY_VERSION + " is invalid." );

				else if( ! Utils.isEmpty( this.properties.get( property ))
						&& ( s = validatePropertyValue( property )) != null )
					recordEntry( result, property, s );
			}
		}

		return result;
	}


	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return this.properties;
	}


	/**
	 * Finds the type declaration for a property.
	 * @param property a property
	 * @return the type declaration, or null if the property was not found or the type not declared
	 * <p>
	 * If not null, the result is trimmed and set in lower case.
	 * </p>
	 */
	public String findTypeDeclaration( String property ) {

		String result = null;
		List<String> comments = this.properties.getComments( property );
		for( String comment : comments ) {

			comment = comment.replaceAll( "^#{1,}\\s*", "" );
			if( ! comment.startsWith( TYPE_PREFIX ))
				continue;

			result = comment.trim().toLowerCase();
			break;
		}

		return result;
	}


	/**
	 * Checks that a type definition is valid.
	 * @param s the string to check
	 * @return true if it is valid, false otherwise
	 */
	public static boolean checkTypePattern( String s ) {
		return Pattern.compile( TYPE_PATTERN, Pattern.CASE_INSENSITIVE ).matcher( s ).matches();
	}


	/**
	 * Creates an instance without throwing any exception.
	 * @param propertiesFile a properties file
	 * @return a model instance, or null if it failed
	 */
	public static AbstractModel create( File propertiesFile ) {
		AbstractModel result = null;
		try {
			result = new AbstractModel( propertiesFile );

		} catch( IOException e ) {
			// nothing
		}

		return result;
	}


	/**
	 * Creates an instance without throwing any exception.
	 * @param inputStream an input stream (not closed by this method)
	 * @return a model instance, or null if it failed
	 */
	public static AbstractModel create( InputStream inputStream ) {
		AbstractModel result = null;
		try {
			result = new AbstractModel( inputStream );

		} catch( IOException e ) {
			// nothing
		}

		return result;
	}


	/**
	 * Records a validation entry in a map.
	 * @param map a map associating properties and error messages
	 * @param key a property name, or the empty string to indicate the whole model
	 * @param msg an error message
	 */
	private void recordEntry( Map<String,List<String>> map, String key, String msg ) {

		List<String> messages = map.get( key );
		if( messages == null )
			messages = new ArrayList<String> ();

		messages.add( msg );
		map.put( key, messages );
	}
}
