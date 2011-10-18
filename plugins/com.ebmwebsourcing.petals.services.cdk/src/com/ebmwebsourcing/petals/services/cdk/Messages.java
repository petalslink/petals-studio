package com.ebmwebsourcing.petals.services.cdk;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	public static String wsdlLocation;
	public static String browse;
	public static String wsdlTools;
	public static String select;
	public static String invalidWSDLLocation;

	static {
		initializeMessages("messages", Messages.class);
	}
}
