/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.explorer.model;

import java.io.File;
import java.net.URI;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.tabbedproperties.IServiceCardId;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.su.extensions.ExtensionManager;
import com.ebmwebsourcing.petals.services.utils.ConsumeUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EndpointBean implements IServiceCardId {

	private QName interfaceName, serviceName;
	private String endpointName, wsdlLocation;
	private ServiceUnitBean serviceUnit;
	private Map<QName,Mep> operationNameToMep;


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.tabbedproperties.IServiceCardId
	 * #getInterfaceName()
	 */
	@Override
	public QName getInterfaceName() {
		return this.interfaceName;
	}

	/**
	 * @param interfaceName the interfaceName to set
	 */
	public void setInterfaceName( QName interfaceName ) {
		this.interfaceName = interfaceName;
	}

	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.tabbedproperties.IServiceCardId
	 * #getServiceName()
	 */
	@Override
	public QName getServiceName() {
		return this.serviceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName( QName serviceName ) {
		this.serviceName = serviceName;
	}

	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.tabbedproperties.IServiceCardId
	 * #getEndpointName()
	 */
	@Override
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
	 * @return the serviceUnit
	 */
	public ServiceUnitBean getServiceUnit() {
		return this.serviceUnit;
	}

	/**
	 * @param serviceUnit the serviceUnit to set
	 */
	public void setServiceUnit( ServiceUnitBean serviceUnit ) {
		this.serviceUnit = serviceUnit;
	}

	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		ServiceUnitBean su = getServiceUnit();
		return su != null ? su.getComponentName() : "";
	}

	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.tabbedproperties.IServiceCardId
	 * #getWsdlLocation()
	 */
	@Override
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
	 * @return the WSDL URI of this end-point, or null if none was found
	 */
	public URI getWsdlUri() {

		try {
			String wsdlContainerLocation = this.serviceUnit.getSource().getWsdlContainerLocation( this.serviceUnit );
			if( StringUtils.isEmpty( wsdlContainerLocation ))
				return null;

			URI uri = new File( wsdlContainerLocation ).toURI();
			uri = UriAndUrlHelper.urlToUri( uri.toString().replaceAll( "\\\\", "/" ));
			URI wsdlUri = UriAndUrlHelper.buildNewURI( uri, this.wsdlLocation );
			return wsdlUri;

		} catch( Exception e ) {
			// nothing
		}

		return null;
	}


	/**
	 * Compares this end-point bean with another one.
	 * <p>
	 * Directly overriding {@link #equals(Object)} does not seem to be a good idea,
	 * since the CNF uses EvalutationReferences and that these objects make
	 * the equal fail.
	 * </p>
	 * <p>
	 * Two end-points are similar if they have the same interface, service, end-point and
	 * service-unit names.
	 * </p>
	 *
	 * @param bean
	 * @return true if these end-point beans can be considered equal, false otherwise
	 */
	public boolean isSimilarTo( EndpointBean bean ) {

		if( bean == null )
			return false;

		// ITF
		if( bean.interfaceName == null ) {
			if( this.interfaceName != null )
				return false;
		}
		else if( ! bean.interfaceName.equals( this.interfaceName ))
			return false;


		// SRV
		if( bean.serviceName == null ) {
			if( this.serviceName != null )
				return false;
		}
		else if( ! bean.serviceName.equals( this.serviceName ))
			return false;


		// EDPT
		if( bean.endpointName == null ) {
			if( this.endpointName != null )
				return false;
		}
		else if( ! bean.endpointName.equals( this.endpointName ))
			return false;

		// SU name
		String suName = this.serviceUnit != null ? this.serviceUnit.getServiceUnitName() : null;
		String otherSuName = bean.serviceUnit != null ? bean.serviceUnit.getServiceUnitName() : null;
		if( suName == null )
			return otherSuName == null;

		return suName.equals( otherSuName );
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		return super.equals( obj ) ||
					obj instanceof EndpointBean && isSimilarTo((EndpointBean) obj) ;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int itf = this.interfaceName == null ? 397 : this.interfaceName.hashCode();
		int srv = this.serviceName == null ? 391 : this.serviceName.hashCode();
		int edpt = this.endpointName == null ? 117 : this.endpointName.hashCode();
		return itf * edpt * srv;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append( this.serviceName.getLocalPart());
		sb.append( " > " );
		sb.append( this.interfaceName.getLocalPart());
		sb.append( " @ " );
		sb.append( this.endpointName );

		return sb.toString();
	}


	/**
	 * Gets the operations and the MEP this end-point supports.
	 * <p>
	 * The operations are found with the WSDL if it exists, or with the default
	 * component operations otherwise.
	 * </p>
	 * <p>
	 * Given the number of times a consumer can be created, it appeared as more efficient to
	 * store this information in the model rather than computing it each time. Notice however
	 * that this computation is only performed at the first invocation and not when the object is created.
	 * </p>
	 *
	 * @return the operationNameToMep a non-null map that associates an operation name with a MEP
	 * @see ConsumeUtils#getOperations(String, String, String, String, String, String, String)
	 */
	public Map<QName,Mep> getOperationNameToMep() {

		if( this.operationNameToMep == null ) {
			URI uri = getWsdlUri();
			this.operationNameToMep = ConsumeUtils.getOperations(
						uri,
						this.interfaceName.getLocalPart(),
						this.interfaceName.getNamespaceURI(),
						this.serviceName.getLocalPart(),
						this.serviceName.getNamespaceURI(),
						this.endpointName,
						this.getComponentName(),
						null );
		}

		return this.operationNameToMep;
	}


	/**
	 * Determines whether this service has at least one operation that supports this invocation MEP.
	 * @param mep an invocation MEP
	 * @return true if there is at least one operation that supports this MEP, false otherwise
	 */
	public boolean supportsMep( Mep mep ) {
		return ConsumeUtils.supportsMep( getOperationNameToMep(), mep );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.tabbedproperties.IServiceCardId
	 * #getImplementationType()
	 */
	@Override
	public String getImplementationType() {
		return ExtensionManager.INSTANCE.findComponentAlias( getComponentName());
	}
}
