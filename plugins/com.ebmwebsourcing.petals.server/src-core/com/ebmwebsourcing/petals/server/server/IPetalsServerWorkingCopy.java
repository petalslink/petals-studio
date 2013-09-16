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
