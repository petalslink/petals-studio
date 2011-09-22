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

/**
 * The bean defining an abstract HCI object.
 * <p>
 * This class can be serialized.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class HciItem extends SerializationItem implements Serializable {

	/** Serial ID. */
	private static final long serialVersionUID = -7647771912085446606L;

	/** The label name. Should be formatted by the client which displays it on screen. */
	private String labelName;

	/** The tool tip to display. Extracted from the documentation of Elements and Attributes. */
	private String tooltip;



	/** Empty constructor. */
	public HciItem() {
		// nothing
	}

	/**
	 * Constructor which creates a duplicate of the argument (list fields are not duplicated).
	 * @param hciItem
	 */
	public HciItem( HciItem hciItem ) {

		if( hciItem == null )
			return;

		this.defaultValue = hciItem.defaultValue;
		this.labelName = hciItem.labelName;
		this.optional = hciItem.optional;
		this.tooltip = hciItem.tooltip;
		this.type = hciItem.type;
		this.xsdName = hciItem.xsdName;
		this.values = hciItem.values;
		this.nillable = hciItem.nillable;
		this.minOccurs = hciItem.minOccurs;
		this.maxOccurs = hciItem.maxOccurs;
		this.annotations = hciItem.annotations;
	}

	/**
	 * @return the labelName
	 */
	public String getLabelName() {
		return this.labelName;
	}
	/**
	 * @return the tooltip
	 */
	public String getTooltip() {
		return this.tooltip;
	}
	/**
	 * @param labelName the labelName to set
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	/**
	 * @param tooltip the tooltip to set
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder result = new StringBuilder((this.labelName == null ? "" : this.labelName) + ": "
					+ (this.type == null ? "" : this.type.toString()) + " ("
					+ (this.defaultValue == null ? "" : this.defaultValue) + ")"
					+ ((this.nillable) ? " nillable" : "") //$NON-NLS-1$ //$NON-NLS-2$
					+ ((this.optional) ? " optional" : "")
					+ "\n\tTooltip: " + (this.tooltip == null ? "" : this.tooltip)
					+ "\n\tXsdName: " + (this.xsdName == null ? "" : this.xsdName));

		if( this.values != null && !this.values.isEmpty()) {
			result.append( "\n\tEnumeration Values: " ); //$NON-NLS-1$
			for( String value : this.values )
				result.append( "- " + value + " " ); //$NON-NLS-1$ //$NON-NLS-2$
		}

		result.append( "\n" );
		return result.toString();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.SerializationItem
	 * #equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object o ) {
		return super.equals( o );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.SerializationItem
	 * #hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
