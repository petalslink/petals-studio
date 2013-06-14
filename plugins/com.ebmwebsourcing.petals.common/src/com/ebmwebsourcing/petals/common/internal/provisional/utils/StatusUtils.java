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
