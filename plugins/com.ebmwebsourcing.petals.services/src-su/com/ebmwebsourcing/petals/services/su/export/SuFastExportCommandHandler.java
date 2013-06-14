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

package com.ebmwebsourcing.petals.services.su.export;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StatusUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.utils.ExportUtils;

/**
 * The "Fast Export" command handler.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuFastExportCommandHandler extends AbstractHandler {

	private final List<IProject> suProjects = new ArrayList<IProject> ();


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute( final ExecutionEvent event ) throws ExecutionException {

		// Define the objects processing
		final List<IStatus> errors = new ArrayList<IStatus> ();
		final List<IResource> resourcesToSelect = new ArrayList<IResource> ();

		IRunnableWithProgress op = new IRunnableWithProgress() {
			@Override
			public void run( IProgressMonitor monitor )
			throws InvocationTargetException, InterruptedException {

				monitor.beginTask( "Service Unit export in progress...", IProgressMonitor.UNKNOWN );
				try {
					for( IProject p : SuFastExportCommandHandler.this.suProjects ) {
						monitor.subTask( p.getName());

						String saName = JbiUtils.createSaName( p.getName());
						String saFilePath = p.getLocation().append( saName + ".zip" ).toOSString();
						IStatus status = ExportUtils.exportSuProject( saName, saFilePath, Arrays.asList( p ), monitor );
						IResource res = ResourceUtils.getIFile( new File( saFilePath ));
						if( res != null )
							resourcesToSelect.add( res );

						monitor.worked( 5 );
						if( ! status.isOK())
							errors.add( status );
					}

				} finally {
					monitor.done();
				}
			}
		};


		// Start processing
		IWorkbench wb = PlatformUI.getWorkbench();
		IProgressService ps = wb.getProgressService();
		try {
			ps.runInUI( ps, op, null );

		} catch( InvocationTargetException e ) {
			IStatus status = StatusUtils.createStatus( e, "" );
			errors.add( status );

		} catch( InterruptedException e ) {
			// nothing
		}


		// Select the resulting files
		for( IResource res : resourcesToSelect ) {
			try {
				res.getProject().refreshLocal( IResource.DEPTH_INFINITE, null );

			} catch( CoreException e ) {
				e.printStackTrace();
			}
		}

		IResource[] resources = new IResource[ resourcesToSelect.size()];
		ResourceUtils.selectResourceInPetalsExplorer( true, resourcesToSelect.toArray( resources ));


		// Errors to display
		if( ! errors.isEmpty()) {

			IStatus[] children = new IStatus[ errors.size()];
			MultiStatus status = new MultiStatus(
						PetalsServicesPlugin.PLUGIN_ID, 0,
						errors.toArray( children ),
						(errors.size() > 1 ? "Errors" : "An error") + " occured during the export.",
						null );

			PetalsServicesPlugin.getDefault().getLog().log( status );
			ErrorDialog dlg = new ErrorDialog(
						new Shell(), "Export Errors",
						status.getMessage(), status, IStatus.ERROR );
			dlg.open();
		}

		return null;
	}
}
