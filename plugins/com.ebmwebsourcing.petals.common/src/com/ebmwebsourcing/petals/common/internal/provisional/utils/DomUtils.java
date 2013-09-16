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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class DomUtils {

	/**
	 * @param prefix
	 * @param node
	 * @return the name space URI associated with prefix and visible from node.
	 */
	public static String lookupNamespaceURI( String prefix, Node node ) {
		return getNamespaceMappings( node ).get( prefix );
	}


	/**
	 * Get all the name spaces visible from this node.
	 * <p>
	 * This method was added because the DOM implementation of the Eclipse
	 * XML editor does not handle this correctly.
	 * </p>
	 * @param node
	 * @return
	 */
	public static Map<String, String> getNamespaceMappings( Node node ) {

		Map<String, String> namespaces = new HashMap<String, String> ();
		boolean firstLoop = true;
		Node n = node;
		while( n != null ) {

			// Get default name space
			if( firstLoop && n.getNamespaceURI() != null ) {
				namespaces.put( "", n.getNamespaceURI());
				firstLoop = false;
			}

			// Get other name spaces
			if( n.getAttributes() != null ) {
				for( int i=0; i<n.getAttributes().getLength(); i++ ) {
					String attrName = n.getAttributes().item( i ).getNodeName();

					if( attrName.startsWith( "xmlns" )) { //$NON-NLS-1$
						String prefix = "";
						int index = attrName.indexOf( ':' );
						if( index != -1 && ++ index < attrName.length())
							prefix = attrName.substring( index );

						String uri = n.getAttributes().item( i ).getNodeValue();
						namespaces.put( prefix, uri );
					}
				}
			}

			// Go on with the parent
			n = n.getParentNode();
		}

		return namespaces;
	}


	/**
	 * @param node
	 * @return the name of the XML node (with no name space element)
	 */
	public static String getNodeName( Node node ) {

		String name = node.getNamespaceURI() != null ? node.getLocalName() : node.getNodeName();
		if( name.contains( ":" )) { //$NON-NLS-1$
			String[] parts = name.split( ":" ); //$NON-NLS-1$
			name = parts[ parts.length - 1 ];
		}

		return name;
	}


	/**
	 * @param namespaceURI
	 * @param node
	 * @return the prefix associated with the given namespace URI, null if not found
	 */
	public static String lookupNamespacePrefix( String namespaceURI, Node node ) {

		for( Map.Entry<String, String> entry : getNamespaceMappings( node ).entrySet()) {
			if( entry.getValue().equals( namespaceURI ))
				return entry.getKey();
		}

		return null;
	}


	/**
	 * Moves an element among its <i>brothers</i> (other elements, not nodes).
	 * @param element the element to move
	 * @param moveBefore true to move the element before (decrease its position), false to move it after (increase its position)
	 */
	public static void moveElementOrder( Element element, boolean moveBefore ) {

		Node parentNode = element.getParentNode();
		if( moveBefore ) {
			Element elementBefore = getElement( element, true );
			if( elementBefore != null ) {
				parentNode.removeChild( element );
				parentNode.insertBefore( element, elementBefore );
			}
		}
		else {
			Element elementAfter = getElement( element, false );
			if( elementAfter != null ) {
				Element elementAfterAfter = getElement( elementAfter, false );
				parentNode.removeChild( element );
				parentNode.insertBefore( element, elementAfterAfter );
			}
		}
	}


	/**
	 * @param element
	 * @param before true to search the element before, false for the element after
	 * @return the element before <i>element</i>
	 */
	public static Element getElement( Element element, boolean before ) {

		NodeList bas = element.getParentNode().getChildNodes();
		Element searchedElement = null;

		boolean found = false;
		for( int i=0; i<bas.getLength() && (searchedElement == null || ! found); i++ ) {

			if( element.equals( bas.item( i )))
				found = true;

			else if( bas.item( i ) instanceof Element ) {

				// We look for the element before - we didn't find the right element yet
				if( before && ! found )
					searchedElement = (Element) bas.item( i );

				// We look for the element after - we have to find the right element first
				else if( ! before && found )
					searchedElement = (Element) bas.item( i );
			}
		}

		return searchedElement;
	}


	/**
	 * @param element
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	public static Attr addOrSetAttribute( Element element, String attributeName, String attributeValue ) {

		try {
			Attr attribute = element.getAttributeNode( attributeName );
			if( attribute != null
						&& attributeValue.equals( attribute.getValue()))
				return attribute;

			element.setAttribute( attributeName, attributeValue );
			return element.getAttributeNode( attributeName );

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return null;
	}


	/**
	 * @param element
	 */
	public static void removeElement( Element element ) {

		try {
			element.getParentNode().removeChild( element );

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}
	}


	/**
	 * @param parentElement
	 * @param newNodeName
	 * @param newNodeNamespaceUri
	 * @return
	 */
	public static Element addElement( Element parentElement, String newNodeName, String newNodeNamespaceUri ) {

		try {
			String prefix = DomUtils.lookupNamespacePrefix( newNodeNamespaceUri, parentElement );
			if( prefix != null && prefix.length() > 0 )
				prefix += ":";
			else
				prefix = "";

			Document doc = parentElement.getOwnerDocument();
			Text textBefore = doc.createTextNode( "\n\t\t" );
			parentElement.appendChild( textBefore );

			Element newChild = doc.createElementNS( newNodeNamespaceUri, prefix + newNodeName );
			parentElement.appendChild( newChild );

			Text textAfter = doc.createTextNode( "\n\t\t" );
			parentElement.appendChild( textAfter );

			return newChild;

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return null;
	}


	/**
	 * @param parentElement
	 * @param childNode
	 * @param insertionIndex
	 * @return
	 */
	public static boolean insertChildElement( Element parentElement, Node childNode, int insertionIndex ) {

		try {
			Node nodeAfter = parentElement.getChildNodes().item( insertionIndex );
			parentElement.insertBefore( childNode, nodeAfter );
			return true;

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return false;
	}


	/**
	 * @param parentElement
	 * @param childElementName
	 * @return the element if it exists, null otherwise
	 */
	public static Element getChildElement( Element parentElement, String childElementName ) {

		NodeList children = parentElement.getChildNodes();
		for( int i=0; i<children.getLength(); i++ ) {
			Node child = children.item( i );
			if( child.getNodeType() == Node.ELEMENT_NODE
						&& childElementName.equals( getNodeName( child )))
				return (Element) child;
		}

		return null;
	}


	/**
	 * Builds a document for the given file.
	 * <p>
	 * If the document is open in the Eclipse XML editor, then the DOM is reused.
	 * Otherwise, the DOM is created from the file...
	 * </p>
	 *
	 * @param file the file to load
	 * @return the document or null if it could not be loaded
	 */
	public static Document buildDocument( File file, boolean tryWithEclipse ) {

		Document doc = null;

		// Try to load the file from the XML editor
		IFile resFile = ResourceUtils.getIFile( file );
		if( resFile != null ) {
			IStructuredModel model = StructuredModelManager.getModelManager().getExistingModelForRead( resFile );
			if( model != null )
				doc = ((IDOMModel) model).getDocument();
		}

		// Otherwise, load the file
		if( doc == null ) {
			DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
			db.setNamespaceAware( true );
			try {
				doc = db.newDocumentBuilder().parse( file );

			} catch( SAXException e ) {
				PetalsCommonPlugin.log( e, IStatus.ERROR );

			} catch( IOException e ) {
				PetalsCommonPlugin.log( e, IStatus.ERROR );

			} catch( ParserConfigurationException e ) {
				PetalsCommonPlugin.log( e, IStatus.ERROR );
			}
		}

		return doc;
	}


	/**
	 * Builds a document for the given file.
	 * <p>
	 * Equivalent to <code><buildDocument( file,false );</code>.
	 * </p>
	 *
	 * @param file the file to load
	 * @return the document or null if it could not be loaded
	 */
	public static Document buildDocument( File file ) {
		return buildDocument( file, false );
	}


	/**
	 * Builds a document from a string.
	 * @param text the text to parse as a XML document
	 * @return the document or null if it could not be loaded
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static Document buildDocument( String text )
	throws SAXException, IOException, ParserConfigurationException {

		DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
		db.setNamespaceAware( true );
		return db.newDocumentBuilder().parse( new ByteArrayInputStream( text.getBytes()));
	}


	/**
	 * Builds a new document.
	 * @return the document or null if it could not be loaded
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static Document buildNewDocument()
	throws SAXException, IOException, ParserConfigurationException {

		DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
		db.setNamespaceAware( true );
		return db.newDocumentBuilder().newDocument();
	}


	/**
	 * Validates if a text represents a valid XML document.
	 * <p>
	 * If the XML text does not start with <code><?xml </code>, then a default instruction is added.
	 * </p>
	 *
	 * @param text the text to parse
	 * @return true if it could be parsed as a XML document, false otherwise
	 */
	public static boolean isValidXmlDocument( String text ) {

		boolean result = false;
		StringBuilder sb = new StringBuilder();
		sb.append( text );
		if( ! text.startsWith( "<?xml " ))
			sb.insert( 0, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );

		try {
			result = buildDocument( sb.toString()) != null;

		} catch( SAXException e ) {
			// nothing

		} catch( IOException e ) {
			// nothing

		} catch( ParserConfigurationException e ) {
			// nothing
		}

		return result;
	}


	/**
	 * Writes a document as a string.
	 * @param doc the document
	 * @return the written document, or null if the conversion failed
	 */
	public static String writeDocument( Document doc ) {
		return writeDocument( doc, false );
	}


	/**
	 * Writes a document as a string.
	 * @param doc the document
	 * @param omitXmlDeclaration true to omit the XML declaration
	 * @return the written document, or null if the conversion failed
	 */
	public static String writeDocument( Document doc, boolean omitXmlDeclaration ) {

		String result = null;
		DOMSource domSource = new DOMSource( doc );
		StringWriter writer = new StringWriter();
		StreamResult streamResult = new StreamResult( writer );

		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute( "indent-number", 4 );

			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
			transformer.setOutputProperty( OutputKeys.STANDALONE, "yes" );
			if( omitXmlDeclaration )
				transformer.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "yes" );

			transformer.transform( domSource, streamResult );
			result = writer.toString();

		} catch( TransformerConfigurationException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );

		} catch( TransformerFactoryConfigurationError e ) {
			PetalsCommonPlugin.log( new Exception( e ), IStatus.ERROR );

		} catch( TransformerException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return result;
	}
}
