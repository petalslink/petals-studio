package com.ebmwebsourcing.petals.services.quartz;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	public static String cronExpression;
	public static String cronHelp;
	public static String content;
	public static String couldNotOpenEditorTitle;
	public static String couldNotOpenEditorMessage;

	static {
		initializeMessages("messages", Messages.class);
	}
}
