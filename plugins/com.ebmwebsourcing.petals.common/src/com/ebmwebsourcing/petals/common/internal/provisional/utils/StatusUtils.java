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
 
package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class StatusUtils {

	/**
	 * Creates a status for ErrorDialog from an exception.
	 * @param e
	 * @param msgPrefix
	 * @return
	 */
	public static IStatus createStatus( Exception e, String msgPrefix ) {

		IStatus status = null;
		if( ! StringUtils.isEmpty( e.getMessage()))
			status = new Status( IStatus.ERROR, PetalsCommonPlugin.PLUGIN_ID, e.getMessage(), e );
		else
			status = new Status( IStatus.ERROR, PetalsCommonPlugin.PLUGIN_ID, "The cause of the error was not reported.", e );

		return status;
	}
}
