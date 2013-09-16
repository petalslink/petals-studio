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

package com.ebmwebsourcing.petals.services.filetransfer;

import org.eclipse.osgi.util.NLS;

/**
 * @author Vincent Zurczak - Linagora
 */
public class Messages extends NLS {

	public static String fileTransfer;
	public static String writeDirectory;
	public static String browse;
	public static String contractType;
	public static String getFiles;
	public static String writeFiles;
	public static String readDirectory;
	public static String identification;
	public static String readFileContract;
	public static String writeFileContract;

	static {
		initializeMessages("messages", Messages.class);
	}

}
