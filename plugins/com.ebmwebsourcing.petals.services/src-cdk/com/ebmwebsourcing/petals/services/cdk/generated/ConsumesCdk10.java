package com.ebmwebsourcing.petals.services.cdk.generated;

public class ConsumesCdk10 {

	/**
	 * The name of the operation to invoke.
	 * <p>
	 * Type: QName
	 * </p>
	 */
	public static final String OPERATION = "operation";

	/**
	 * The message exchange pattern.
	 * <p>
	 * Type: Enumeration{ InOnly ; InOut ; RobustInOnly ; InOptionalOut }
	 * </p>
	 */
	public static final String MEP = "mep";

	/**
	 * The timeout used when sending messages (milliseconds).
	 * <p>
	 * Type: long
	 * </p>
	 */
	public static final String TIMEOUT = "timeout";

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

	/**
	 * Enumeration values for the 'mep' property.
	 */
	public enum Mep {
		INOPTIONALOUT("InOptionalOut"),
		INONLY("InOnly"),
		ROBUSTINONLY("RobustInOnly"),
		INOUT("InOut");

		private String value;
		private Mep( String value ) {
			this.value = value;
		}
	
		public String toString() {
			return value;
		}
	
		public static Mep parse( String s ) {
			for( Mep val : values())
				if( val.toString().equalsIgnoreCase( s ))
					return val;
			
			return null;
		}
	}
}
