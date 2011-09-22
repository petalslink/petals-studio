/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.sca.configuration.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The information to create a pre-defined composite.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ScaWizardBean {

	private String compositeName, compositeTns;
	private String packageName;
	private String serviceInterfaceName, serviceImplName;
	private final List<ScaWizardReferenceBean> references;


	/**
	 * Constructor.
	 */
	public ScaWizardBean() {
		this.references = new ArrayList<ScaWizardReferenceBean> ();
	}

	/**
	 * @return the compositeName
	 */
	public String getCompositeName() {
		return this.compositeName;
	}

	/**
	 * @param compositeName the compositeName to set
	 */
	public void setCompositeName( String compositeName ) {
		this.compositeName = compositeName;
	}

	/**
	 * @return the compositeTns
	 */
	public String getCompositeTns() {
		return this.compositeTns;
	}

	/**
	 * @param compositeTns the compositeTns to set
	 */
	public void setCompositeTns( String compositeTns ) {
		this.compositeTns = compositeTns;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return this.packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName( String packageName ) {
		this.packageName = packageName;
	}

	/**
	 * @return the serviceInterfaceName
	 */
	public String getServiceInterfaceName() {
		return this.serviceInterfaceName;
	}

	/**
	 * @param serviceInterfaceName the serviceInterfaceName to set
	 */
	public void setServiceInterfaceName( String serviceInterfaceName ) {
		this.serviceInterfaceName = serviceInterfaceName;
	}

	/**
	 * @return the serviceImplName
	 */
	public String getServiceImplName() {
		return this.serviceImplName;
	}

	/**
	 * @param serviceImplName the serviceImplName to set
	 */
	public void setServiceImplName( String serviceImplName ) {
		this.serviceImplName = serviceImplName;
	}

	/**
	 * @return the references
	 */
	public List<ScaWizardReferenceBean> getReferences() {
		return this.references;
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	public boolean addAllReferences( Collection<? extends ScaWizardReferenceBean> c ) {
		return this.references.addAll( c );
	}
}
