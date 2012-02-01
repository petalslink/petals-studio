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

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * A connection between an EIP and an EIP or an end-point.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipConnection {

	public static final String PROPERTY_CONNECTION_SOURCE = "ConnectionSource";
	public static final String PROPERTY_CONSUME_BY_ITF = "ConsumeByItf";
	public static final String PROPERTY_CONSUME_BY_SRV = "ConsumeBySrv";
	public static final String PROPERTY_CONSUME_BY_EDPT = "ConsumeByEdpt";
	public static final String PROPERTY_CONSUMED_OPERATION = "ConsumedOperation";
	public static final String PROPERTY_CONSUMED_MEP = "ConsumedMep";
	public static final String PROPERTY_CONDITION_NAME = "ConditionName";
	public static final String PROPERTY_CONDITION_EXPRESSION = "ConditionExpression";
	public static final String PROPERTY_ERROR = "ConnectionError";

	private final PropertyChangeSupport listeners;
	private boolean alreadyConnected = false;
	private final List<String> errorMessages;
	private final List<String> warningMessages;

	private final int id;

	/**
	 * Used in {@link #connect()} and {@link #disconnect()} to be able to
	 * restore the connection at the right index in the source's outgoing connections.
	 */
	private int outgoingConnectionIndex;

	private EipNode source;
	private AbstractNode target;
	private String conditionExpression, conditionName;
	private boolean consumeItf = true, consumeSrv = true, consumeEdpt = true;
	private String consumeOperation, consumeMep;


	/**
	 * Constructor.
	 * @param id
	 * @param source
	 * @param target
	 */
	public EipConnection( int id, EipNode source, AbstractNode target ) {
		this.id = id;
		this.source = source;
		this.target = target;
		this.listeners = new PropertyChangeSupport( this );
		this.errorMessages = new ArrayList<String> ();
		this.warningMessages = new ArrayList<String>();
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
	 * Sends a notification to refresh the connection.
	 */
	public void refreshConnection() {
		this.listeners.firePropertyChange( PROPERTY_CONNECTION_SOURCE, null, null );
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

	/**
	 * Adds a warning message for this element.
	 * @param warningMessages a list of warning messages (may be null)
	 */
	public void setWarningMessages( List<String> warningMessages ) {

		this.warningMessages.clear();
		if( warningMessages != null )
			this.warningMessages.addAll( warningMessages );

		this.listeners.firePropertyChange( PROPERTY_ERROR, null, errorMessages );
	}

	

	/**
	 * @return the source
	 */
	public EipNode getSource() {
		return this.source;
	}


	/**
	 * @param source the source to set
	 * <p>
	 * If the connection was already connected, then this connection is not
	 * part of the outgoing connections of <i>source</i>. It will have to be
	 * added explicitly or {@link #connect()} will have to be invoked again.
	 * </p>
	 */
	public void setSource( EipNode source ) {
		this.source = source;
	}


	/**
	 * @return the conditionName
	 */
	public String getConditionName() {
		return this.conditionName;
	}


	/**
	 * @param conditionName the conditionName to set
	 */
	public void setConditionName( String conditionName ) {

		String oldValue = this.conditionName;
		this.conditionName = conditionName;
		this.listeners.firePropertyChange( PROPERTY_CONDITION_NAME, oldValue, this.conditionName );
	}


	/**
	 * @return the conditionExpression
	 */
	public String getConditionExpression() {
		return this.conditionExpression;
	}


	/**
	 * @param conditionExpression the conditionExpression to set
	 */
	public void setConditionExpression( String conditionExpression ) {

		String oldValue = this.conditionExpression;
		this.conditionExpression = conditionExpression;
		this.listeners.firePropertyChange( PROPERTY_CONDITION_EXPRESSION, oldValue, this.conditionExpression );
	}


	/**
	 * @return the consumeItf
	 */
	public boolean isConsumeItf() {
		return this.consumeItf;
	}


	/**
	 * @param consumeItf the consumeItf to set
	 */
	public void setConsumeItf( boolean consumeItf ) {

		boolean oldValue = this.consumeItf;
		this.consumeItf = consumeItf;
		this.listeners.firePropertyChange( PROPERTY_CONSUME_BY_ITF, oldValue, this.consumeItf );
	}


	/**
	 * @return the consumeSrv
	 */
	public boolean isConsumeSrv() {
		return isConsumeSrvPossible() ? this.consumeSrv : false;
	}


	/**
	 * @param consumeSrv the consumeSrv to set
	 */
	public void setConsumeSrv( boolean consumeSrv ) {

		boolean oldValue = this.consumeSrv;
		this.consumeSrv = consumeSrv;
		this.listeners.firePropertyChange( PROPERTY_CONSUME_BY_SRV, oldValue, this.consumeSrv );
	}


	/**
	 * @return the consumeEdpt
	 */
	public boolean isConsumeEdpt() {
		return isConsumeEdptPossible() ? this.consumeEdpt : false;
	}


	/**
	 * @return the consumeEdpt
	 */
	public boolean isConsumeEdptPossible() {
		return  getTarget() != null
		&& ! StringUtils.isEmpty( getTarget().getEndpointName());
	}


	/**
	 * @return the consumeEdpt
	 */
	public boolean isConsumeSrvPossible() {
		return  getTarget() != null
		&& ! StringUtils.isEmpty( getTarget().getServiceName());
	}


	/**
	 * @param consumeEdpt the consumeEdpt to set
	 */
	public void setConsumeEdpt( boolean consumeEdpt ) {

		boolean oldValue = this.consumeEdpt;
		this.consumeEdpt = consumeEdpt;
		this.listeners.firePropertyChange( PROPERTY_CONSUME_BY_EDPT, oldValue, this.consumeEdpt );
	}


	/**
	 * @return the consumeMep
	 */
	public String getConsumeMep() {
		return this.consumeMep;
	}


	/**
	 * @param consumeMep the consumeMep to set
	 */
	public void setConsumeMep( String consumeMep ) {

		String oldValue = this.consumeMep;
		this.consumeMep = consumeMep;
		this.listeners.firePropertyChange( PROPERTY_CONSUMED_MEP, oldValue, this.consumeMep );
	}


	/**
	 * @return the consumeOperation
	 */
	public String getConsumeOperation() {
		return this.consumeOperation;
	}


	/**
	 * @param consumeOperation the consumeOperation to set
	 */
	public void setConsumeOperation( String consumeOperation ) {

		String oldValue = this.consumeOperation;
		this.consumeOperation = consumeOperation;
		this.listeners.firePropertyChange( PROPERTY_CONSUMED_OPERATION, oldValue, this.consumeOperation );
	}


	/**
	 * @return the target
	 */
	public AbstractNode getTarget() {
		return this.target;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
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
		&& obj instanceof EipConnection
		&& ((EipConnection) obj).id == this.id;
	}


	/**
	 * Adds this connection in the model.
	 */
	public void connect() {

		// Update the model
		if( this.alreadyConnected )
			this.target.getEipChain().restoreConnection( this );
		else {
			this.outgoingConnectionIndex = -1;
			this.alreadyConnected = true;
			this.target.getEipChain().simplyAddConnection( this );
		}

		if( this.target != null )
			this.target.setIncomingConnection( this );

		if( this.source != null ) {
			if( this.outgoingConnectionIndex == -1 )
				this.source.addOutgoingConnection( this );
			else
				this.source.addOutgoingConnection( this, this.outgoingConnectionIndex );
		}

		// If the target is an EIP, the consume operation may have to be updated
		if( this.target instanceof EipNode
					&& StringUtils.isEmpty( this.consumeOperation ))
			setConsumeOperation( "{" + EipNode.DEFAULT_EIP_NS + "}anyOperation" );
	}


	/**
	 * Removes this connection from the model.
	 */
	public void disconnect() {

		if( this.target != null ) {
			this.target.getEipChain().removeConnection( this );
			this.target.setIncomingConnection( null );
		}

		if( this.source != null ) {
			this.outgoingConnectionIndex = this.source.getOutgoingConnections().indexOf( this );
			this.target.getEipChain().removeConnection( this );
			this.source.removeOutgoingConnection( this );
		}
	}


	/**
	 * Changes the source or the target of a connection.
	 * @param sourceNode the source node
	 * @param targetNode the target node
	 */
	public void reconnect( EipNode sourceNode, AbstractNode targetNode ) {

		if( sourceNode == null || targetNode == null || sourceNode == targetNode )
			throw new IllegalArgumentException();

		disconnect();
		this.source = sourceNode;
		this.target = targetNode;
		connect();
	}


	/**
	 * @return true if this connection should define a condition, false otherwise
	 */
	public boolean shouldHaveCondition() {

		boolean result = false;
		if( this.source != null ) {
			if( this.source.getEipType() == EIPtype.ROUTER
						|| this.source.getEipType() == EIPtype.DYNAMIC_ROUTER ) {

				int pos = this.source.getOutgoingConnections().indexOf( this );
				int size = this.source.getOutgoingConnections().size();
				result = pos != size - 1;
			}
		}

		return result;
	}


	public List<String> getWarningMessages() {
		return this.warningMessages;
	}
}
