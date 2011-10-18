package com.ebmwebsourcing.petals.services.filetransfer;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	
	public static String fileTransfer;
	public static String writeDirectory;
	public static String browse;
	public static String contractType;
	public static String getFiles;
	public static String writeFiles;
	public static String readDirectory;
	public static String identification;

	static {
		initializeMessages("messages", Messages.class);
	}

}
