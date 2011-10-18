package com.ebmwebsourcing.petals.services;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	public static String services;
	public static String notValidQName;
	public static String consumeDescription;
	public static String provideDescription;
	public static String confimeRemoveEndpointMessage;
	public static String confimeRemoveEndpointTitle;
	public static String version;
	public static String addService;
	public static String serviceName;
	public static String serviceMode;
	public static String selectComponent;
	public static String createProvidesSU;
	public static String createProvidesSUDescription;
	public static String createConsumesSU;
	public static String createConsumesSUDescription;
	public static String edit;
	public static String interfaceQName;
	public static String serviceQName;

	static {
		initializeMessages("messages", Messages.class);
	}
}
