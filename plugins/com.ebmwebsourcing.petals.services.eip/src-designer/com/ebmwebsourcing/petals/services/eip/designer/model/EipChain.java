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
package com.ebmwebsourcing.petals.services.eip.designer.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A chain of EIP.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipChain {

	public static final String PROPERTY_CHAIN_TITLE = "EipChainTitle";
	public static final String PROPERTY_CHAIN_DESCRIPTION = "EipChainDescription";
	public static final String PROPERTY_CHAIN_VERSION = "EipChainVersion";
	public static final String PROPERTY_CHILD = "ChildCountChange";
	public static final String PROPERTY_CONNECTION = "ConnectionChange";

	private final PropertyChangeSupport listeners;
	private final AtomicInteger nodeIdCounter = new AtomicInteger();
	private final AtomicInteger connIdCounter = new AtomicInteger();

	private String title;
	private String description;
	private String version;

	private final List<EipNode> eipNodes = new ArrayList<EipNode> ();
	private final List<Endpoint> endpoints = new ArrayList<Endpoint> ();
	private final List<EipConnection> eipConnections = new ArrayList<EipConnection> ();


	/**
	 * Constructor.
	 */
	public EipChain() {
		this.listeners = new PropertyChangeSupport( this );
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
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle( String title ) {

		String oldValue = this.title;
		this.title = title;
		this.listeners.firePropertyChange( PROPERTY_CHAIN_TITLE, oldValue, title );
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

		String oldValue = this.description;
		this.description = description;
		this.listeners.firePropertyChange( PROPERTY_CHAIN_DESCRIPTION, oldValue, description );
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion( String version ) {

		String oldValue = this.version;
		this.version = version;
		this.listeners.firePropertyChange( PROPERTY_CHAIN_VERSION, oldValue, version );
	}

	/**
	 * @return the eipNodes (not modifiable)
	 * <p>
	 * It cannot be modified because any modification should trigger a property change (propagate changes in MVC).
	 * Use delegate methods to add or remove an element.
	 * </p>
	 */
	public List<EipNode> getEipNodes() {
		return Collections.unmodifiableList( this.eipNodes );
	}

	/**
	 * @return the endpoints (not modifiable)
	 * <p>
	 * It cannot be modified because any modification should trigger a property change (propagate changes in MVC).
	 * Use delegate methods to add or remove an element.
	 * </p>
	 */
	public List<Endpoint> getEndpoints() {
		return Collections.unmodifiableList( this.endpoints );
	}

	/**
	 * @return the eipConnections (not modifiable)
	 * <p>
	 * It cannot be modified because any modification should trigger a property change (propagate changes in MVC).
	 * Use delegate methods to add or remove an element.
	 * </p>
	 */
	public List<EipConnection> getConnections() {
		return Collections.unmodifiableList( this.eipConnections );
	}

	/**
	 * @return a new ID for an EIP node
	 * @see java.util.concurrent.atomic.AtomicInteger#getAndIncrement()
	 */
	public final int getNewNodeId() {
		return this.nodeIdCounter.getAndIncrement();
	}

	/**
	 * @param newValue the next node ID this instance should return
	 * @see java.util.concurrent.atomic.AtomicInteger#set(int)
	 */
	public final void setNextNodeId( int newValue ) {
		this.nodeIdCounter.set( newValue );
	}

	/**
	 * @return a new ID for an EIP connection
	 * @see java.util.concurrent.atomic.AtomicInteger#getAndIncrement()
	 */
	public final int getNewConnectionId() {
		return this.connIdCounter.getAndIncrement();
	}

	/**
	 * @param newValue the next connection ID this instance should return
	 * @see java.util.concurrent.atomic.AtomicInteger#set(int)
	 */
	public final void setNextConnectionId( int newValue ) {
		this.connIdCounter.set( newValue );
	}


	/**
	 * Adopts an EIP node in this graph.
	 * <p>
	 * The node will be associated with this chain instead of its previous one.
	 * And the node will be assigned a new ID in this chain.
	 * Other node properties are preserved by this operation.
	 * </p>
	 * <p>
	 * It avoids duplicate objects.
	 * </p>
	 * 
	 * @param eip the EIP node to adopt
	 */
	public void adoptEipNode( EipNode eip ) {
		eip.setId( getNewNodeId());
		eip.setEipChain( this );
		this.eipNodes.add( eip );
		this.listeners.firePropertyChange( PROPERTY_CHILD, null, eip );
	}


	/**
	 * Adopts an end-point in this graph.
	 * <p>
	 * The node will be associated with this chain instead of its previous one.
	 * And the node will be assigned a new ID in this chain.
	 * Other node properties are preserved by this operation.
	 * </p>
	 * <p>
	 * It avoids duplicate objects.
	 * </p>
	 * 
	 * @param edpt the end-point node to adopt
	 */
	public void adoptEndpoint( Endpoint edpt ) {
		edpt.setId( getNewNodeId());
		edpt.setEipChain( this );
		this.endpoints.add( edpt );
		this.listeners.firePropertyChange( PROPERTY_CHILD, null, edpt );
	}


	/**
	 * Removes an end-point from this graph.
	 * @param edpt the end-point node to remove
	 */
	public void removeEndpoint( Endpoint edpt ) {
		this.endpoints.remove( edpt );
		this.listeners.firePropertyChange( PROPERTY_CHILD, edpt, null );
	}


	/**
	 * Removes an EIP node from this graph.
	 * @param eip the EIP node to remove
	 */
	public void removeEipNode( EipNode eip ) {
		this.eipNodes.remove( eip );
		this.listeners.firePropertyChange( PROPERTY_CHILD, eip, null );
	}


	/**
	 * Restores a deleted EIP¨node.
	 * @param eip the EIP node to restore
	 */
	public void restoreEipNode( EipNode eip ) {
		this.eipNodes.add( eip );
		this.listeners.firePropertyChange( PROPERTY_CHILD, null, eip );
	}


	/**
	 * Restores a deleted end-point.
	 * @param edpt the end-point to restore
	 */
	public void restoreEndpoint( Endpoint edpt ) {
		this.endpoints.add( edpt );
		this.listeners.firePropertyChange( PROPERTY_CHILD, null, edpt );
	}


	/**
	 * Removes an EIP connection from this graph.
	 * @param conn the EIP connection to remove
	 */
	public void removeConnection( EipConnection conn ) {
		this.eipConnections.remove( conn );
		this.listeners.firePropertyChange( PROPERTY_CONNECTION, conn, null );
	}


	/**
	 * Restores an EIP connection from this graph.
	 * @param conn the EIP connection to restore
	 */
	public void restoreConnection( EipConnection conn ) {
		this.eipConnections.add( conn );
		this.listeners.firePropertyChange( PROPERTY_CONNECTION, null, conn );
	}


	/**
	 * Adds an end-point without triggering a property change (deserialization purpose).
	 * @param edpt the end-point to add
	 */
	public void simplyAddEndpoint( Endpoint edpt ) {
		this.endpoints.add( edpt );
	}


	/**
	 * Adds an EIP node without triggering a property change (deserialization purpose).
	 * @param eip the EIP node to add
	 */
	public void simplyAddEipNode( EipNode eip ) {
		this.eipNodes.add( eip );
	}


	/**
	 * Adds an EIP connection without triggering a property change.
	 * @param conn the EIP connection to add
	 */
	public void simplyAddConnection( EipConnection conn ) {
		this.eipConnections.add( conn );
	}
}
