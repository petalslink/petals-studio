package com.ebmwebsourcing.petals.services.cdk.generated;

public class ProvidesCdk10 {

	/**
	 * The WSDL location.
	 * <p>
	 * It can be an URL or the relative location of the file from the root of the service-unit.
	 * Type: String, required, nullable
	 * </p>
	 */
	public static final String WSDL = "wsdl";

	/**
	 * The timeout used if this services sends messages (milliseconds).
	 * <p>
	 * Type: long
	 * </p>
	 */
	public static final String TIMEOUT = "timeout";

	/**
	 * Defines whether the jbi.
	 * <p>
	 * xml file must be validated against the WSDL definition.
	 * Type: boolean
	 * </p>
	 */
	public static final String VALIDATE_WSDL = "validate-wsdl";

	/**
	 * Defines if the security subject will be forwarded from IN message to OUT message.
	 * <p>
	 * Type: boolean
	 * </p>
	 */
	public static final String FORWARD_SECURITY_SUBJECT = "forward-security-subject";

	/**
	 * Defines if properties will be forwarded from IN message to OUT message.
	 * <p>
	 * Type: boolean
	 * </p>
	 */
	public static final String FORWARD_MESSAGE_PROPERTIES = "forward-message-properties";

	/**
	 * Defines if attachments will be forwarded from IN message to OUT message.
	 * <p>
	 * Type: boolean
	 * </p>
	 */
	public static final String FORWARD_ATTACHMENTS = "forward-attachments";

	/**
	 * Parameters for the future
	 * <p>
	 * A list of interceptors invoked on request sending.
	 * Type: String
	 * </p>
	 */
	public static final String INTERCEPTOR_SEND = "interceptor.send";

	/**
	 * A list of interceptors invoked on message accepting.
	 * <p>
	 * Type: String
	 * </p>
	 */
	public static final String INTERCEPTOR_ACCEPT = "interceptor.accept";

	/**
	 * A list of interceptors invoked on response sending.
	 * <p>
	 * Type: String
	 * </p>
	 */
	public static final String INTERCEPTOR_SEND_RESPONSE = "interceptor.send-response";

	/**
	 * A list of interceptors invoked on response accepting.
	 * <p>
	 * Type: String
	 * </p>
	 */
	public static final String INTERCEPTOR_ACCEPT_RESPONSE = "interceptor.accept-response";

	/**
	 * Define static properties on an exchange.
	 * <p>
	 * Type: String
	 * </p>
	 */
	public static final String EXCHANGE_PROPERTY_KEY = "exchange.property.key";

	/**
	 * Define static properties on a message (IN, OUT, FAULT).
	 * <p>
	 * Type: String
	 * </p>
	 */
	public static final String MESSAGE_PROPERTY_KEY = "message.property.key";
}
