/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.export;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.utils.ExportUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuExportWizard extends Wizard implements IExportWizard {

	private SuExportWizardPage page;
	private IStructuredSelection selection;
	private final List<IStatus> status = new ArrayList<IStatus>();
	private final List<IResource> resourcesToSelect = new ArrayList<IResource>();


	public enum SuExportMode {
		SEPARATE_IN_PROJECT, ALL_IN_ONE, SEPARATE_IN_DIRECTORY;
	}


	/**
	 * Constructor.
	 */
	public SuExportWizard() {
		super();
		setNeedsProgressMonitor( true );
		setWindowTitle( "Service Unit Export" );

		ImageDescriptor desc = PetalsServicesPlugin.getImageDescriptor( "icons/wizban/wiz_su_export.png" );
		setDefaultPageImageDescriptor( desc );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPages()
	 */
	@Override
	public void addPages() {
		this.page = new SuExportWizardPage( this.selection );
		addPage( this.page );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #performFinish()
	 */
	@Override
	public boolean performFinish() {

		IRunnableWithProgress op = getExportOperation();
		try {
			getContainer().run( true, false, op );
			if( this.page.getExportMode() == SuExportMode.SEPARATE_IN_PROJECT ) {
				IResource[] res = new IResource[ this.resourcesToSelect.size()];
				ResourceUtils.selectResourceInPetalsExplorer( false, this.resourcesToSelect.toArray( res ));
			}

		} catch( InvocationTargetException e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
			MessageDialog.openError( getShell(), "Export Error", "An error occurred during the export." );

		} catch( InterruptedException e ) {
			// nothing
		}

		if( ! this.status.isEmpty()) {
			IStatus[] children = new IStatus[ this.status.size()];
			MultiStatus s = new MultiStatus(
						PetalsServicesPlugin.PLUGIN_ID, 0,
						this.status.toArray( children ),
						this.status.size() + " error" + (this.status.size() == 1 ? "" : "s") + " were found.",
						null );

			PetalsServicesPlugin.getDefault().getLog().log( s );
			ErrorDialog.openError( getShell(), "Export Error(s)", "Errors occurred during the export.", s );
		}

		return true;
	}


	/**
	 * 
	 * @return
	 */
	private IRunnableWithProgress getExportOperation() {

		IRunnableWithProgress op = null;
		switch( this.page.getExportMode()) {
		case SEPARATE_IN_PROJECT:
			op = new WorkspaceModifyOperation() {

				@Override
				protected void execute( IProgressMonitor monitor )
				throws CoreException, InvocationTargetException, InterruptedException {

					monitor.beginTask( "Export in progress...", IProgressMonitor.UNKNOWN );
					try {
						Collection<IProject> projects = SuExportWizard.this.page.getSuProjectsToExport();
						for( IProject p :projects ) {
							if( monitor.isCanceled())
								break;

							monitor.subTask( p.getName());
							String saName = JbiUtils.createSaName( p.getName());
							String saFilePath = p.getLocation().append( saName + ".zip" ).toString();
							IStatus s = ExportUtils.exportSuProject(
										saName,
										saFilePath,
										Arrays.asList( p ),
										monitor );

							if( ! s.isOK())
								SuExportWizard.this.status.add( s );

							monitor.worked( 2 );
							try {
								p.refreshLocal( IResource.DEPTH_ONE, monitor );
								SuExportWizard.this.resourcesToSelect.add( p.getFile( saName + ".zip" ));

							} catch( Exception e ) {
								e.printStackTrace();
							}
						}
					} finally {
						monitor.done();
					}
				}
			};
			break;

		case SEPARATE_IN_DIRECTORY:
			op = new IRunnableWithProgress() {
				public void run( IProgressMonitor monitor ) {

					monitor.beginTask( "Export in progress...", IProgressMonitor.UNKNOWN );
					try {
						Collection<IProject> projects = SuExportWizard.this.page.getSuProjectsToExport();
						for( IProject p :projects ) {
							if( monitor.isCanceled())
								break;

							monitor.subTask( p.getName());
							String saName = JbiUtils.createSaName( p.getName());
							String saFilePath = new File( SuExportWizard.this.page.getOutputFile(), saName + ".zip" ).getAbsolutePath();
							IStatus s = ExportUtils.exportSuProject(
										saName,
										saFilePath,
										Arrays.asList( p ),
										monitor );

							if( ! s.isOK())
								SuExportWizard.this.status.add( s );

							monitor.worked( 2 );
						}
					} finally {
						monitor.done();
					}
				}
			};
			break;

		case ALL_IN_ONE:
			op = new IRunnableWithProgress() {
				public void run( IProgressMonitor monitor ) {

					monitor.beginTask( "Export in progress...", IProgressMonitor.UNKNOWN );
					try {
						Collection<IProject> projects = SuExportWizard.this.page.getSuProjectsToExport();
						String saFilePath = SuExportWizard.this.page.getOutputFile().getAbsolutePath();
						String saName = new Path( saFilePath ).removeFileExtension().lastSegment();
						IStatus s = ExportUtils.exportSuProject( saName, saFilePath, projects, monitor );
						if( ! s.isOK())
							SuExportWizard.this.status.add( s );

						monitor.worked( 5 );

					} finally {
						monitor.done();
					}
				}
			};
			break;
		}

		return op;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard
	 * #init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init( IWorkbench workbench, IStructuredSelection selection ) {
		this.selection = selection;
	}
}
