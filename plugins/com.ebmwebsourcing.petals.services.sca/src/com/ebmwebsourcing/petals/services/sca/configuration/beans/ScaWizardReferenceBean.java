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

/**
 * The information for a Petals (SCA) reference.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ScaWizardReferenceBean {

	private String referenceName;
	private String interfaceName, serviceName, endpointName;
	private String interfaceNamespace, serviceNamespace;
	private String wsdlLocation;
	private String interfaceClassName, simpleInterfaceClassName;

	/**
	 * @return the referenceName
	 */
	public String getReferenceName() {
		return this.referenceName;
	}

	/**
	 * @param referenceName the referenceName to set
	 */
	public void setReferenceName( String referenceName ) {
		this.referenceName = referenceName;
	}

	/**
	 * @return the interfaceName
	 */
	public String getInterfaceName() {
		return this.interfaceName;
	}

	/**
	 * @param interfaceName the interfaceName to set
	 */
	public void setInterfaceName( String interfaceName ) {
		this.interfaceName = interfaceName;
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return this.serviceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName( String serviceName ) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the endpointName
	 */
	public String getEndpointName() {
		return this.endpointName;
	}

	/**
	 * @param endpointName the endpointName to set
	 */
	public void setEndpointName( String endpointName ) {
		this.endpointName = endpointName;
	}

	/**
	 * @return the interfaceNamespace
	 */
	public String getInterfaceNamespace() {
		return this.interfaceNamespace;
	}

	/**
	 * @param interfaceNamespace the interfaceNamespace to set
	 */
	public void setInterfaceNamespace( String interfaceNamespace ) {
		this.interfaceNamespace = interfaceNamespace;
	}

	/**
	 * @return the serviceNamespace
	 */
	public String getServiceNamespace() {
		return this.serviceNamespace;
	}

	/**
	 * @param serviceNamespace the serviceNamespace to set
	 */
	public void setServiceNamespace( String serviceNamespace ) {
		this.serviceNamespace = serviceNamespace;
	}

	/**
	 * @return the wsdlLocation
	 */
	public String getWsdlLocation() {
		return this.wsdlLocation;
	}

	/**
	 * @param wsdlLocation the wsdlLocation to set
	 */
	public void setWsdlLocation( String wsdlLocation ) {
		this.wsdlLocation = wsdlLocation;
	}

	/**
	 * @return the interfaceClassName
	 */
	public String getInterfaceClassName() {
		return this.interfaceClassName;
	}

	/**
	 * @param interfaceClassName the interfaceClassName to set
	 */
	public void setInterfaceClassName( String interfaceClassName ) {
		this.interfaceClassName = interfaceClassName;
	}

	/**
	 * @return the simpleInterfaceClassName
	 */
	public String getSimpleInterfaceClassName() {
		return this.simpleInterfaceClassName;
	}

	/**
	 * @param simpleInterfaceClassName the simpleInterfaceClassName to set
	 */
	public void setSimpleInterfaceClassName( String simpleInterfaceClassName ) {
		this.simpleInterfaceClassName = simpleInterfaceClassName;
	}
}
