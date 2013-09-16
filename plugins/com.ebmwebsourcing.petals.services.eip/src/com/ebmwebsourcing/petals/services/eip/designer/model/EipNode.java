/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.eip.designer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An EIP node.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipNode extends AbstractNode {

	public static final String DEFAULT_EIP_NS = "http://petals.ow2.org";

	public static final String PROPERTY_WSDL_URI = "WSDLuri";
	public static final String PROPERTY_EIP_TYPE = "EipType";
	public static final String PROPERTY_COPY_WSDL = "CopyWsdl";
	public static final String PROPERTY_OUTGOING_CONNECTION = "OutgoingConnection";


	/**
	 * The URI of the WSDL that describes this EIP.
	 */
	protected String wsdlUri;

	/**
	 * The EIP type.
	 */
	protected EIPtype eipType;

	/**
	 * True to copy the WSDL in the SU during the export, false otherwise.
	 */
	protected boolean copyWsdl = true;

	/**
	 * The outgoing connections (ordered list).
	 */
	protected final ArrayList<EipConnection> outgoingConnections = new ArrayList<EipConnection> ();

	/**
	 * The properties of the EIP.
	 */
	protected final Map<EipProperty,String> properties = new HashMap<EipProperty,String> ();


	/**
	 * Constructor.
	 * @param id
	 * @param eipType
	 */
	public EipNode( int id, EIPtype eipType ) {
		super( id );
		this.eipType = eipType;

		// Set a default value for name space URIs
		this.serviceNamespace = DEFAULT_EIP_NS;
		this.interfaceNamespace = DEFAULT_EIP_NS;
	}


	/**
	 * @return a list of supported properties (never null)
	 */
	public EipProperty[] getSupportedProperties() {
		return getEipType().getSupportedProperties();
	}


	/**
	 * @return the EIP type
	 */
	public EIPtype getEipType() {
		return this.eipType;
	}


	/**
	 * @param eipType the eipType to set
	 */
	public void setEipType( EIPtype eipType ) {

		EIPtype oldtype = this.eipType;
		this.eipType = eipType;
		this.listeners.firePropertyChange( PROPERTY_EIP_TYPE, oldtype, eipType );

		// Also refresh connections
		for( EipConnection conn : this.outgoingConnections )
			conn.refreshConnection();
	}


	/**
	 * @return the copyWsdl
	 */
	public boolean isCopyWsdl() {
		return this.copyWsdl;
	}


	/**
	 * @param copyWsdl the copyWsdl to set
	 */
	public void setCopyWsdl( boolean copyWsdl ) {
		this.copyWsdl = copyWsdl;
	}


	/**
	 * @return the outgoing connections
	 */
	public List<EipConnection> getOutgoingConnections() {
		return Collections.unmodifiableList( this.outgoingConnections );
	}


	/**
	 * @return the EIP properties (not modifiable)
	 * <p>
	 * It cannot be modified because any modification should trigger a property change (propagate changes in MVC).
	 * Use delegate methods to set a property.
	 * </p>
	 */
	public Map<EipProperty,String> getProperties() {
		return Collections.unmodifiableMap( this.properties );
	}


	/**
	 * Sets an EIP property.
	 * @param eipProperty
	 * @param newValue
	 */
	public void setEipProperty( EipProperty eipProperty, String newValue ) {

		String oldValue = this.properties.get( eipProperty );
		this.properties.put( eipProperty, newValue );
		this.listeners.firePropertyChange( eipProperty.toString(), oldValue, newValue );
	}


	/**
	 * Adds an EIP connection and fires a property change.
	 * @param conn an EIP connection
	 */
	public void addOutgoingConnection( EipConnection conn ) {

		if( ! this.outgoingConnections.contains( conn )) {
			this.outgoingConnections.add( conn );
			this.listeners.firePropertyChange( PROPERTY_OUTGOING_CONNECTION, null, conn );
		}
	}


	/**
	 * Adds an EIP connection and fires a property change.
	 * @param conn an EIP connection
	 * @param index the index where to insert the connection
	 */
	public void addOutgoingConnection( EipConnection conn, int index ) {

		if( ! this.outgoingConnections.contains( conn )) {
			this.outgoingConnections.add( index, conn );
			this.listeners.firePropertyChange( PROPERTY_OUTGOING_CONNECTION, null, conn );
		}
	}


	/**
	 * Swaps two connections among the outgoing connections and fires a property change.
	 * <p>
	 * If any of the two connections is not in the outgoing connections, then nothing is done.
	 * </p>
	 * 
	 * @param conn1 the first connection to swap
	 * @param conn2 the second connection to swap
	 */
	public void swapOutgoingConnections( EipConnection conn1, EipConnection conn2 ) {

		int index1 = this.outgoingConnections.indexOf( conn1 );
		int index2 = this.outgoingConnections.indexOf( conn2 );
		if( index1 != -1 && index2 != -1 ) {
			Collections.swap( this.outgoingConnections, index1, index2 );
			this.listeners.firePropertyChange( PROPERTY_OUTGOING_CONNECTION, null, null );
		}
	}


	/**
	 * Adds an EIP connection and fires a property change.
	 * @param conn an EIP connection
	 */
	public void removeOutgoingConnection( EipConnection conn ) {

		Object o = this.outgoingConnections.remove( conn );
		if( o != null )
			this.listeners.firePropertyChange( PROPERTY_OUTGOING_CONNECTION, o, null );
	}


	/**
	 * @return the wsdlUri
	 */
	public String getWsdlUri() {
		return this.wsdlUri;
	}


	/**
	 * @param wsdlUri the wsdlUri to set
	 */
	public void setWsdlUri( String wsdlUri ) {
		this.wsdlUri = wsdlUri;
	}
}
