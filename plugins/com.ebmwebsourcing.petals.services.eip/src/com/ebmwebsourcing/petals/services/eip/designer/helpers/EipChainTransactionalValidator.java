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
package com.ebmwebsourcing.petals.services.eip.designer.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.XPathUtils;
import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EIPtype;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipChain;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipProperty;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipProperty.EipPropertyType;
import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.utils.ConsumeUtils;

/**
 * A class in charge of maintaining results of an EIP chain validation.
 * @author Vincent Zurczak - EBM WebSourcing
 * FIXME: once internationalized, the strings will take much less space
 */
public class EipChainTransactionalValidator {

	private final Map<AbstractNode,List<String>> nodeToErrorMessage;
	private final Map<EipConnection,List<String>> connectionToErrorMessage;
	private final Map<EipConnection,List<String>> connectionToWarningMessage;


	/**
	 * Constructor.
	 */
	public EipChainTransactionalValidator() {
		this.nodeToErrorMessage = new HashMap<AbstractNode,List<String>> ();
		this.connectionToErrorMessage = new HashMap<EipConnection,List<String>> ();
		this.connectionToWarningMessage = new HashMap<EipConnection, List<String>>();
	}


	/**
	 * Validates all the elements of this chain.
	 * @param eipChain
	 */
	public void validateAll( EipChain eipChain ) {

		// Clear the contextual messages
		this.nodeToErrorMessage.clear();
		this.connectionToErrorMessage.clear();
		this.connectionToWarningMessage.clear();


		// Validate the properties of connections first
		// Nodes also use connections properties and add other errors about connections
		for( EipConnection conn : eipChain.getConnections()) {
			validateConnection( conn );
		}


		// Prepare the check for the end-point uniqueness in the chain
		Map<EndpointBean,List<AbstractNode>> edptToNodes = new HashMap<EndpointBean,List<AbstractNode>> ();


		// Validate the properties of the EIP nodes
		Map<String,List<EipNode>> eipServiceNameToEip = new HashMap<String,List<EipNode>> ();
		for( EipNode eip : eipChain.getEipNodes()) {
			validateEipNode( eip );

			// Valid node => we have an EIP
			List<String> messages = this.nodeToErrorMessage.get( eip );
			if( messages == null || messages.isEmpty()) {

				EndpointBean bean = new EndpointBean();
				bean.setEndpointName( eip.getEndpointName());
				bean.setInterfaceName( new QName( eip.getInterfaceNamespace(), eip.getInterfaceName()));
				bean.setServiceName( new QName( eip.getServiceNamespace(), eip.getServiceName()));

				// This is to check the end-point uniqueness (itf + srv + edpt)
				List<AbstractNode> list = edptToNodes.get( bean );
				if( list == null )
					list = new ArrayList<AbstractNode> ();

				list.add( eip );
				edptToNodes.put( bean, list );

				// This is to check the uniqueness of the service name (local part)
				List<EipNode> eips = eipServiceNameToEip.get( eip.getServiceName());
				if( eips == null )
					eips = new ArrayList<EipNode> ();

				eips.add( eip );
				eipServiceNameToEip.put( eip.getServiceName(), eips );
			}
		}


		// We must also avoid a same service name for EIPs, because of the export (can lead to conflict during the export).
		// This is a constraint due to the fact we have one project / SU per EIP.
		for( Map.Entry<String,List<EipNode>> entry : eipServiceNameToEip.entrySet()) {
			if( entry.getValue().size() == 1 )
				continue;

			for( EipNode eip : entry.getValue())
				addErrorMessage( eip, "Same service names are not allowed within a same chain. " + entry.getKey() + " is already used." );
		}


		// Validate the properties of the end-points
		for( Endpoint edpt : eipChain.getEndpoints()) {
			validateEndpoint( edpt );

			// Valid node => we have an end-point
			List<String> messages = this.nodeToErrorMessage.get( edpt );
			if( messages == null || messages.isEmpty()) {

				EndpointBean bean = new EndpointBean();
				bean.setEndpointName( edpt.getEndpointName());
				bean.setInterfaceName( new QName( edpt.getInterfaceNamespace(), edpt.getInterfaceName()));
				bean.setServiceName( new QName( edpt.getServiceNamespace(), edpt.getServiceName()));

				List<AbstractNode> list = edptToNodes.get( bean );
				if( list == null )
					list = new ArrayList<AbstractNode> ();

				list.add( edpt );
				edptToNodes.put( bean, list );
			}
		}


		// Check the end-point uniqueness in the chain
		for( List<AbstractNode> nodes : edptToNodes.values()) {
			if( nodes.size() == 1 )
				continue;

			// Two EIP cannot have the same ID.
			// An EIP and an end-point cannot either.
			// Two end-points with the same ID refer to the same end-point.
			int eipCpt = 0;
			boolean hasEdpt = false;
			for( AbstractNode node : nodes ) {
				if( node instanceof EipNode )
					eipCpt ++;
				else if( node instanceof Endpoint )
					hasEdpt = true;
			}

			// Add the errors
			if( hasEdpt && eipCpt > 0 ) {
				for( AbstractNode node : nodes ) {
					if( node instanceof EipNode )
						addErrorMessage( node, "This EIP identifier is already taken by a Petals service used in this chain." );
				}

			} else if (eipCpt > 1 ) {
				for( AbstractNode node : nodes ) {
					if( node instanceof EipNode )
						addErrorMessage( node, "This EIP identifier is already used by another EIP in the chain." );
				}
			}
		}


		// Update the model with the found error messages
		for( Map.Entry<AbstractNode,List<String>> entry : this.nodeToErrorMessage.entrySet()) {
			entry.getKey().setErrorMessages( entry.getValue());
		}

		for( Map.Entry<EipConnection,List<String>> entry : this.connectionToErrorMessage.entrySet()) {
			entry.getKey().setErrorMessages( entry.getValue());
		}

		for (Map.Entry<EipConnection,List<String>> entry : this.connectionToWarningMessage.entrySet()) {
			entry.getKey().setWarningMessages( entry.getValue());
		}
	}


