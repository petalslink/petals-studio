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

package com.ebmwebsourcing.commons.jbi.internal.provisional.beans;

import java.util.ArrayList;
import java.util.List;

import com.ebmwebsourcing.commons.jbi.internal.provisional.utils.JbiNameFormatter;

/**
 * A bean used to describe an XML element.
 * An XML element has the following fields:
 * <ul>
 * <li>Name is the mark-up name. It includes the name space prefix.</li>
 * <li>Value is value given as a string, e.g. in <code>&lt;markupName&gt;value&lt;/markupName&gt;</code>.</li>
 * <li>IsNillable indicates whether the element is nillable (as defined in XSD files).</li>
 * <li>IsOptional indicates whether the element is optional (case where it is referenced by another element).</li>
 * <li>Attributes are the attributes of this element.</li>
 * <li>ChildrenElements are the XML children elements of this element.</li>
 * </ul>
 * 
 * An XML element either has a value or has children.
 * The client (the one using this class) should ensure that what he/she builds does not break this rule.
 * By default, XML children override the value (e.g. when you serialize an XML element with a value and XML children,
 * the value is not written while children are).
 * 
 * This class is used to store information about an XML element.
 * It is not intended to describe an XML structure or replace XSD files, or to replace JDOM or other XML libraries.
 * This class neither performs validation on the created elements. It is not what it is expected to do.
 * It is an XML structure used in the generation of jbi.xml files. It simply stores information.
 * Thus, do not expect this to be the <i>ultimate</i> XML library.
 * 
 * An XML tree can be built using this class with a <b>bottom-up approach</b>.
 * It means you have to define leafs first, then the nodes above the leafs, and so on... until you reach the root.
 * 
 * An XML tree can be written as a String by calling toString on the tree root.
 * It is the responsibility of the client (the one using this class) to project its data structure
 * and its information to this structure. In particular, you should not expect any utility to build such a structure.
 * You will have to do your own.
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XmlElement {

	/** The name of the element. */
	protected String name;
	/** The value of an element. If an XML element has a value, it should not have any XML child element. */
	protected String value;
	/** True if the element is nillable. False by default. */
	protected boolean isNillable = false;
	/** True if the element is optional (e.g. referenced by another element with minOccurs = "0"). False by default. */
	protected boolean isOptional = false;
	/** The default value of the element. */
	protected String defaultValue;

	/** A list of attributes of this element. */
	final public List<XmlAttribute> attributes = new ArrayList<XmlAttribute> ();
	/** A list of XML children elements. */
	final public List<XmlElement> childrenElements = new ArrayList<XmlElement> ();


	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return this.defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue( String value ) {
		this.value = value;
	}

	/**
	 * @return the isNillable
	 */
	public boolean isNillable() {
		return this.isNillable;
	}

	/**
	 * @param isNillable the isNillable to set. False by default.
	 */
	public void setNillable( boolean isNillable ) {
		this.isNillable = isNillable;
	}

	/**
	 * @return the isOptional
	 */
	public boolean isOptional() {
		return this.isOptional;
	}

	/**
	 * @param isOptional the isOptional to set. False by default.
	 */
	public void setOptional( boolean isOptional ) {
		this.isOptional = isOptional;
	}

	/**
	 * Equivalent to toString( 1 ).
	 * @see XmlElement#toString(int)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return toString( 1 );
	}

	/**
	 * Repeat <b>text</b> <b>repeat</b> times.
	 * @param text the text to repeat.
	 * @param repeat the number of times the text has to be repeated.
	 * @return the text after repetition.
	 */
	public static String repeatString( String text, int repeat ) {
		StringBuilder result = new StringBuilder();
		for( int i=0; i<repeat; i++ )
			result.append( text );
		return result.toString();
	}

	/**
	 * Return a list of elements having the same mark-up name.
	 * Each XmlElement contained in the list has the following properties:
	 * <ul>
	 * <li>The name given in parameter.</li>
	 * <li>The nillable property given in parameter.</li>
	 * <li>The optional property given in parameter.</li>
	 * <li>The attributes given in parameter.</li>
	 * <li>One of the value (contained in values) as its value.</li>
	 * </ul>
	 * 
	 * @param name the mark-up name with the name space prefix.
	 * @param isOptional whether this element is optional or not.
	 * @param isNillable whether this element is nillable or not.
	 * @param values a list of values for this element.
	 * @param attributes a list of Xml attributes common to all of these elements.
	 * @return a list of XmlElements.
	 */
	public static List<XmlElement> createListOfElements(
				String name, boolean isOptional, boolean isNillable,
				List<String> values, List<XmlAttribute> attributes ) {

		List<XmlElement> elements = new ArrayList<XmlElement> ();

		for( String value : values ) {
			XmlElement element = new XmlElement();
			element.setName( name );
			element.setOptional( isOptional );
			element.setNillable( isNillable );
			element.setValue( value );
			elements.add( element );
			if( attributes != null )
				element.attributes.addAll( attributes );
		}

		return elements;
	}


	/**
	 * Return a list of elements having the same mark-up name.
	 * Each XmlElement contained in the list has the following properties:
	 * <ul>
	 * <li>The name given in parameter.</li>
	 * <li>The nillable property given in parameter.</li>
	 * <li>The optional property given in parameter.</li>
	 * <li>The attributes given in parameter.</li>
	 * <li>One of the list of XmlElement (contained in childrenElements) as children.</li>
	 * </ul>
	 * 
	 * @param name the mark-up name with the name space prefix.
	 * @param childrenElements a list of lists of XmlElements.
	 * @param isOptional whether this element is optional or not.
	 * @param isNillable whether this element is nillable or not.
	 * @param attributes a list of Xml attributes common to all of these elements.
	 * @return a list of XmlElements.
	 */
	public static List<XmlElement> createListOfElements(
				String name, List<List<XmlElement>> childrenElements,
				boolean isOptional, boolean isNillable, List<XmlAttribute> attributes ) {

		List<XmlElement> elements = new ArrayList<XmlElement> ();

		for( List<XmlElement> children : childrenElements ) {
			XmlElement element = new XmlElement();
			element.setName( name );
			element.setOptional( isOptional );
			element.setNillable( isNillable );
			elements.add( element );

			if( children != null )
				element.childrenElements.addAll( children );
			if( attributes != null )
				element.attributes.addAll( attributes );
		}

		return elements;
	}

	/**
	 * Serialize an XML element with its attributes.
	 * This method recursively serializes XML children elements.
	 * 
	 * @param indentationLevel the number of tabulations before writing this element.
	 * @return the element serialized, containing its attributes and its XML children elements.
	 */
	public String toString( int indentationLevel ) {

		// Process children first is important (maybe all of them write nothing),
		// in which case it has consequences on the writing of this element.
		StringBuilder sb = new StringBuilder();
		for( XmlElement childElement : this.childrenElements )
			sb.append( childElement.toString( indentationLevel + 1 ));

		// Value is null <==> No value set and no children to write.
		String childrenResult = sb.toString();
		boolean valueIsNull = ( this.value == null || "".equals( this.value )) && "".equals( childrenResult );
		if( valueIsNull && !this.isNillable ) {
			return "";
		}

		// Optional => referencer element does not mandatory need it.
		if( this.isNillable && this.isOptional && valueIsNull ) {
			return "";
		}

		//		if( isOptional && !isNillable &&
		//				( value != null && value.equals( defaultValue )
		//						|| value == null && defaultValue == null ))
		//			return "";

		// Common beginning for all, write mark-up name and attributes.
		StringBuilder result = new StringBuilder( repeatString( "\t", indentationLevel ) + "<" + this.name );
		for( XmlAttribute attr : this.attributes )
			result.append( " " + attr.toString());

		// Nillable and no value.
		if( this.isNillable && valueIsNull ) {
			result.append( " xsi:nil=\"true\" />\n" );
			return result.toString();
		}

		// A value and no child.
		if( ! valueIsNull && "".equals( childrenResult )) {

			// Handle name spaces
			if( this.value.startsWith( "{" )) {
				String nsUri = JbiNameFormatter.extractNamespaceUri( this.value );
				if( nsUri.trim().length() > 0 ) {
					this.value = JbiNameFormatter.removeNamespaceElements( this.value );
					result.append( " xmlns:" + this.value + "Ns=\"" + nsUri + "\">" );
					result.append( this.value + "Ns:" );
				}
				else {
					result.append( ">" );
				}
			} else {
				result.append( ">" );
			}

			// Write it
			result.append( this.value + "</" + this.name + ">\n" );
			return result.toString();
		}

		// By default, write children and skip the value.
		result.append( ">\n" );
		result.append( childrenResult );
		result.append( repeatString( "\t", indentationLevel ) + "</" + this.name + ">\n" );
		return result.toString();
	}

	/**
	 * Serialize an XML element with its attributes with the old CDK style.
	 * This method recursively serializes XML children elements.
	 * <i>Old CDK style</i> means &lt;param elementName="value"...
	 * 
	 * @param indentationLevel the number of tabulations before writing this element.
	 * @return the element serialized, containing its attributes and its XML children elements.
	 * @deprecated
	 * Written only for the SCA component, waiting for it to move on the new CDK.
	 */
	@Deprecated
	public String toStringAsOldCdk( int indentationLevel ) {

		// Process children first is important (maybe all of them write nothing),
		// in which case it has consequences on the writing of this element.
		StringBuilder sb = new StringBuilder();
		for( XmlElement childElement : this.childrenElements )
			sb.append( childElement.toStringAsOldCdk( indentationLevel + 1 ));

		// Value is null <==> No value set and no children to write.
		String childrenResult = sb.toString();
		boolean valueIsNull = ( this.value == null || "".equals( this.value )) && "".equals( childrenResult );
		if( valueIsNull ) {
			return "";
		}

		// Common beginning for all, write mark-up name and attributes.
		String result = repeatString( "\t", indentationLevel )
		+ "<extensions:param name=\""
		+ JbiNameFormatter.removeNamespaceElements( this.name )
		+ "\"";	// Not very clean.
		for( XmlAttribute attr : this.attributes )
			result += " " + attr.toString();

		result += ">" + this.value + "</extensions:param>";
		return result;
	}
}
