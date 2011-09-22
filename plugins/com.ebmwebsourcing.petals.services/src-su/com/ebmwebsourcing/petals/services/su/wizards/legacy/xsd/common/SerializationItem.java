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

package com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


/**
 * Serializable bean with every data required to write in the jbi.xml file.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SerializationItem implements Serializable {

	/** Serial ID. */
	private static final long serialVersionUID = 5042303879350201993L;

	/** The name to print in the XML file (special case: the GUI will result in the completion of an XML file). */
	protected String xsdName;

	/** The type of the widget to create. This type is abstract and will have to be "translated" by the client. */
	protected HciType type;

	/** The default value to display. */
	protected String defaultValue;

	/** True if the field is optional and might be "not filled". */
	protected boolean optional;

	/** If the field is empty, write <mark-up xs:nillable="true" /> in the xml file. */
	protected boolean nillable;

	/** The minimum and maximum occurrence of an element. */
	protected int minOccurs, maxOccurs;

	/** Values for enumerations. */
	protected List<String> values = new ArrayList<String> ();

	/** Annotations */
	protected Collection<DependencyAnnotation> annotations = new HashSet<DependencyAnnotation> ();



	/**
	 * @return the xsdName
	 */
	public String getXsdName() {
		return this.xsdName;
	}
	/**
	 * @return the type
	 */
	public HciType getType() {
		return this.type;
	}
	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return this.defaultValue;
	}
	/**
	 * @return the optional
	 */
	public boolean isOptional() {
		return this.optional;
	}
	/**
	 * @return the values
	 */
	public List<String> getValues() {
		return Collections.unmodifiableList( this.values );
	}
	/**
	 * @param xsdName the xsdName to set
	 */
	public void setXsdName(String xsdName) {
		this.xsdName = xsdName;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(HciType type) {
		this.type = type;
	}
	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	/**
	 * @param optional the optional to set
	 */
	public void setOptional(boolean optional) {
		this.optional = optional;
	}
	/**
	 * @param values the values to set
	 */
	public void setValues(List<String> values) {
		this.values = values;
	}
	/**
	 * @return the isNillable
	 */
	public boolean isNillable() {
		return this.nillable;
	}
	/**
	 * @param nillable the isNillable to set
	 */
	public void setNillable(boolean nillable) {
		this.nillable = nillable;
	}
	/**
	 * @return the minOccurs
	 */
	public int getMinOccurs() {
		return this.minOccurs;
	}
	/**
	 * @param minOccurs the minOccurs to set
	 */
	public void setMinOccurs(int minOccurs) {
		this.minOccurs = minOccurs;
	}
	/**
	 * @return the maxOccurs
	 */
	public int getMaxOccurs() {
		return this.maxOccurs;
	}
	/**
	 * @param maxOccurs the maxOccurs to set
	 */
	public void setMaxOccurs(int maxOccurs) {
		this.maxOccurs = maxOccurs;
	}
	/**
	 * @return the annotations
	 */
	public Collection<DependencyAnnotation> getAnnotations() {
		return this.annotations;
	}
	/**
	 * @param annotations the annotations to set
	 */
	public void addAnnotations( Collection<DependencyAnnotation> annotations ) {
		this.annotations.addAll( annotations );
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object o ) {

		boolean equals = false;
		if( o != null && o instanceof SerializationItem ) {
			SerializationItem item = (SerializationItem) o;
			if( this.xsdName == null )
				equals = item.xsdName == null;
			else
				equals = this.xsdName.equals( item.xsdName );
		}

		return equals;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (this.xsdName == null) ? 17 : this.xsdName.hashCode();
	}


	/**
	 * @return true only if the item is visible, i.e. if it does not own the annotation PetalsXsdAnnotations.NO_HCI
	 */
	public boolean isVisible() {
		return ! PetalsXsdAnnotations.containsNoHciAnnotation( this.annotations );
	}


	/**
	 * Make an element visible or invislbe by adding / removing the corresponding annotation.
	 * @param isVisible
	 */
	public void setVisible( boolean isVisible ) {
		if( isVisible )
			this.annotations.remove( new DependencyAnnotation( PetalsXsdAnnotations.NO_HCI, "" ));
		else
			this.annotations.add( new DependencyAnnotation( PetalsXsdAnnotations.NO_HCI, "" ));
	}
}
