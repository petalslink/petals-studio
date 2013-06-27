package com.ebmwebsourcing.petals.services.cdk;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.ebmwebsourcing.petals.services.cdk"; //$NON-NLS-1$

	public static String wsdlLocation;
	public static String browse;
	public static String wsdlTools;
	public static String select;
	public static String invalidWSDLLocation;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