	/**
	 * @return the nodeToErrorMessage
	 */
	public Map<AbstractNode,List<String>> getNodeToErrorMessage() {
		return this.nodeToErrorMessage;
	}


	/**
	 * @return the connectionToErrorMessage
	 */
	public Map<EipConnection,List<String>> getConnectionToErrorMessage() {
		return this.connectionToErrorMessage;
	}

	/**
	 * @return the warningMessages
	 */
	public Map<EipConnection,List<String>> getConnectionToWarningMessage() {
		return this.connectionToWarningMessage;
	}


	/**
	 * @param node the node that was validated
	 * @param errorMessage an error message (not null)
	 */
	private void addErrorMessage( AbstractNode node, String errorMessage ) {

		List<String> messages = this.nodeToErrorMessage.get( node );
		if( messages == null ) {
			messages = new ArrayList<String> ();
			this.nodeToErrorMessage.put( node, messages );
		}

		messages.add( errorMessage );
	}


	/**
	 * @param conn the connection that was validated
	 * @param errorMessage an error message (not null)
	 */
	private void addErrorMessage( EipConnection conn, String errorMessage ) {

		List<String> messages = this.connectionToErrorMessage.get( conn );
		if( messages == null ) {
			messages = new ArrayList<String> ();
			this.connectionToErrorMessage.put( conn, messages );
		}

		messages.add( errorMessage );
	}

	/**
	 * @param conn the connection that was validated
	 * @param warningMessage an error message (not null)
	 */
	private void addWarningMessage( EipConnection conn, String warningMessage ) {

		List<String> messages = this.connectionToWarningMessage.get( conn );
		if( messages == null ) {
			messages = new ArrayList<String> ();
			this.connectionToWarningMessage.put( conn, messages );
		}

		messages.add( warningMessage );
	}


	/**
	 * Clears all the error messages for this connection.
	 * @param conn
	 */
	private void clearErrorMessages( EipConnection conn ) {

		List<String> messages = this.connectionToErrorMessage.get( conn );
		if( messages == null )
			messages = new ArrayList<String> ();
		else
			messages.clear();

		this.connectionToErrorMessage.put( conn, messages );
	}


