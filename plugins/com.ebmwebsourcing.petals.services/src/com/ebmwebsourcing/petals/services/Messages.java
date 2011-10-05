package com.ebmwebsourcing.petals.services;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	public static String services;
	public static String notValidQName;

	static {
		initializeMessages("messages", Messages.class);
	}
}
