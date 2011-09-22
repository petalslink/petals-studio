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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A bean used to generate jbi.xml files for a Service Assembly.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SaBean {
	/** The service assembly name. */
	protected String saName;
	/** The service assembly description. */
	protected String description;
	/** A list of SuBeanForSa with the required data about the service units contained into this service assembly. */
	protected Collection<SuBeanForSa> sus = Collections.emptyList();


	/**
	 * @return the saName
	 */
	public String getSaName() {
		return this.saName;
	}
	/**
	 * @param saName the saName to set
	 */
	public void setSaName( String saName ) {
		this.saName = saName;
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
	 * @return the sus
	 */
	public Collection<SuBeanForSa> getSus() {
		return Collections.unmodifiableCollection( this.sus );
	}
	/**
	 * @param sus the sus to set
	 */
	public void setSus( List<SuBeanForSa> sus ) {
		this.sus = sus;
	}
}
