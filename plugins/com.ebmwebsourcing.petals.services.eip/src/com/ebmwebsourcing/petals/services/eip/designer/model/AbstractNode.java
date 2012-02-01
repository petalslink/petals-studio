/****************************************************************************
 * 
 * Copyright (c) 2010-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;

/**
 * An abstract node in a EIP graph.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractNode {

	protected final PropertyChangeSupport listeners;
	protected Point location = new Point( 60, 60 );

	public static final String PROPERTY_LAYOUT = "NodeLayout";
	public static final String PROPERTY_SERVICE_NAME = "ServiceName";
	public static final String PROPERTY_SERVICE_NAMESPACE = "ServiceNamespace";
	public static final String PROPERTY_INTERFACE_NAME = "InterfaceName";
	public static final String PROPERTY_INTERFACE_NAMESPACE = "InterfaceNamespace";
	public static final String PROPERTY_ENDPOINT_NAME = "EndpointName";
	public static final String PROPERTY_INCOMING_CONNECTION = "IncomingConnection";
	public static final String PROPERTY_ERROR = "NodeError";

	protected int id;
	protected String serviceName, interfaceName, endpointName;
	protected String serviceNamespace, interfaceNamespace;
	protected EipChain eipChain;

	protected EipConnection incomingConnection;
	protected List<String> errorMessages;


	/**
	 * Constructor.
	 * @param id
	 */
	protected AbstractNode( int id ) {
		this.id = id;
		this.listeners = new PropertyChangeSupport( this );
		this.errorMessages = new ArrayList<String> ();
	}


	/**
	 * @param listener
	 */
	public void addPropertyChangeListener( PropertyChangeListener listener ) {
		this.listeners.addPropertyChangeListener( listener );
	}


	/**
	 * @param listener
	 */
	public void removePropertyChangeListener( PropertyChangeListener listener ) {
		this.listeners.removePropertyChangeListener( listener );
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

		String oldServiceName = this.serviceName;
		this.serviceName = serviceName;
		this.listeners.firePropertyChange( PROPERTY_SERVICE_NAME, oldServiceName, serviceName );
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

		String oldInterfaceName = this.interfaceName;
		this.interfaceName = interfaceName;
		this.listeners.firePropertyChange( PROPERTY_INTERFACE_NAME, oldInterfaceName, interfaceName );
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

		String oldEndpointName = this.endpointName;
		this.endpointName = endpointName;
		this.listeners.firePropertyChange( PROPERTY_ENDPOINT_NAME, oldEndpointName, endpointName );
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

		String oldServiceNamespace = this.serviceNamespace;
		this.serviceNamespace = serviceNamespace;
		this.listeners.firePropertyChange( PROPERTY_SERVICE_NAMESPACE, oldServiceNamespace, serviceNamespace );
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

		String oldInterfacespaceName = this.interfaceNamespace;
		this.interfaceNamespace = interfaceNamespace;
		this.listeners.firePropertyChange( PROPERTY_INTERFACE_NAMESPACE, oldInterfacespaceName, interfaceNamespace );
	}


	/**
	 * @param id the id to set
	 */
	public void setId( int id ) {
		this.id = id;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}


	/**
	 * @return the eipChain
	 */
	public EipChain getEipChain() {
		return this.eipChain;
	}


	/**
	 * @param eipChain the eipChain to set
	 */
	public void setEipChain( EipChain eipChain ) {
		this.eipChain = eipChain;
	}


	/**
	 * @return the incomingConnection
	 */
	public EipConnection getIncomingConnection() {
		return this.incomingConnection;
	}


	/**
	 * @param incomingConnection the incomingConnection to set
	 */
	public void setIncomingConnection( EipConnection incomingConnection ) {

		EipConnection oldValue = this.incomingConnection;
		this.incomingConnection = incomingConnection;
		this.listeners.firePropertyChange( PROPERTY_INCOMING_CONNECTION, oldValue, incomingConnection );
	}


	/**
	 * @return the location
	 */
	public Point getLocation() {
		return this.location;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation( Point location ) {

		Point oldLocation = this.location;
		this.location = location;
		this.listeners.firePropertyChange( PROPERTY_LAYOUT, oldLocation, location );
	}


	/**
	 * @return the errorMessages (not modifiable and never null)
	 * <p>
	 * It cannot be modified because any modification should trigger a property change (propagate changes in MVC).
	 * Use delegate methods to add or remove an element.
	 * </p>
	 */
	public List<String> getErrorMessages() {
		return Collections.unmodifiableList( this.errorMessages );
	}


	/**
	 * Adds an error message for this element.
	 * @param errorMessages a list of error messages (may be null)
	 */
	public void setErrorMessages( List<String> errorMessages ) {

		this.errorMessages.clear();
		if( errorMessages != null )
			this.errorMessages.addAll( errorMessages );

		this.listeners.firePropertyChange( PROPERTY_ERROR, null, errorMessages );
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.id;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {

		return obj != null
		&& getClass().isAssignableFrom( obj.getClass())
		&& ((AbstractNode) obj).id == this.id;
	}
}
