/****************************************************************************
 * 
 * Copyright (c) 2009-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

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