	/**
	 * Clears all the error messages for this connection.
	 * @param conn
	 */
	private void clearWarningMessages( EipConnection conn ) {

		List<String> messages = this.connectionToWarningMessage.get( conn );
		if( messages == null )
			messages = new ArrayList<String> ();
		else
			messages.clear();

		this.connectionToWarningMessage.put( conn, messages );
	}



	/**
	 * Clears all the error messages for this node.
	 * @param node
	 */
	private void clearErrorMessages( AbstractNode node ) {

		List<String> messages = this.nodeToErrorMessage.get( node );
		if( messages == null )
			messages = new ArrayList<String> ();
		else
			messages.clear();

		this.nodeToErrorMessage.put( node, messages );
	}


	/**
	 * Validates the properties of an EIP node.
	 * @param eip
	 */
	private void validateEipNode( EipNode eip ) {

		// Clear messages
		clearErrorMessages( eip );


		// Service validation
		if( StringUtils.isEmpty( eip.getServiceName())
					!= StringUtils.isEmpty( eip.getServiceNamespace()))
			addErrorMessage( eip, "A service name is a QName. Both the service local name and the service name space should be set (or none of them)." );

		if( StringUtils.isEmpty( eip.getInterfaceName())
					!= StringUtils.isEmpty( eip.getInterfaceNamespace()))
			addErrorMessage( eip, "An interface name is a QName. Both the interface local name and the interface name space should be set (or none of them)." );

		if( StringUtils.isEmpty( eip.getInterfaceName()))
			addErrorMessage( eip, "The interface name is required." );

		if( StringUtils.isEmpty( eip.getServiceName()))
			addErrorMessage( eip, "The service name is required." );

		if( StringUtils.isEmpty( eip.getEndpointName()))
			addErrorMessage( eip, "The end-point name is required." );


		// Validate XPath properties
		// Router conditions are on the connections, not in the EIP properties
		for( Map.Entry<EipProperty,String> entry : eip.getProperties().entrySet()) {
			if( entry.getKey().getType() == EipPropertyType.XPATH ) {
				String msg = XPathUtils.validateXPathExpression( entry.getValue());
				if( msg != null )
					addErrorMessage( eip, msg );
			}
		}


		// TODO: for the routing-slip, the invocation pattern should be the one of its last target
		// TODO: for routers and dynamic routers, the MEP should be the same for all the EIP and all the targets
		// TODO: think about the other patterns and the MEP coherence...


		// EIP validation
		String msg = null;
		int outCpt = eip.getOutgoingConnections().size();

		switch( eip.getEipType()) {
		case AGGREGATOR:
			if( outCpt != 1 ) {
				msg = "The " + eip.getEipType().toString() + " pattern only supports 1 connected service.";
				addErrorMessage( eip, msg );
			}
			break;

		case BRIDGE:
			if( outCpt != 1 ) {
				msg = "The " + eip.getEipType().toString() + " pattern only supports 1 connected service.";
				addErrorMessage( eip, msg );
			}
			break;

		case DISPATCHER:
			if( outCpt < 1 ) {
				msg = "The " + eip.getEipType().toString() + " pattern requires at least 1 connected service.";
				addErrorMessage( eip, msg );
			}

			if( ! isValidMep( eip.getIncomingConnection(), Mep.IN_ONLY ))
				addErrorMessage( eip.getIncomingConnection(), "A " + eip.getEipType().toString() + " pattern can only be invoked with the " + Mep.IN_ONLY + " MEP." );

			for( EipConnection conn : eip.getOutgoingConnections()) {
				if( ! isValidMep( conn, Mep.IN_ONLY ))
					addErrorMessage( conn, "A " + eip.getEipType().toString() + " pattern can only invoke operations with the " + Mep.IN_ONLY + " MEP." );
			}
			break;

		case DYNAMIC_ROUTER:
			if( outCpt < 3 ) {
				msg = "The pattern " + eip.getEipType().toString() + " is useless if there are less than 3 connected services.";
				addErrorMessage( eip, msg );

			} else if( ! isValidMep( eip.getOutgoingConnections().get( 0 ), Mep.IN_OUT )) {
				addErrorMessage(
							eip.getOutgoingConnections().get( 0 ),
							"The first connected service of a " + eip.getEipType().toString() + " pattern must be invokek with the " + Mep.IN_OUT + " MEP." );
			}
			break;

		case ROUTER:
			if( outCpt < 2 ) {
				msg = "The pattern " + eip.getEipType().toString() + " is useless if there are less than 2 connected services.";
				addErrorMessage( eip, msg );
			}
			break;

		case ROUTING_SLIP:
			if( outCpt < 1 ) {
				msg = "The " + eip.getEipType().toString() + " pattern requires at least 1 connected service.";
				addErrorMessage( eip, msg );
			}

			else {
				int size = eip.getOutgoingConnections().size();
				for( int i=0; i<size-1; i++ ) {
					if( ! isValidMep( eip.getOutgoingConnections().get( i ), Mep.IN_OUT ))
						addErrorMessage( eip.getOutgoingConnections().get( i ), "This service must be invoked with the " + Mep.IN_OUT + " MEP." );
				}

				if( eip.getIncomingConnection() != null ) {
					String mep = eip.getIncomingConnection().getConsumeMep();
					Mep expectedMep = Mep.whichMep( mep );
					if( ! isValidMep( eip.getOutgoingConnections().get( size-1 ), expectedMep ))
						addErrorMessage( eip.getOutgoingConnections().get( size-1 ), "This service must be invoked using the " + expectedMep + " MEP to be coherent with the original request." );
				}
			}
			break;

		case SCATTER_GATHER:
			if( outCpt < 1 ) {
				msg = "The " + eip.getEipType().toString() + " pattern requires at least 1 connected service.";
				addErrorMessage( eip, msg );
			}

			if( ! isValidMep( eip.getIncomingConnection(), Mep.IN_OUT ))
				addErrorMessage( eip.getIncomingConnection(), "A " + eip.getEipType().toString() + " pattern can only be invoked with the " + Mep.IN_OUT + " MEP." );
			break;

		case SPLITTER:
			if( outCpt != 1 ) {
				msg = "The " + eip.getEipType().toString() + " pattern only supports 1 connected service.";
				addErrorMessage( eip, msg );
			}

			if( ! isValidMep( eip.getIncomingConnection(), Mep.IN_OUT ))
				addErrorMessage( eip.getIncomingConnection(), "A " + eip.getEipType().toString() + " pattern can only be invoked with the " + Mep.IN_OUT + " MEP." );
			break;

		case WIRETAP:
			if( outCpt != 2 ) {
				msg = "The " + eip.getEipType().toString() + " pattern requires exactly 2 connected services.";
				addErrorMessage( eip, msg );
			}

			if( StringUtils.isEmpty( eip.getProperties().get( EipProperty.WIRETAP_WAY )))
				addErrorMessage( eip, "The wiretap way is not set." );

			if( ! isValidMep( eip.getOutgoingConnections().get( 1 ), Mep.IN_ONLY ))
				addErrorMessage( eip.getOutgoingConnections().get( 1 ), "The monitoring flow must be invoked with the " + Mep.IN_ONLY + " MEP." );
			break;
		}
	}



