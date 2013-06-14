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

package com.ebmwebsourcing.petals.services.wizards;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenBean;
import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.utils.PetalsServicesProjectUtils;
import com.ebmwebsourcing.petals.services.wizards.beans.SaImportBean;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBean;

/**
 * A wizard to import a service assembly as a set of jbiFolders.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ServiceAssemblyImportWizard extends Wizard implements IImportWizard {

	private ServiceAssemblyImportWizardPage page;
	private final List<IResource> resourcesToSelect = new ArrayList<IResource> ();


	/**
	 * Constructor.
	 */
	public ServiceAssemblyImportWizard() {
		super();
		setNeedsProgressMonitor( true );
		setWindowTitle( "Service Assembly Import" );

		ImageDescriptor desc = PetalsServicesPlugin.getImageDescriptor( "icons/wizban/wiz_sa_import.png" );
		setDefaultPageImageDescriptor( desc );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPages()
	 */
	@Override
	public void addPages() {
		this.page = new ServiceAssemblyImportWizardPage();
		addPage( this.page );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #performFinish()
	 */
	@Override
	public boolean performFinish() {

		// Define the wizard completion process.
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {

			@Override
			protected void execute( IProgressMonitor monitor )
			throws CoreException, InvocationTargetException, InterruptedException {

				try {
					doFinish( monitor );

				} catch( Exception e ) {
					throw new InvocationTargetException( e );
				} finally {
					monitor.done();
				}
			}
		};

		try {
			// Run the operation.
			getContainer().run( true, false, op );

			// Reveal the file in the explorer
			IResource[] res = new IResource[ this.resourcesToSelect.size()];
			ResourceUtils.selectResourceInPetalsExplorer( true, this.resourcesToSelect.toArray( res ));

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
			return false;
		}

		return true;
	}


	/**
	 * Imports the selected projects.
	 * @param monitor
	 * @throws CoreException
	 */
	protected void doFinish( IProgressMonitor monitor ) throws CoreException {

		monitor.beginTask( "Importing the Service Assembly...", IProgressMonitor.UNKNOWN );
		List<SaImportBean> importBeans = this.page.getImportsBeans();

		List<IProject> suDependencies = new ArrayList<IProject> ();
		List<MavenBean> suMavenBeans = new ArrayList<MavenBean>();
		for( SaImportBean saBean : importBeans ) {

			// Deal with the SU projects first
			for( SuImportBean suBean : saBean.getSuBeans()) {

				if( ! suBean.isToCreate())
					continue;

				try {
					// Delete the project?
					if( suBean.isToOverwrite()) {
						IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject( suBean.getProjectName());
						if( p.exists())
							p.delete( true, monitor );
					}

					IProject project = null;
					URI pLocUri = null;
					if( ! this.page.isAtDefaultLocation())
						pLocUri = new File( this.page.getProjectLocation(), suBean.getProjectName()).toURI();

					project = PetalsServicesProjectUtils.createSuProject(
								suBean.getProjectName(),
								pLocUri,
								suBean.getComponentName(),
								suBean.getComponentVersion(),
								suBean.getSuType(),
								false,
								monitor );
					suDependencies.add( project );

					// Create the POM artifacts for the SA
					// Elements match what is set in PetalsServicesProjectUtils#createPetalsSuProjectStructure().
					MavenBean suMavenBean = new MavenBean();
					suMavenBean.setArtifactId( project.getName());
					suMavenBeans.add( suMavenBean );

					// Copy the resources
					File jbiXmlFile = new File( suBean.getJbiXmlLocation());
					File targetFile = project.getLocation().append( PetalsConstants.LOC_JBI_FILE ).toFile();
					if( ! jbiXmlFile.exists())
						throw new IOException( "The jbi.xml does not exist." );

					IoUtils.moveFileRobustly( jbiXmlFile, targetFile, true );
					IFolder resFolder = project.getFolder( PetalsConstants.LOC_RES_FOLDER );
					File resFile = resFolder.getLocation().toFile();

					for( File fileToCopy : jbiXmlFile.getParentFile().getParentFile().listFiles()) {
						if( ! "meta-inf".equalsIgnoreCase( fileToCopy.getName()))
							IoUtils.copyFileInDirectory( fileToCopy, resFile, false );
					}

					// Keep the project to show it at the end
					this.resourcesToSelect.add( project );
					monitor.worked( 5 );
					project.refreshLocal( IResource.DEPTH_INFINITE, monitor );

				} catch( Exception e ) {
					PetalsServicesPlugin.log( e, IStatus.ERROR );
				}
			}

			// Deal with the SA project then
			if( saBean.isToCreate()) {

				// Delete the existing project?
				if( saBean.isToOverwrite()) {
					IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject( saBean.getProjectName());
					if( p.exists())
						p.delete( true, monitor );
				}

				// Create the SA project
				URI pLocUri = null;
				if( ! this.page.isAtDefaultLocation())
					pLocUri = new File( this.page.getProjectLocation(), saBean.getProjectName()).toURI();

				// Generate a basic POM
				MavenBean saMavenBean = new MavenBean();
				saMavenBean.setName( saBean.getProjectName());
				saMavenBean.setArtifactId( saMavenBean.getName());
				saMavenBean.dependencies.addAll( suMavenBeans );

				// Get the parent pom.xml
				MavenBean parentBean = MavenUtils.getPomParentElements();
				if( parentBean != null ) {
					saMavenBean.setParentArtifactId( parentBean.getArtifactId());
					saMavenBean.setParentGroupId( parentBean.getGroupId());
					saMavenBean.setParentVersion( parentBean.getVersion());
				}

				try {
					// Create the project
					IProject saProject = PetalsServicesProjectUtils.createSaProject(
								saBean.getProjectName(),
								pLocUri,
								saMavenBean,
								monitor );

					this.resourcesToSelect.add( saProject );

					// Copy the resources
					File jbiXmlFile = new File( saBean.getJbiXmlLocation());
					File targetFile = saProject.getLocation().append( PetalsConstants.LOC_JBI_FILE ).toFile();
					if( ! jbiXmlFile.exists())
						throw new IOException( "The jbi.xml does not exist." );

					IoUtils.moveFileRobustly( jbiXmlFile, targetFile, true );

					// Set the SU dependencies
					saProject.refreshLocal( IResource.DEPTH_INFINITE, monitor );
					IProjectDescription desc = saProject.getDescription();
					IProject[] projects = new IProject[ suDependencies.size()];
					desc.setReferencedProjects( suDependencies.toArray( projects ));
					saProject.setDescription( desc, null );

				} catch( CoreException e ) {
					PetalsServicesPlugin.log( e, IStatus.ERROR );

				} catch( Exception e ) {
					PetalsServicesPlugin.log( e, IStatus.ERROR );
				}
			}
		}

		monitor.worked( 1 );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard
	 * #init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init( IWorkbench workbench, IStructuredSelection selection ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#dispose()
	 */
	@Override
	public void dispose() {

		if( this.page != null )
			this.page.releaseResources();

		super.dispose();
	}
}
