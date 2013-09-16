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
 
package com.ebmwebsourcing.petals.common.internal.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.RadioState;

import com.ebmwebsourcing.petals.common.internal.projectscnf.PetalsProjectNavigator;

/**
 * The default handler for the command "Switch Java layout".
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SwitchJavaPackageLayoutHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		if( ! HandlerUtil.matchesRadioState( event )) {
			IWorkbenchPart part = null;
			try {
				part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
			} catch( Exception e1 ) {
				// nothing
			}

			boolean flat = "flat".equals( event.getParameter( RadioState.PARAMETER_ID ));
			if( part instanceof PetalsProjectNavigator )
				((PetalsProjectNavigator) part).setFlatLayout( flat );

			HandlerUtil.updateRadioState( event.getCommand(), event.getParameter( RadioState.PARAMETER_ID ));
		}

		return null;
	}
}
