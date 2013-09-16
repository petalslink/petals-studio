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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.progress.IProgressService;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.projectscnf.PetalsProjectManager;
import com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectCategory;

/**
 * Refreshes a Petals category.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RefreshCategoryHandler extends AbstractHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		// Check the target view
		IWorkbenchPart part =
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();

		if( part instanceof CommonNavigator ) {
			final CommonViewer viewer = ((CommonNavigator) part).getCommonViewer();

			// Check the selection
			ISelection s = null;
			try {
				s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
			} catch( Exception e1 ) {
				e1.printStackTrace();
			}

			if( s != null && ! s.isEmpty()) {
				final PetalsProjectCategory category = (PetalsProjectCategory) ((IStructuredSelection) s).getFirstElement();
				IRunnableWithProgress irwp = new IRunnableWithProgress() {
					public void run( IProgressMonitor monitor )
					throws InvocationTargetException, InterruptedException {

						monitor.beginTask( "Refresh in progress...", 2 );

						// Refresh the workspace
						try {
							ResourcesPlugin.getWorkspace().getRoot().refreshLocal( IResource.DEPTH_INFINITE, monitor );
							PetalsProjectManager.INSTANCE.rebuildCategoryAssociations();

						} catch( CoreException e1 ) {
							PetalsCommonPlugin.log( e1, IStatus.WARNING );

						} finally {
							// Refresh the category contents
							monitor.worked( 1 );
							Display.getDefault().asyncExec( new Runnable() {
								public void run() {
									viewer.refresh( category, true );
								}
							});
							monitor.done();
						}
					}
				};

				// Run it
				try {
					IProgressService ps = PlatformUI.getWorkbench().getProgressService();
					ps.busyCursorWhile( irwp );

				} catch( InterruptedException e ) {
					// nothing

				} catch( InvocationTargetException e ) {
					PetalsCommonPlugin.log( e, IStatus.ERROR );
				}
			}
		}

		return null;
	}
}
