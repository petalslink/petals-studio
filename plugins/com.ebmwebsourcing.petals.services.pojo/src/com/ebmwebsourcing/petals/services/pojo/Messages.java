package com.ebmwebsourcing.petals.services.pojo;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	public static String classpath;
	public static String addLib;
	public static String removeLib;
	public static String className;
	public static String select;

	static {
		initializeMessages("messages", Messages.class);
	}
}
