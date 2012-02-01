/****************************************************************************
 * 
 * Copyright (c) 2010-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

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
