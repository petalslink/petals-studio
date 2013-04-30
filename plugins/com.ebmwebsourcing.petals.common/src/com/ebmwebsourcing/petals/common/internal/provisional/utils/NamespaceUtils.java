/****************************************************************************
 * 
 * Copyright (c) 2008-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ebmwebsourcing.petals.common.internal.provisional.sse.StructuredModelHelper;

/**
 * A set of utility methods to format names.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class NamespaceUtils {

	/**
	 * Verifies if a string is not null and is a shorten namespace.
	 * <p>
	 * <code>{http://petals.ow2.org}value</code> is an example of shorten namespace.
	 * </p>
	 * 
	 * @param s a string
	 * @return
	 */
	public static boolean isShortenNamespace( String s ) {
		return s != null && s.matches( "\\{[^}]+\\}[^{}]+" );
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
		Pattern namespacePattern = Pattern.compile( "\\{.+\\}" );
		Matcher m = namespacePattern.matcher( name );
		if( name.contains( "{" ) && ! m.find())
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
	 * Builds a QName object from a serialized QName.
	 * <p>
	 * Notice that this method does not check the validity of the parameter.
	 * If no name space URI was found, then only the local part will be used.
	 * </p>
	 * 
	 * @param qname something like {http://namespace/uri}localPart
	 * @return a QName
	 */
	public static QName buildQName( String qname ) {

		String nsUri = extractNamespaceUri( qname );
		String local = removeNamespaceElements( qname );

		return new QName( nsUri, local );
	}


	/**
	 * Gets the QName associated with an attribute.
	 * <p>
	 * <code>&lt;... interface-name="toto:itfName" xmlns:toto="http://lol.fr" ...&gt;</code>
	 * will return {http://lol.fr}itfName when this method is called with the attribute
	 * "interface-name".
	 * </p>
	 * 
	 * @param attribute the attribute
	 * @return the associated QName, or null if the attribute value does not have a valid syntax
	 */
	public static QName getQNameAttribute( Attr attribute ) {

		QName result = null;
		String value = attribute.getNodeValue().trim();
		String[] splits = value.split( ":" );

		if( splits.length == 1 ) {
			result = new QName( value );
		}
		else if( splits.length == 2 ) {
			String prefix = splits[ 0 ];
			String decl = splits[ 1 ];
			String url = DomUtils.lookupNamespaceURI( prefix, attribute.getOwnerElement());
			result = new QName( url, decl, prefix );
		}

		return result;
	}


	/**
	 * Gets the QName associated with an element.
	 * <p>
	 * <code>&lt;petalsCDK:operation xmlns:op="http://toto.fr"&gt;op:value&lt;&gt;</code>
	 * will return {http://toto.fr}value.
	 * </p>
	 * 
	 * @param element the element
	 * @return the associated QName, or null if the attribute value does not have a valid syntax
	 */
	public static QName getQNameElement( Element element ) {

		QName result = null;
		String value = StructuredModelHelper.getElementSimpleValue( element );
		String[] splits = value.split( ":" );

		if( splits.length == 1 ) {
			result = new QName( value );
		}
		else if( splits.length == 2 ) {
			String prefix = splits[ 0 ];
			String decl = splits[ 1 ];
			String url = DomUtils.lookupNamespaceURI( prefix, element );
			result = new QName( url, decl, prefix );
		}

		return result;
	}


	/**
	 * Get the name space URI from a QName value.
	 * <p>
	 * "{http://petals.ow2.org}value" will return "http://petals.ow2.org"<br />
	 * "petals:value", where the name space URI for the prefix "petals" is defined
	 * in or before the node using this value, will return the associated name space URI,
	 * or null if the name space declaration was not found.
	 * </p>
	 * <p>
	 * If qNameValue is null, then null is returned.
	 * </p>
	 * 
	 * @param qNameValue
	 * @param node
	 * @return
	 */
	public static String getNamespaceUri( String qNameValue, Node node ) {

		if( qNameValue == null )
			return null;

		String uri = extractNamespaceUri( qNameValue );
		if( uri.length() == 0 ) {
			int index = qNameValue.indexOf( ':' );
			if( index > 0 ) {
				String prefix = qNameValue.substring( 0, index );
				uri = DomUtils.lookupNamespaceURI( prefix, node );
			}
			else
				uri = null;
		}

		return uri;
	}


	/**
	 * Extract the name space URI from a name space given as a string.
	 * Example: <b>{http://namespace.uri}toto</b> will return <b>http://namespace.uri</b>
	 * @param namespace
	 * @return the name space URI, or the empty string if no URI was found
	 */
	public static String extractNamespaceUri( String namespace ) {

		if( namespace == null )
			return "";

		String namespaceCopy = namespace;
		Pattern namespacePattern = Pattern.compile("\\{.+\\}"); //$NON-NLS-1$
		Matcher m = namespacePattern.matcher( namespaceCopy );
		if( ! m.find())
			return "";

		namespaceCopy = namespaceCopy.substring( m.start(), m.end());

		if( namespaceCopy.startsWith( "{" )) //$NON-NLS-1$
			namespaceCopy = namespaceCopy.substring( 1 );
		if( namespaceCopy.endsWith( "}" )) //$NON-NLS-1$
			namespaceCopy = namespaceCopy.substring( 0, namespaceCopy.length() - 1 );

		return namespaceCopy.trim();
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
