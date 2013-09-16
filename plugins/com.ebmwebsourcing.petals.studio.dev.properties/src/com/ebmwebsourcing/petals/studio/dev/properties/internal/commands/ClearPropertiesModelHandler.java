/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.studio.dev.properties.internal.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.ebmwebsourcing.petals.studio.dev.properties.internal.AbstractModelValidator;

/**
 * The default handler for the command to validate a properties model.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ClearPropertiesModelHandler extends ValidatePropertiesModelHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		if( ! this.initialized )
			setEnabled( null );

		AbstractModelValidator.clearMarkers( this.propertiesFile );
		return null;
	}
}
