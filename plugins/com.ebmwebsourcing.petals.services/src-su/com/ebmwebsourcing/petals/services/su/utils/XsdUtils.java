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

package com.ebmwebsourcing.petals.services.su.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlAttribute;
import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.HciItem;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.HciType;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.SerializationItem;

/**
 * Utility methods related to the XSD-based generation.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XsdUtils {

	/**
	 * @param items a list of HciItems.
	 * @return true only if all visible items are optional or if the argument is null.
	 */
	public static boolean allFieldsAreOptional( List<HciItem> items ) {
		if( items == null )
			return true;

		for( HciItem item : items ) {
			if( item.isVisible() && !item.isOptional())
				return false;
		}
		return true;
	}

	/**
	 * @param items a list of HciItems.
	 * @return true only if all visible items are optional or if the argument is null.
	 */
	public static boolean allFieldsAreOptionalOrNillable( List<HciItem> items ) {
		if( items == null )
			return true;

		for( HciItem item : items ) {
			if( item.isVisible() && !item.isOptional() && !item.isNillable())
				return false;
		}
		return true;
	}

	/**
	 * @param items a list of HciItems.
	 * @return true only if all the items are not visible.
	 */
	public static boolean allFieldAreNotVisible( List<HciItem> items ) {
		if( items == null )
			return true;

		for( HciItem item : items ) {
			if( item.isVisible())
				return false;
		}
		return true;
	}

	/**
	 * Convert values retrieved by an XsdAbstractSuPage into an XML structure for <b>commons-jbi</b>.
	 * 
	 * @param xmlMap the XSD-based mapping (key = XSD serialization item, value = item value).
	 * @param fileImportManager
	 * @return a list of XML elements to write in a jbi.xml file.
	 */
	public static List<XmlElement> createXmlStructure(
				LinkedHashMap<SerializationItem, String> xmlMap, FileImportManager fileImportManager ) {

		List<XmlElement> xmlElements = new ArrayList<XmlElement> ();
		if( xmlMap.isEmpty())
			return xmlElements;

		Map<String, SerializationItem> excludedKeys = new HashMap<String, SerializationItem> ();
		Map<String, List<XmlAttribute>> attributes = new HashMap<String, List<XmlAttribute>> ();

		// ATTRIBUTES
		// Attributes are "specifics" values whose xsdName is made up of two words (element + attr. name).
		for( Map.Entry<SerializationItem, String> entry : xmlMap.entrySet()) {

			SerializationItem key = entry.getKey();
			if( key == null )
				continue;

			String[] parts = key.getXsdName().split( " " ); //$NON-NLS-1$
			if( parts.length == 2 ) {
				excludedKeys.put( parts[0], entry.getKey());

				List<XmlAttribute> attributesForThisElement = attributes.get( parts[ 0 ]);
				if( attributesForThisElement == null )
					attributesForThisElement = new ArrayList<XmlAttribute> ();

				XmlAttribute attribute = new XmlAttribute();
				attribute.setName( parts[ 1 ] );
				attribute.setValue( entry.getValue());
				// TODO: check default settings for attributes - required.

				if( key.getType() == HciType.FILE ) {
					fileImportManager.registerXmlFileAttribute( attribute, attribute.getValue(), "" );
				}

				attributesForThisElement.add( attribute );
				attributes.put( parts[ 0 ], attributesForThisElement );
			}
		}

		// OTHER ELEMENTS
		for( Map.Entry<SerializationItem, String> entry : xmlMap.entrySet()) {
			if( entry.getKey() == null
						|| excludedKeys.containsValue( entry.getKey())
						|| entry.getKey().getXsdName().equals( "" )) //$NON-NLS-1$
				continue;

			if( entry.getKey().getMaxOccurs() > 1 ) {
				List<XmlElement> elements =
					createMultipleXmlElements( entry.getKey(), entry.getValue(), fileImportManager );
				xmlElements.addAll( elements );
			}
			else {
				XmlElement xmlElement =
					createSimpleXmlElement( entry.getKey(), entry.getValue(), attributes, fileImportManager );
				xmlElements.add( xmlElement );
			}
		}

		return xmlElements;
	}


	/**
	 * Generate simple mark-ups for the version 4.0 of the CDK.
	 * e.g.
	 * 		<param name="toto">value1</param>
	 * 
	 * @param key the SerializableItem to serialize (mark-up data).
	 * @param value the value associated with the key (mark-up value).
	 * @param attributes a map associating a XSD serialization item name (key)
	 * with a list of XML attributes (<b>commons-jbi</b>).
	 * @param fileImportManager
	 * 
	 * @return the XML element corresponding to the given item and value.
	 */
	public static XmlElement createSimpleXmlElement(
				SerializationItem key, String value,
				Map<String, List<XmlAttribute>> attributes,
				FileImportManager fileImportManager ) {

		XmlElement element = new XmlElement();
		element.setName( key.getXsdName());
		element.setNillable( key.isNillable());
		element.setOptional( key.isOptional());
		element.setValue( value );
		element.setDefaultValue( key.getDefaultValue());

		if( key.getType() == HciType.FILE )
			fileImportManager.registerXmlFileElement( element, element.getValue(), "" );

		if( attributes == null || attributes.isEmpty())
			return element;

		List<XmlAttribute> elementAttributes = attributes.get( key.getXsdName());
		if( elementAttributes != null )
			element.attributes.addAll(elementAttributes );

		return element;
	}

	/**
	 * Generate several mark-ups with name same (version 4.0 of the CDK).
	 * e.g.
	 * 		<param name="toto">value1</param>
	 * 		<param name="toto">value1</param>
	 * 		...
	 * 
	 * @param key the SerializableItem to serialize (mark-up data).
	 * @param value the value associated with the key (mark-up value).
	 * @param fileImportManager
	 * 
	 * @return the list of XML elements corresponding to the given item and values.
	 */
	public static List<XmlElement>
	createMultipleXmlElements( SerializationItem key, String value, FileImportManager fileImportManager ) {

		// Element values are separated by two semi-colons.
		String[] parts = (( value == null ) ? "" : value ).split( ";;" ); //$NON-NLS-1$ //$NON-NLS-2$
		List<String> validValues = new ArrayList<String> ();
		for( String string : parts ) {
			string = string.trim();
			if( !"".equals( string )) //$NON-NLS-1$
				validValues.add( string );
		}

		// If there is no valid value, add "" so that it could be updated later.
		// See the EIP plug-in for that (fillInData).
		if( validValues.isEmpty())
			validValues.add( "" );

		// Build elements.
		List<XmlElement> xmlElements = new ArrayList<XmlElement>();
		for( String validValue : validValues ) {
			XmlElement xmlElement = createSimpleXmlElement( key, validValue, null, fileImportManager );
			xmlElements.add( xmlElement );
		}

		return xmlElements;
	}

	/**
	 * Create a list of XmlElement with the same properties than the first argument
	 * and having the values given in the second argument.
	 * 
	 * @param xmlElement the XML element to mimic.
	 * @param value the values to split and to give to each element.
	 * @param fileImportManager
	 * @return the list of XML elements corresponding to the given item and values.
	 */
	public static List<XmlElement>
	createMultipleXmlElements( XmlElement xmlElement, String value, FileImportManager fileImportManager ) {

		SerializationItem item = new SerializationItem();
		item.setNillable( xmlElement.isNillable());
		item.setOptional( xmlElement.isOptional());
		item.setXsdName( xmlElement.getName());

		return createMultipleXmlElements( item, value, fileImportManager );
	}

	/**
	 * Create an of XmlElement with the same properties than the first argument
	 * and having the values given in the second argument.
	 * 
	 * @param xmlElement the XML element to mimic.
	 * @param value the value to give to the element.
	 * @param fileImportManager
	 * @return the XML element corresponding to the given item and value.
	 */
	public static XmlElement createSimpleXmlElement( XmlElement xmlElement, String value, FileImportManager fileImportManager ) {

		SerializationItem item = new SerializationItem();
		item.setNillable( xmlElement.isNillable());
		item.setOptional( xmlElement.isOptional());
		item.setXsdName( xmlElement.getName());

		return createSimpleXmlElement( item, value, null, fileImportManager );
	}

	/**
	 * Find the XmlElements from their name (includes the name space prefix).
	 * The search is made in the list and in the children elements.
	 * 
	 * @param name
	 * @param elements
	 * @return the matching XmlElements as a non-null list.
	 */
	public static List<XmlElement> findXmlElement( String name, List<XmlElement> elements ) {
		List<XmlElement> matchingElements = new ArrayList<XmlElement> ();
		for( XmlElement xmlElement : elements ) {
			if( name.equals( xmlElement.getName()))
				matchingElements.add( xmlElement );

			matchingElements.addAll( findXmlElement( name, xmlElement.childrenElements ));
		}

		return matchingElements;
	}
}
