/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.eip.designer.edit;

import org.eclipse.gef.requests.CreationFactory;

import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EndpointCreationFactory implements CreationFactory {

	private String serviceName, interfaceName, endpointName;
	private String serviceNamespace, interfaceNamespace;


	/* (non-Jsdoc)
	 * @see org.eclipse.gef.requests.CreationFactory
	 * #getNewObject()
	 */
	public Object getNewObject() {

		Endpoint edpt = new Endpoint( -1 );

		edpt.setEndpointName( this.endpointName );
		edpt.setServiceName( this.serviceName );
		edpt.setServiceNamespace( this.serviceNamespace );
		edpt.setInterfaceName( this.interfaceName );
		edpt.setInterfaceNamespace( this.interfaceNamespace );

		return edpt;
	}


	/* (non-Jsdoc)
	 * @see org.eclipse.gef.requests.CreationFactory
	 * #getObjectType()
	 */
	public Object getObjectType() {
		return Endpoint.class;
	}


	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName( String serviceName ) {
		this.serviceName = serviceName;
	}


	/**
	 * @param interfaceName the interfaceName to set
	 */
	public void setInterfaceName( String interfaceName ) {
		this.interfaceName = interfaceName;
	}


	/**
	 * @param endpointName the endpointName to set
	 */
	public void setEndpointName( String endpointName ) {
		this.endpointName = endpointName;
	}


	/**
	 * @param serviceNamespace the serviceNamespace to set
	 */
	public void setServiceNamespace( String serviceNamespace ) {
		this.serviceNamespace = serviceNamespace;
	}


	/**
	 * @param interfaceNamespace the interfaceNamespace to set
	 */
	public void setInterfaceNamespace( String interfaceNamespace ) {
		this.interfaceNamespace = interfaceNamespace;
	}
}
