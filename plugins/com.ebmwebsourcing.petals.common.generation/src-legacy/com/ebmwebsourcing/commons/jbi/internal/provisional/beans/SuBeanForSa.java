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

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import com.ebmwebsourcing.commons.jbi.internal.provisional.utils.JbiNameFormatter;

/**
 * A bean with Service Unit data used to generate jbi.xml files for a Service Assembly.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuBeanForSa {

	/** The service unit name. */
	protected String suName;
	/** The service unit description. */
	protected String description;
	/** The zip artifact name (the one containing the service unit). */
	protected String zipArtifact;
	/** The component name (the one <i>using</i> the service unit). */
	protected String componentName;


	/** Empty constructor. */
	public SuBeanForSa() {}

	/**
	 * Create a SuBeanForSa from a SuBean.
	 * <p>
	 * Initializes the component name.
	 * Generates the service unit name from the service name.
	 * The zip name is generated with {@link JbiNameFormatter#createSuName(String, String, boolean)}.
	 * The description is a default text message indicating the component name and the generation time.
	 * Other fields have to be filled in manually.
	 * </p>
	 * 
	 * @param suBean
	 */
	public SuBeanForSa( SuBean suBean ) {
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat( "MMMM d yyyy (H:m:s)" );

		this.suName = JbiNameFormatter.removeNamespaceElements( suBean.getServiceName());
		if( "".equals( this.suName)) {
			SimpleDateFormat sdf2 = new SimpleDateFormat( "dd_MM_yyyy__H_m_s_S" );
			this.suName = "defaultSuName__" + sdf2.format( calendar.getTime());
		}

		this.componentName = suBean.getComponentName();
		this.zipArtifact =
			JbiNameFormatter.createSuName(
						suBean.getSuType(), suBean.getServiceName(), suBean.isConsume ()) + ".zip";
		this.description = 	"Service Unit for " + this.componentName +
		", generated on " + sdf.format( calendar.getTime());
	}

	/**
	 * @return the suName
	 */
	public String getSuName() {
		return this.suName;
	}
	/**
	 * @param suName the suName to set
	 */
	public void setSuName(String suName) {
		this.suName = suName;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription( String description ) {
		this.description = description;
	}
	/**
	 * @return the zipArtifact
	 */
	public String getZipArtifact() {
		return this.zipArtifact;
	}
	/**
	 * @param zipArtifact the zipArtifact to set
	 */
	public void setZipArtifact( String zipArtifact ) {
		this.zipArtifact = zipArtifact;
	}
	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return this.componentName;
	}
	/**
	 * @param componentName the componentName to set
	 */
	public void setComponentName( String componentName ) {
		this.componentName = componentName;
	}
}
