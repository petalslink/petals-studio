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

import java.util.Arrays;

/**
 * The different Enterprise Integration Patterns this designer supports.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public enum EIPtype {

	AGGREGATOR,
	BRIDGE,
	DISPATCHER,
	DYNAMIC_ROUTER,
	ROUTER,
	ROUTING_SLIP,
	SCATTER_GATHER,
	SPLITTER,
	WIRETAP;


	/**
	 * @return the properties this pattern supports.
	 */
	public EipProperty[] getSupportedProperties() {

		EipProperty[] result;
		switch( this ) {
		case AGGREGATOR:
			result = new EipProperty[] { EipProperty.TEST, EipProperty.AGGREGATOR_CORRELATION };
			break;
		case BRIDGE:
			result = new EipProperty[] { EipProperty.FAULT_TO_EXCEPTION };
			break;
		case DISPATCHER:
			result = new EipProperty[ 0 ];
			break;
		case DYNAMIC_ROUTER:
			result = new EipProperty[] { EipProperty.FAULT_TO_EXCEPTION };
			break;
		case ROUTER:
			result = new EipProperty[] { EipProperty.FAULT_TO_EXCEPTION, EipProperty.ROUTING_CRITERIA };
			break;
		case ROUTING_SLIP:
			result = new EipProperty[] { EipProperty.FAULT_TO_EXCEPTION };
			break;
		case SCATTER_GATHER:
			result = new EipProperty[] { EipProperty.FAULT_ROBUST, EipProperty.EXCEPTION_ROBUST };
			break;
		case SPLITTER:
			result = new EipProperty[] { EipProperty.TEST, EipProperty.ATTACHMENT, EipProperty.FAULT_ROBUST, EipProperty.EXCEPTION_ROBUST };
			break;
		case WIRETAP:
			result = new EipProperty[] { EipProperty.WIRETAP_WAY };
			break;
		default:
			result = new EipProperty[ 0 ];
		}

		return result;
	}


	/**
	 * @param propertyName a property name
	 * @return true if this property is supported by this EIP type, false otherwise
	 */
	public boolean supportsProperty( String propertyName ) {
		return Arrays.asList( getSupportedProperties()).contains( propertyName );
	}


	/**
	 * @return the EIP type's name with a pretty formatting
	 */
	public String getPrettyName() {
		String name = name().substring( 0, 1 ) + name().toLowerCase().substring( 1 );
		name = name.replaceAll( "_", " " );
		return name;
	}


	/**
	 * @return a detailed description of an EIP
	 */
	public String getExplaination() {

		StringBuilder result = new StringBuilder();
		switch( this ) {
		case AGGREGATOR:
			result.append( "This pattern exposes an aggregation service.\n\n" );
			result.append( "Received messages that match a condition (aggregator-correlation) are stored for aggregation.\n" );
			result.append( "The aggrgation is a new message whose content is buffered from the content of matching received messages.\n" );
			result.append( "This aggrgation is flushed when a received message matches another condition (test).\n" );
			result.append( "In this case, the aggregated message is sent to the linked service.\n\n" );
			result.append( "Note that non-matching messages are simply ignored." );
			break;

		case BRIDGE:
			result.append( "This pattern exposes a mediation service.\n" );
			result.append( "The mediation is about Message Exchange Patterns.\n\n" );
			result.append( "Examples:\n" );
			result.append( "+ Invoke a service operation in RobustInOnly as an InOut operation.\n" );
			result.append( "+ Invoke a service operation in InOut as a InOnly operation." );
			break;

		case DISPATCHER:
			result.append( "This pattern exposes a dispatching service.\n" );
			result.append( "When a message is received, it is broadcasted to all the services that are linked to this EIP." );
			break;

		case DYNAMIC_ROUTER:
			result.append( "This pattern exposes a routing service.\n" );
			result.append( "Unlike the router pattern, the routing is not based on the received message.\n\n" );
			result.append( "The first linked service is invoked. The service's response is checked against XPath conditions.\n" );
			result.append( "If the 1st condition is verified, then the 2nd service is invoked.\n" );
			result.append( "If the 2nd condition is verified, then the 3rd service is invoked.\n" );
			result.append( "...\n" );
			result.append( "If the Nth condition is verified, then the (N+1)th service is invoked.\n" );
			result.append( "If no condition is verified, then the last linked service is invoked." );
			break;

		case ROUTER:
			result.append( "This pattern exposes a routing service.\n" );
			result.append( "Such a service forward the received message to ONE service among a list of potential targets.\n" );
			result.append( "The routing depends on conditions, either on the message's content or on the invoked operation.\n\n" );
			result.append( "If the 1st condition is verified, then the 1st service is invoked.\n" );
			result.append( "If the 2nd condition is verified, then the 2nd service is invoked.\n" );
			result.append( "...\n" );
			result.append( "If the Nth condition is verified, then the Nth service is invoked.\n" );
			result.append( "If no condition is verified, then the last linked service is invoked." );
			break;

		case ROUTING_SLIP:
			result.append( "This pattern exposes a simple orchestration service.\n\n" );
			result.append( "The 1st linked service is invoked by passing it the original received message.\n" );
			result.append( "The 2nd linked service is invoked by passing it the response of the 1st service.\n" );
			result.append( "The 3rd linked service is invoked by passing it the response of the 2nd service.\n" );
			result.append( "...\n" );
			result.append( "The Nth linked service is invoked by passing it the response of the (N-1)th service.\n" );
			result.append( "If the last linked service returns a response, then this response is the one returned by the EIP.\n\n" );
			result.append( "Note that the output of one service must match the input of the next service in the chain.\n" );
			result.append( "Unlike BPEL, it is not possible to define transformations in the EIP.\n" );
			result.append( "You have to invoke a transformation service in the orchestration." );
			break;

		case SCATTER_GATHER:
			result.append( "This pattern exposes a service that broadcasts the requests and aggregates the responses.\n\n" );
			result.append( "When a message is received, this message is broadcasted to all the linked services.\n" );
			result.append( "The EIP service then waits for all the responses and aggregates them together.\n" );
			result.append( "The EIP service returns the aggregated response." );
			break;

		case SPLITTER:
			result.append( "This pattern exposes a splitter service.\n\n" );
			result.append( "When a message is received, it is splitted against a XPath expression.\n" );
			result.append( "More exactly, sub-messages are extracted from the request using a XPath expression.\n" );
			result.append( "All these sub-messages are forwarded to the linked service." );
			break;

		case WIRETAP:
			result.append( "This pattern exposes an observable proxy service.\n\n" );
			result.append( "When a message is received, it is always forwarded to the first linked service.\n" );
			result.append( "The second linked service will receive a part of the message exchange.\n" );
			result.append( "This part can be the first service's request or the first service's response.\n\n" );
			result.append( "Note that the second linked service is ONLY an observer: it cannot respond to the proxy." );
			break;

		default:
			// nothing
		}

		return result.toString();
	}
}
