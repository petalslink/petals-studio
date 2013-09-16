/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.server.handlers;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public interface IPetalsVersionHandler {

	IStatus verifyInstallPath( IPath installPath );
	String getRuntimeClass();
	List<File> getRuntimeClasspath( IPath installPath );
	boolean isServerInstallationDirty( IPath installPath );
	IStatus cleanServerInstallation( IPath installPath );
}
