/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services;

import org.eclipse.osgi.util.NLS;

/**
 * @author Vincent Zurczak - Linagora
 */
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
	public static String namespace;
	public static String localPart;
	public static String provides;
	public static String consumes;
	public static String provideTitle;
	public static String consumeTitle;
	public static String featureNotSet;
	public static String invalidClassName;

	static {
		initializeMessages("messages", Messages.class);
	}
}
