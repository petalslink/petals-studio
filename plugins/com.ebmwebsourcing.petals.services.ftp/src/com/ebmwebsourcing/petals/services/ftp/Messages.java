package com.ebmwebsourcing.petals.services.ftp;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	public static String destination;
	public static String host;

	static {
		initializeMessages("messages", Messages.class);
	}
}
