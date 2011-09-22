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

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class StringUtils {

	/**
	 * @param string
	 * @return true if the argument contains at least a null string or a strings made up of white spaces.
	 */
	public static boolean hasOneEmpty( String... string ) {

		for( String s : string ) {
			if( s == null || s.trim().length() == 0 )
				return true;
		}

		return false;
	}


	/**
	 * @param string
	 * @return true if the argument is only contains null strings or strings made up of white spaces.
	 */
	public static boolean areAllEmpty( String... string ) {

		int empty = 0;
		for( String s : string ) {
			if( s == null || s.trim().length() == 0 )
				empty ++;
		}

		return empty == string.length;
	}


	/**
	 * @param string
	 * @return true if the argument is null or is only made up of white spaces.
	 */
	public static boolean isEmpty( String string ) {
		return string == null || string.trim().length() == 0;
	}


	/**
	 * @param s1
	 * @param s2
	 * @return true either if both string are null or if they are equal
	 */
	public static boolean areEqual( String s1, String s2 ) {
		return s1 == null && s2 == null || s1 != null && s1.equals( s2 );
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
}
