/****************************************************************************
 * 
 * Copyright (c) 2009-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.server.server;

import org.eclipse.core.runtime.IStatus;

/**
 * An instance of a Petals server.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public interface IPetalsServerWorkingCopy extends IPetalsServer {

	public boolean isServerInstallationDirty();
	public IStatus cleanServerInstallation();
	public boolean isRunning();
}
