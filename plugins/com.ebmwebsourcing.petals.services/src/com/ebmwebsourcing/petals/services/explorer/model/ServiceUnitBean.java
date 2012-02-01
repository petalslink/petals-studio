/****************************************************************************
 * 
 * Copyright (c) 2009-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.explorer.model;

import java.util.ArrayList;
import java.util.List;

import com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ServiceUnitBean {

	private String componentName, serviceAssemblyId;
	private String serviceUnitName, jbiXmlLocation;
	private String saJbiXmlLocation;
	private EndpointSource source;
	private final List<EndpointBean> endpoints = new ArrayList<EndpointBean>();
	private boolean wsdlContainerLocationComputed = false;


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

	/**
	 * @return the serviceAssemblyId
	 */
	public String getServiceAssemblyId() {
		return this.serviceAssemblyId;
	}

	/**
	 * @param serviceAssemblyId the serviceAssemblyId to set
	 */
	public void setServiceAssemblyId( String serviceAssemblyId ) {
		this.serviceAssemblyId = serviceAssemblyId;
	}

	/**
	 * @return the endpoints
	 */
	public List<EndpointBean> getEndpoints() {
		return this.endpoints;
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean addEndpoint( EndpointBean o ) {
		return this.endpoints.add( o );
	}

	/**
	 * @return
	 * @see java.util.List#size()
	 */
	public int countEndpoints() {
		return this.endpoints.size();
	}

	/**
	 * @return the source
	 */
	public EndpointSource getSource() {
		return this.source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource( EndpointSource source ) {
		this.source = source;
	}

	/**
	 * @return the jbiXmlLocation
	 */
	public String getJbiXmlLocation() {
		return this.jbiXmlLocation;
	}

	/**
	 * @param jbiXmlLocation the jbiXmlLocation to set
	 */
	public void setJbiXmlLocation( String jbiXmlLocation ) {
		this.jbiXmlLocation = jbiXmlLocation;
	}

	/**
	 * Indicates whether the WSDL location container was already computed.
	 * <p>
	 * This method was introduced because {@link EndpointBean#getWsdlUri()} relies on
	 * {@link EndpointSource#getWsdlContainerLocation(ServiceUnitBean)}.
	 * </p>
	 * <p>
	 * Since this last method may take several seconds to be processed, the retrieval of
	 * the WSDL URI may be quite long. Therefore, clients may need to check whether
	 * it was already computed or not and thus adapt their use.
	 * </p>
	 * <p>
	 * Returns false by default.
	 * </p>
	 * 
	 * @return the wsdlContainerLocationComputed
	 */
	public boolean isWsdlContainerLocationComputed() {
		return this.wsdlContainerLocationComputed;
	}

	/**
	 * Sets a flag indicating that the WSDL container location was already computed.
	 * <p>
	 * Only source that take a long time to get the WSDL container location should call
	 * this method. Call this method and passing it <b>true</b> means that the WSDL
	 * locations of all the end-points of a SU were computed,
	 * </p>
	 * <p>
	 * Be careful, this is applied to service-units and not to end-points.<br />
	 * When the WSDL container location is computed, it must be done for all the
	 * end-points of the service-unit.
	 * </p>
	 * 
	 * @param wsdlContainerLocationComputed
	 */
	public void setWsdlContainerLocationComputed(
				boolean wsdlContainerLocationComputed ) {

		this.wsdlContainerLocationComputed = wsdlContainerLocationComputed;
	}

	/**
	 * @return the saJbiXmlLocation
	 */
	public String getSaJbiXmlLocation() {
		return this.saJbiXmlLocation;
	}

	/**
	 * @param saJbiXmlLocation the saJbiXmlLocation to set
	 */
	public void setSaJbiXmlLocation( String saJbiXmlLocation ) {
		this.saJbiXmlLocation = saJbiXmlLocation;
	}

	/**
	 * @return the serviceUnitName
	 */
	public String getServiceUnitName() {
		return this.serviceUnitName;
	}

	/**
	 * @param serviceUnitName the serviceUnitName to set
	 */
	public void setServiceUnitName( String serviceUnitName ) {
		this.serviceUnitName = serviceUnitName;
	}
}
