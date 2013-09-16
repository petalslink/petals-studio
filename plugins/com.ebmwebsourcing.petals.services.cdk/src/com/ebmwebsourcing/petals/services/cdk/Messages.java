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

package com.ebmwebsourcing.petals.services.cdk;

import org.eclipse.osgi.util.NLS;

/**
 * @author Vincent Zurczak - Linagora
 */
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
