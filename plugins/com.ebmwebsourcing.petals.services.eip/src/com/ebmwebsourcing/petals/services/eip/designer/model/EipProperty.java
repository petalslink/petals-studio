/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.model;

/**
 * Properties (or fields) an EIP may support.
 * <p>
 * These properties are defined here because most of them are
 * shared among Petals EIPs.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public enum EipProperty {

	/**
	 * To use if an EIP only supports 1 XPath condition.
	 */
	TEST,

	/**
	 * To use if an EIP supports the fault-robust property.
	 */
	FAULT_ROBUST,

	/**
	 * To use if an EIP supports the exception-robust property.
	 */
	EXCEPTION_ROBUST,

	/**
	 * To use if an EIP supports the fault-to-exception property.
	 */
	FAULT_TO_EXCEPTION,

	/**
	 * To use if an EIP supports the attachment property.
	 */
	ATTACHMENT,

	/**
	 * To use if an EIP supports the aggregator-correlation property.
	 */
	AGGREGATOR_CORRELATION,

	/**
	 * To use if an EIP supports the wiretap-way property.
	 */
	WIRETAP_WAY,

	/**
	 * A property that is not really in Petals EIP SE, but
	 * that is associated with the router pattern. It defines the
	 * type of condition used to route messages.
	 */
	ROUTING_CRITERIA;


	/**
	 * Routing is based on the name of the invoked operation.
	 * <p>
	 * If no routing criteria was defined, {@link #ROUTING_CRITERIA_BY_CONTENT} is used.
	 * </p>
	 */
	public final static String ROUTING_CRITERIA_BY_OPERATION = "RouteByOperation";

	/**
	 * Routing is made on the message's content (XPath assertion).
	 * <p>
	 * Default if no routing criteria was defined.
	 * </p>
	 */
	public final static String ROUTING_CRITERIA_BY_CONTENT = "RouteByContent";


	/**
	 * @return a label for display
	 */
	public String getDisplayLabel() {

		String result = null;
		switch( this ) {
		case AGGREGATOR_CORRELATION:
			result = "Correlation Predicate";
			break;
		case ATTACHMENT:
			result = "Split Attachment (instead of the payload)";
			break;
		case EXCEPTION_ROBUST:
			result = "Interrupt on Exception received";
			break;
		case FAULT_ROBUST:
			result = "Interrupt on Fault received";
			break;
		case FAULT_TO_EXCEPTION:
			result = "Convert received Faults in Exceptions";
			break;
		case TEST:
			result = "XPath Condition";
			break;
		case WIRETAP_WAY:
			result = "Wiretap Way";
			break;
		case ROUTING_CRITERIA:
			result = "Route Messages by";
			break;
		}

		return result;
	}


	/**
	 * @return a text to display as a tool tip
	 */
	public String getTooltipText() {

		String result = null;
		switch( this ) {
		case AGGREGATOR_CORRELATION:
			result = "A XPath predicate which determines whether the message should be aggregated or not";
			break;
		case ATTACHMENT:
			result = "True to split message attachments or false to split the message's payload";
			break;
		case EXCEPTION_ROBUST:
			result = "True to stop the processing when an exception occurs, false to go on";
			break;
		case FAULT_ROBUST:
			result = "True to stop the processing when a fault occurs, false to go on";
			break;
		case FAULT_TO_EXCEPTION:
			result = "True to convert received Faults in Exceptions";
			break;
		case TEST:
			result = "A XPath condition";
			break;
		case WIRETAP_WAY:
			result = "A parameter to determine which message will be sent to the monitoring service";
			break;
		case ROUTING_CRITERIA:
			result = "The criteria that defines how messages are routed";
			break;
		}

		return result;
	}


	/**
	 * Gets the possible values for a property.
	 * @return null if no choice is available, an array otherwise
	 */
	public EipPropertyType getType() {

		EipPropertyType result;
		switch( this ) {
		case ATTACHMENT:
			result = EipPropertyType.BOOLEAN;
			break;
		case EXCEPTION_ROBUST:
			result = EipPropertyType.BOOLEAN;
			break;
		case FAULT_ROBUST:
			result = EipPropertyType.BOOLEAN;
			break;
		case FAULT_TO_EXCEPTION:
			result = EipPropertyType.BOOLEAN;
			break;
		case WIRETAP_WAY:
			result = EipPropertyType.WIRETAP_ENUM;
			break;
		case TEST:
			result = EipPropertyType.XPATH;
			break;
		case AGGREGATOR_CORRELATION:
			result = EipPropertyType.XPATH;
			break;
		case ROUTING_CRITERIA:
			result = EipPropertyType.ROUTING_CRITERIA_ENUM;
			break;
		default:
			result = EipPropertyType.FREE;
		}

		return result;
	}


	/**
	 * Gets the default value for a property.
	 * @return null if no choice is available, an array otherwise
	 */
	public String getDefaultValue() {

		String result = null;
		switch( this ) {
		case ATTACHMENT:
			result = "False";
			break;
		case EXCEPTION_ROBUST:
			result = "True";
			break;
		case FAULT_ROBUST:
			result = "True";
			break;
		case FAULT_TO_EXCEPTION:
			result = "False";
			break;
		}

		return result;
	}


	/**
	 * The types of the properties to make UI generation as automatic as possible.
	 */
	public enum EipPropertyType {
		XPATH, BOOLEAN, WIRETAP_ENUM, FREE, ROUTING_CRITERIA_ENUM;
	}


	/**
	 * @param propertyName a property name (can be null)
	 * @return true if this property is an enum value, false otherwise
	 */
	public static boolean isKnownProperty( String propertyName ) {

		for( EipProperty property : values()) {
			if( property.toString().equals( propertyName ))
				return true;
		}

		return false;
	}
}
