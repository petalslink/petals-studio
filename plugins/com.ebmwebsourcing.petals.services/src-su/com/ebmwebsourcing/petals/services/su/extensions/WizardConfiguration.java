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

package com.ebmwebsourcing.petals.services.su.extensions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.HciItem;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class WizardConfiguration {

	protected final Map<String,String> wizardSettings = new HashMap<String,String>();
	protected final Map<String, String> additionalNamespaces = new HashMap<String, String> ();
	protected final List<XmlElement> additionalSpecificElements = new ArrayList<XmlElement> ();
	protected final List<XmlElement> additionalCdkElements = new ArrayList<XmlElement> ();

	private final List<HciItem> redefinedItems = new ArrayList<HciItem> ();
	public final Map<HciItem, String> itemValues = new HashMap<HciItem, String> ();



	/**
	 * Add or redefine an XsdItem (an element got from XSD files).
	 * 
	 * @param xsdId the element ID in the XSD (typically <i>tns:elementName</i>)
	 * @param displayedLabel the label to display (null to keep the default one)
	 * @param toolTip the tool tip (null to keep the default one)
	 * @param defaultValue the initial value to put for this element (null to keep the default value)
	 * @param isVisible true to make this element visible in the wizard, false otherwise
	 * @param values the new values if it is an enumeration (null to keep the default values)
	 */
	public void addHciItem(
				String xsdId, String displayedLabel, String toolTip,
				String defaultValue, boolean isVisible, String... values ) {

		HciItem item = new HciItem();
		item.setXsdName( xsdId );
		item.setVisible( isVisible );

		if( displayedLabel != null )
			item.setLabelName( displayedLabel );

		if( toolTip != null )
			item.setTooltip( toolTip );

		if( defaultValue != null )
			item.setDefaultValue( defaultValue );

		if( values != null )
			item.setValues( Arrays.asList( values ));

		this.redefinedItems.add( item );
	}


	/**
	 * @return the redefinedItems
	 */
	public List<HciItem> getRedefinedItems() {
		return Collections.unmodifiableList( this.redefinedItems );
	}


	/**
	 * @return the wizardSettings
	 */
	public Map<String, String> getWizardSettings() {
		return this.wizardSettings;
	}


	/**
	 * @return the additionalNamespaces
	 */
	public Map<String, String> getAdditionalNamespaces() {
		return this.additionalNamespaces;
	}


	/**
	 * @return the additionalSpecificElements
	 */
	public List<XmlElement> getAdditionalSpecificElements() {
		return this.additionalSpecificElements;
	}


	/**
	 * @return the additionalCdkElements
	 */
	public List<XmlElement> getAdditionalCdkElements() {
		return this.additionalCdkElements;
	}
}
