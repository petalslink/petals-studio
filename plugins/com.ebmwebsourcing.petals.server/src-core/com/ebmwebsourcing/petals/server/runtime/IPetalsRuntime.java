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
 
package com.ebmwebsourcing.petals.server.runtime;

import java.io.File;
import java.util.List;

import org.eclipse.jst.server.core.IJavaRuntime;

/**
 * The Petals runtime (information required for a Petals server).
 * @author Vincent Zurczak - EBM WebSourcing
 */
public interface IPetalsRuntime extends IJavaRuntime {

	/**
	 * Returns the runtime classpath that is used by this runtime.
	 * @return the runtime classpath
	 */
	public List<File> getRuntimeClasspath();
}
