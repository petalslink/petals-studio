/****************************************************************************
 * 
 * Copyright (c) 2011-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
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
