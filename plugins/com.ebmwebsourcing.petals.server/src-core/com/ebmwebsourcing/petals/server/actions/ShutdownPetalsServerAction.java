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
 
package com.ebmwebsourcing.petals.server.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.wst.server.core.IServer;

import com.ebmwebsourcing.petals.server.PetalsServerBehavior;

/**
 * The shutdown action.
 * <p>
 * The menu path is "org.eclipse.wst.server.ui.internal.cnf.controlServerSectionEnd".
 * See org.eclipse.wst.server.ui.internal.cnf.ServerActionProvider to get the other
 * constants.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ShutdownPetalsServerAction implements IObjectActionDelegate {

	private IServer server;
	private Shell shell;


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate
	 * #setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart( IAction action, IWorkbenchPart targetPart ) {
		this.shell = targetPart.getSite().getShell();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate
	 * #run(org.eclipse.jface.action.IAction)
	 */
	public void run( IAction action ) {

		if( this.server != null ) {

			boolean shutdown = MessageDialog.openQuestion( this.shell, "Shutdown the server?",
						"Are you sure you want to shutdown Petals?\n"
						+ "Every deployed artefact will be deleted by this action." );

			if( shutdown ) {
				action.setEnabled( true );
				PetalsServerBehavior behavior = (PetalsServerBehavior)
				this.server.loadAdapter( PetalsServerBehavior.class, null );

				if( behavior != null )
					behavior.stop( true, false );
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate
	 * #selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged( IAction action, ISelection selection ) {

		this.server = null;
		if( ! selection.isEmpty() && selection instanceof IStructuredSelection ) {
			Object o = ((IStructuredSelection) selection).getFirstElement();
			if( o instanceof IServer )
				this.server = (IServer) o;
			else
				this.server = null;
		}

		if( this.server != null ) {
			boolean canStop = this.server.canStop().isOK();
			action.setEnabled( canStop );

		}
	}
}
