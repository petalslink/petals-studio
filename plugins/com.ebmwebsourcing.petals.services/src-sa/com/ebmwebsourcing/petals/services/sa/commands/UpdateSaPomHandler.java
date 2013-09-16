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
 
package com.ebmwebsourcing.petals.services.sa.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;

/**
 * The default handler for the command "Update the project's POM".
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class UpdateSaPomHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		ISelection s = null;
		try {
			s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
		} catch( Exception e1 ) {
			// nothing
		}

		if( s instanceof IStructuredSelection && ! s.isEmpty()) {
			try {
				Object o = ((IStructuredSelection) s).getFirstElement();
				IProject saProject;
				if( o instanceof IProject
							&& (saProject = (IProject) o).isAccessible()
							&& ServiceProjectRelationUtils.isSaProject( saProject )) {

					// Find the referenced SU projects
					List<File> suPoms = new ArrayList<File>();
					for( IProject p : saProject.getReferencedProjects()) {
						if( ! p.isAccessible()
									|| ! ServiceProjectRelationUtils.isSuProject( p ))
							continue;

						IFile pomFile = p.getFile( PetalsConstants.LOC_POM_FILE );
						if( pomFile.exists())
							suPoms.add( pomFile.getLocation().toFile());
					}

					// Update the POM dependencies
					IFile saPomFile = saProject.getFile( PetalsConstants.LOC_POM_FILE );
					MavenUtils.setPomDependencies( saPomFile.getLocation().toFile(), suPoms );
					saPomFile.refreshLocal( IResource.DEPTH_ZERO, new NullProgressMonitor());
				}

			} catch( CoreException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );
			}
		}

		return null;
	}
}
