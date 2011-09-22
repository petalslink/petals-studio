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

package com.ebmwebsourcing.commons.jbi.internal.provisional.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A set of utility methods to format names.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JbiNameFormatter {

	/** Constant indicating that Petals is expected to generate an end-point name (provide only). */
	public static final String PETALS_AUTO_GENERATE_ENDPOINT = "autogenerate"; //$NON-NLS-1$


	/**
	 * Create a SU name from a service name.
	 * This method does not add, check or modify file extensions.
	 * 
	 * @param suType the component used by the SU (e.g. FTP, XSLT...).
	 * @param serviceName the service name.
	 * @param isConsume true if the SU is <i>consume</i> mode, false for <i>provide</i>.
	 * @return the generated SU name.
	 */
	public static String createSuName( String suType, String serviceName, boolean isConsume ) {
		String serviceNameRewritten = removeNamespaceElements( serviceName );
		String suMode = ( isConsume ) ? "consume" : "provide";
		return "su-" + suType + "-" + serviceNameRewritten + "-" + suMode;
	}

	/**
	 * Create a SA name from a service name.
	 * This method does not add, check or modify file extensions.
	 * 
	 * @param suType the component used by the SU (e.g. FTP, XSLT...).
	 * @param serviceName the service name.
	 * @param isConsume true if the SU is <i>consume</i> mode, false for <i>provide</i>.
	 * @return the generated SU name.
	 */
	public static String createSaName( String suType, String serviceName, boolean isConsume ) {
		String serviceNameRewritten = removeNamespaceElements( serviceName );
		String suMode = ( isConsume ) ? "consume" : "provide";
		return "sa-" + suType + "-" + serviceNameRewritten + "-" + suMode;
	}

	/**
	 * Create a SA name from a SU name.
	 * This method does not add, check or modify file extensions.
	 * 
	 * @param suName a service unit archive name.
	 * @return the generated SU name.
	 */
	public static String createSaName( String suName ) {
		if( suName == null )
			return "";

		if( suName.startsWith( "su-" ))
			return suName.replaceFirst( "su-", "sa-" );

		return "sa-" + suName;
	}

	/**
	 * Remove the name space elements from a string.
	 * Name space elements are name space URLs between curly brackets or name space prefixes.
	 * If the string is null, it returns the empty string.
	 * 
	 * @param name the name whose name space elements should be removed.
	 * @return the substring without name space URL and prefix.
	 */
	public static String removeNamespaceElements( String name ) {
		if( name == null )
			return "";

		// Remove name space (written between '{' and '}').
		Pattern namespacePattern = Pattern.compile("\\{.+\\}");
		Matcher m = namespacePattern.matcher( name );
		if( name.contains( "{" ) && !m.find())
			return "";

		int namespaceEndPosition = name.lastIndexOf( "}" );
		if( namespaceEndPosition > 0 )
			name = name.substring( namespaceEndPosition + 1 );

		// Remove name space prefix.
		String[] splits = name.split( ":" );
		if( splits.length == 1 )
			return name.trim();

		// In any other case, return the string in last position.
		return splits[ splits.length - 1 ].trim();
	}

	/**
	 * Extract the name space URI from a name space�given as a string.
	 * Example: <b>{http://namespace.uri}</b> will return <b>http://namespace.uri</b>
	 * @param namespace
	 * @return the name space URI, or the empty string if no URI was found
	 */
	public static String extractNamespaceUri( String namespace ) {
		if( namespace == null )
			return "";

		String namespaceCopy = namespace;
		Pattern namespacePattern = Pattern.compile("\\{.+\\}"); //$NON-NLS-1$
		Matcher m = namespacePattern.matcher( namespaceCopy );
		if( !m.find())
			return "";

		namespaceCopy = namespaceCopy.substring( m.start(), m.end());

		if( namespaceCopy.startsWith( "{" )) //$NON-NLS-1$
			namespaceCopy = namespaceCopy.substring( 1 );
		if( namespaceCopy.endsWith( "}" )) //$NON-NLS-1$
			namespaceCopy = namespaceCopy.substring( 0, namespaceCopy.length() - 1 );

		return namespaceCopy.trim();
	}

	/**
	 * Remove the file extension from a file name.
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
	 * Insert a suffix between the file name and its extension.
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
	 * 
	 * @param nsDeclaration
	 * @return
	 */
	public static String getNsPrefixFromNsDeclaration( String nsDeclaration ) {
		NsDeclarationElements decl = extractNsDeclarationElements( nsDeclaration );
		return decl != null ? decl.getPrefix() : null;
	}

	/**
	 * 
	 * @param nsDeclaration
	 * @return
	 */
	public static String getNsUrlFromNsDeclaration( String nsDeclaration ) {
		NsDeclarationElements decl = extractNsDeclarationElements( nsDeclaration );
		return decl != null ? decl.getUrl() : null;
	}

	/**
	 * @return a bean containing elements relative to a name space declaration, or
	 * null if the argument does not match with the regular expression describing a
	 * name space declaration.
	 * 
	 * The regular expression describing a name space declaration
	 * is "xmlns(\\:[-_\\w]+)?\\=\"[-.:/_\\w]+\"".
	 */
	private static NsDeclarationElements extractNsDeclarationElements( String nsDeclaration ) {
		if( nsDeclaration == null )
			return null;

		Pattern nsDeclarationPattern =
			Pattern.compile( "xmlns(\\:[-_\\w]+)?\\=\"[-.:/_\\w]+\"" ); //$NON-NLS-1$

		Matcher m = nsDeclarationPattern.matcher( nsDeclaration );
		if( !m.find())
			return null;

		NsDeclarationElements result = new NsDeclarationElements();
		String[] parts = nsDeclaration.split( "=" );
		result.url = parts[ 1 ].replace( "\"", "" );
		result.prefix = parts[ 0 ].replace( "xmlns", "" ).replace( ":", "" );

		return result;
	}

	/**
	 * A simple bean to store elements from a name space declaration.
	 */
	private static class NsDeclarationElements {
		private String prefix;
		private String url;
		/**
		 * @return the prefix
		 */
		public String getPrefix() {
			return this.prefix;
		}
		/**
		 * @return the url
		 */
		public String getUrl() {
			return this.url;
		}
	}
}
