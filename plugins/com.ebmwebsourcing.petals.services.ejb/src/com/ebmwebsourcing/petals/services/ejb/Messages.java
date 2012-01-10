package com.ebmwebsourcing.petals.services.ejb;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	public static String ejbLibsText;
	public static String serverLibsText;
	public static String testConnection;

	static {
		initializeMessages("messages", Messages.class);
	}
}
