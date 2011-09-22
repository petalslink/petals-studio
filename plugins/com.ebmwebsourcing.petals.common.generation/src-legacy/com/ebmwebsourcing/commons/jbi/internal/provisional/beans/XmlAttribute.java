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

/**
 * A bean used to store information about an XML attribute.
 * This class is strongly associated with {@link XmlElement}.
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XmlAttribute {
	/** The name of the attribute. */
	protected String name;
	/** The value of the attribute. */
	protected String value;
	/** Whether the attribute is optional or not. False by default. */
	protected boolean isOptional = false;


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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if( this.isOptional && ( this.value == null || "".equals( this.value )))
			return "";

		return this.name + "=\"" + this.value + "\"";
	}

	/**
	 * Return a list of attributes having the same attribute name.
	 * Each XmlAttribute contained in the list has the following properties:
	 * <ul>
	 * <li>The name given in parameter.</li>
	 * <li>The optional property given in parameter.</li>
	 * <li>One of the value (contained in values) as its value.</li>
	 * </ul>
	 * 
	 * @param attributeName the attribute name.
	 * @param isOptional whether this attribute is optional or not.
	 * @param values a list of values for this attribute.
	 * @return a list of XmlAttributes.
	 */
	public static List<XmlAttribute> createListOfAttributes( String attributeName, boolean isOptional, List<String> values ) {
		List<XmlAttribute> attributes = new ArrayList<XmlAttribute> ();

		for( String value : values ) {
			XmlAttribute attribute = new XmlAttribute();
			attribute.setName( attributeName );
			attribute.setOptional( isOptional );
			attribute.setValue( value );
			attributes.add( attribute );
		}

		return attributes;
	}
}