	/**
	 * Validates the properties of an end-point.
	 * @param edpt
	 */
	private void validateEndpoint( Endpoint edpt ) {

		// Clear messages
		clearErrorMessages( edpt );


		// End-point validation
		if( StringUtils.isEmpty( edpt.getServiceName())
					!= StringUtils.isEmpty( edpt.getServiceNamespace()))
			addErrorMessage( edpt, "A service name is a QName. Both the service local name and the service name space should be set (or none of them)." );

		if( StringUtils.isEmpty( edpt.getInterfaceName())
					!= StringUtils.isEmpty( edpt.getInterfaceNamespace()))
			addErrorMessage( edpt, "An interface name is a QName. Both the interface local name and the interface name space should be set (or none of them)." );

		if( StringUtils.isEmpty( edpt.getInterfaceName()))
			addErrorMessage( edpt, "The interface name is required." );


		// End-point's specific validation
		if( edpt.getIncomingConnection() == null )
			addErrorMessage( edpt, "This service will never be invoked (no incoming connection)." );
	}


	/**
	 * Validates the connection.
	 * <p>
	 * Error messages should be stored with {@link EipChainTransactionalValidator#addErrorMessage(AbstractNode, String)}.
	 * </p>
	 *
	 * @param validator a validator (not null)
	 * <p>
	 * The validator will validate a part or the entire chain.
	 * Once it has all the errors, it updates the model, which triggers a property change.
	 * </p>
	 * <p>
	 * Even connections with no error should be added in the validator. Use
	 * <code>validator.addErrorMessage( conn, null )</code>
	 * </p>
	 */
	private void validateConnection( EipConnection conn ) {

		clearErrorMessages( conn );
		clearWarningMessages(conn);

		// Validate conditions
		String msg = null;
		if( conn.shouldHaveCondition()) {
			if( StringUtils.isEmpty( conn.getConditionExpression()))
				msg = "A condition must be defined.";

			else {
				if( conn.getSource().getEipType() == EIPtype.ROUTER &&
							EipProperty.ROUTING_CRITERIA_BY_OPERATION.equals( conn.getSource().getProperties().get( EipProperty.ROUTING_CRITERIA ))) {

					if( ! NamespaceUtils.isShortenNamespace( conn.getConditionExpression()))
						msg = "The routing operation is not a valid QName.";

				} else {
					msg = XPathUtils.validateXPathExpression( conn.getConditionExpression());
				}
			}
		}

		if( msg != null ) {
			addErrorMessage( conn, msg );
		}

		// Validate operation and MEP - they must be set
		if( StringUtils.isEmpty( conn.getConsumeOperation())) {
			addWarningMessage( conn, "The operation to invoke is not defined." );

		} else if( ! NamespaceUtils.isShortenNamespace( conn.getConsumeOperation())) {
			addErrorMessage( conn, "The operation to invoke must be a valid qualified name." );

		} if( StringUtils.isEmpty( conn.getConsumeMep())
				|| Mep.UNKNOWN.toString().equals( conn.getConsumeMep())) {
			addWarningMessage( conn, "The invocation pattern (MEP) should  be set." );
		}

		// Validate the MEP and the invoked operations for the end-points
		// Skip this step if the operation is not set
		else if( conn.getTarget() instanceof Endpoint
				&& ! StringUtils.isEmpty( conn.getConsumeOperation())) {

			Mep currentMep = Mep.whichMep( conn.getConsumeMep());
			QName currentOperation = NamespaceUtils.buildQName( conn.getConsumeOperation());
			AbstractNode t = conn.getTarget();

			QName itfName = null;
			if( ! StringUtils.isEmpty( t.getInterfaceName())
						&& ! StringUtils.isEmpty( t.getInterfaceNamespace()))
				itfName = new QName( t.getInterfaceNamespace(), t.getInterfaceName());

			QName srvName = null;
			if( ! StringUtils.isEmpty( t.getServiceName())
						&& ! StringUtils.isEmpty( t.getServiceNamespace()))
				srvName = new QName( t.getServiceNamespace(), t.getServiceName());

			String edptName = t.getEndpointName();
			if( StringUtils.isEmpty( edptName ))
				edptName = null;

			Map<QName,Mep> consumableOps = ConsumeUtils.getValidOperationsForConsume( itfName, srvName, edptName );
			if( ! consumableOps.containsKey( currentOperation )) {
				addWarningMessage( conn, "The invoked operation is not available for this service." );

			} else if( currentMep != Mep.UNKNOWN
						&& consumableOps.get( currentOperation ) != currentMep ) {
				addWarningMessage( conn, "The invocation pattern (MEP) does not match the operation's one (or there is an ambiguity in the service consumption)." );
			}
		}
	}


	/**
	 * Compares the MEP associated with a connection and an expected MEP.
	 * @param conn the connection to check (can be null)
	 * @param expectedMep the expected MEP (not null)
	 * @return true if the expected MEP and the associated one are equal or if the connection is null, false otherwise
	 */
	private boolean isValidMep( EipConnection conn, Mep expectedMep ) {
		return conn == null || expectedMep.toString().equalsIgnoreCase( conn.getConsumeMep());
	}
}
