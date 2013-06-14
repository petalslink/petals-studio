/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.studio;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.Parameterization;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

import com.ebmwebsourcing.petals.studio.welcome.RegistrationManager;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsStudioStartup implements IStartup {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IStartup#earlyStartup()
	 */
	public void earlyStartup() {

		// Not registered? Show the welcome page.
		if( RegistrationManager.needsRegistration()
					&& ! PetalsStudioPlugin.getDefault().isWelcomePageAlreadyShown()) {

			Display.getDefault().syncExec( new Runnable() {
				public void run() {
					ICommandService cmdService = (ICommandService)
					PlatformUI.getWorkbench().getService( ICommandService.class );
					Command runExportWiz = cmdService.getCommand( IWorkbenchCommandConstants.HELP_WELCOME );
					try {
						ParameterizedCommand parmCommand =
							new ParameterizedCommand( runExportWiz, new Parameterization[ 0 ]);

						IHandlerService ds = (IHandlerService) PlatformUI.getWorkbench().getService( IHandlerService.class );
						ds.executeCommand( parmCommand, null );

					} catch( Exception e ) {
						PetalsStudioPlugin.log( e, IStatus.WARNING );
					}
				}
			});
		}
	}
}
