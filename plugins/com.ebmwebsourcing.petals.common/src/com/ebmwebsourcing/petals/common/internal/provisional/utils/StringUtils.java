/******************************************************************************
 * Copyright (c) 2008-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public final class StringUtils {

	/**
	 * Private constructor for utility class.
	 */
	private StringUtils() {
		// nothing
	}


	/**
	 * @param string
	 * @return true if the argument is null or is only made up of white spaces.
	 */
	public static boolean isEmpty( String string ) {
		return string == null || string.trim().length() == 0;
	}


	/**
	 * Removes the file extension from a file name.
	 *
	 * @param fileName the file name.
	 * @return "" if the file name is null, the file name if it does not contain
	 * an extension or if it is a hidden file, the file name without the extension otherwise.
	 */
	public static String removeFileExtension( String fileName ) {
		if( fileName == null )
			return "";

		int index = fileName.lastIndexOf( '.' );
		if( index <= 0 )
			return fileName;

		return fileName.substring( 0, index );
	}


	/**
	 * Inserts a suffix between the file name and its extension.
	 *
	 * @param fileName the file name.
	 * @param suffix the suffix to insert between the file name and the file extension.
	 * @return suffix if the file name is null, the file name followed by suffix if it does
	 * not contain an extension or if it is a hidden file, the file name with the suffix
	 * at the right position otherwise.
	 */
	public static String insertSuffixBeforeFileExtension( String fileName, String suffix ) {
		if( fileName == null )
			return suffix;

		int index = fileName.lastIndexOf( '.' );
		if( index <= 0 )
			return fileName + suffix;

		return fileName.substring( 0, index ) + suffix + fileName.substring( index );
	}


	/**
	 * Turns a CamelCase String to a more human friendly string
	 * See TestStringUtils for specification.
	 * @param input
	 * @return
	 */
	public static String camelCaseToHuman(String input) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char currentChar = input.charAt(i);
			if (i == 0) {
				builder.append(Character.toUpperCase(currentChar));
			} else if (Character.isLowerCase(currentChar)) {
				builder.append(currentChar);
			} else if (Character.isUpperCase(currentChar)) {
				char previousChar = input.charAt(i - 1);
				if (Character.isUpperCase(previousChar)) { // most probably an acronym
					builder.append(currentChar);
					if (i < input.length() - 1) {
						char nextChar = input.charAt(i + 1);
						if (! Character.isUpperCase(nextChar)) { // current is last letter of acronym
							if (builder.charAt(builder.length() - 1) != ' ') {
								builder.append(' ');
							}
						}
					}
				} else if (! Character.isUpperCase(previousChar)) {
					if (builder.charAt(builder.length() - 1) != ' ') {
						builder.append(' ');
					}
					if (i == input.length() - 1) {
						builder.append(currentChar);
					} else {
						char nextChar = input.charAt(i + 1);
						if (Character.isUpperCase(nextChar)) { // most probably an acronym
							builder.append(currentChar);
						} else {
							builder.append(Character.toLowerCase(currentChar));
						}
					}
				}
			} else if (Character.isDigit(currentChar)) {
				char previousChar = input.charAt(i - 1);
				if (!Character.isDigit(previousChar)) { // first digit of a sequence
					if (builder.charAt(builder.length() - 1) != ' ') {
						builder.append(' ');
					}
				}
				builder.append(currentChar);
				if (i < input.length() - 1) {
					char nextChar = input.charAt(i + 1);
					if (!Character.isDigit(nextChar)) { // current is last digit of the sequence
						if (builder.charAt(builder.length() - 1) != ' ') {
							builder.append(' ');
						}
					}
				}
			} else {
				builder.append(currentChar);
			}
		}

		return builder.toString();
	}


	/**
	 * Capitalizes all the words of a string.
	 * @param s a string (can be null)
	 * @return the capitalized string, or null if the original string was null
	 */
	public static String capitalize( String s ) {

		if( s == null )
			return null;

		StringBuilder sb = new StringBuilder();
		for( String part : s.split( "\\s" )) {
			part = part.trim();
			if( part.length() == 0 )
				continue;

			if( part.length() == 1 )
				part = part.toUpperCase();
			else
				part = part.substring( 0, 1 ).toUpperCase() + part.substring( 1 ).toLowerCase();

			sb.append( part + " " );
		}

		return sb.toString().trim();
	}
}
