/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

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
