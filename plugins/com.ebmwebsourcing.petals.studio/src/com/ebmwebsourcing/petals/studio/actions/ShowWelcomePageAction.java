/****************************************************************************
 * 
 * Copyright (c) 2009-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.studio.actions;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.Parameterization;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ShowWelcomePageAction implements IWorkbenchWindowActionDelegate {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate
	 * #dispose()
	 */
	public void dispose() {
		// nothing
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate
	 * #init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
		// nothing
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate
	 * #run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {

		ICommandService cmdService = (ICommandService)
		PlatformUI.getWorkbench().getService( ICommandService.class );

		/*
		 * Hint: use the simple CheatSheet editor to browse the available commands.s
		 */
		Command runExportWiz = cmdService.getCommand( "org.eclipse.ui.help.quickStartAction" ); //$NON-NLS-1$

		try {
			ParameterizedCommand parmCommand =
				new ParameterizedCommand( runExportWiz, new Parameterization[ 0 ]);

			IHandlerService ds = (IHandlerService) PlatformUI.getWorkbench().getService( IHandlerService.class );
			ds.executeCommand( parmCommand, null );

		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate
	 * #selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		// nothing
	}
}
