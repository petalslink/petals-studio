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
 
package com.ebmwebsourcing.petals.services.wizards;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

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

import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.jbiproperties.PetalsSPPropertiesManager;
import com.ebmwebsourcing.petals.services.su.nature.SuNature;

/**
 * A wizard to create a studio's project from a Maven file structure for Petals.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileStructureImportWizard extends Wizard implements IImportWizard {

	private FileStructureImportWizardPage page;
	private IProject project;


	/**
	 * Constructor.
	 */
	public FileStructureImportWizard() {
		super();
		setNeedsProgressMonitor( true );
		setWindowTitle( "Petals File Structure Import" );

		ImageDescriptor desc = PetalsServicesPlugin.getImageDescriptor( "icons/wizban/importdir_wiz.png" );
		setDefaultPageImageDescriptor( desc );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPages()
	 */
	@Override
	public void addPages() {
		this.page = new FileStructureImportWizardPage();
		addPage( this.page );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.IWorkbenchWizard
	 * #init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init( IWorkbench workbench, IStructuredSelection selection ) {
		// nothing
	}


	/*
	 * (non-Jsdoc)
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
			ResourceUtils.selectResourceInPetalsExplorer( false, this.project );

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
	 * @throws IOException
	 */
	protected void doFinish( IProgressMonitor monitor ) throws CoreException, IOException {

		monitor.beginTask( "Importing the project...", IProgressMonitor.UNKNOWN );

		// Create the project
		this.project = ResourcesPlugin.getWorkspace().getRoot().getProject( this.page.getProjectName());
		if( this.page.isCopyProject()) {
			this.project.create( monitor );

			// Copy the resources
			IoUtils.copyFile(
						new File( this.page.getProjectLocation()),
						this.project.getLocation().toFile(),
						false );
		}
		else {
			IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription( this.page.getProjectName());
			projectDescription.setLocationURI( new File( this.page.getProjectLocation()).toURI());
			this.project.create( projectDescription, monitor );
		}

		// Refresh the project
		this.project.open( monitor );
		monitor.worked( 10 );

		this.project.refreshLocal( IResource.DEPTH_INFINITE, monitor );
		monitor.worked( 5 );

		// Add natures
		if( this.page.isAddJavaNature())
			JavaUtils.createJavaProject( this.project );

		if( this.page.isAddOtherNature()
					&& this.page.getOtherNature() != null ) {

			// Add the nature
			IProjectDescription description = this.project.getDescription();
			String[] natures = description.getNatureIds();
			String[] newNatures = new String[ natures.length + 1 ];

			System.arraycopy( natures, 0, newNatures, 0, natures.length );
			newNatures[ natures.length ] = this.page.getOtherNature();
			description.setNatureIds( newNatures );
			this.project.setDescription( description, null );

			// Handle SU parameters
			if( SuNature.NATURE_ID.equals( this.page.getOtherNature())) {
				Properties projectProperties = new Properties();
				projectProperties.put( PetalsSPPropertiesManager.COMPONENT_FUNCTION, this.page.getComponentFunction());
				projectProperties.put( PetalsSPPropertiesManager.COMPONENT_NAME, this.page.getComponentName());
				projectProperties.put( PetalsSPPropertiesManager.COMPONENT_VERSION, this.page.getComponentVersion());

				PetalsSPPropertiesManager.saveProperties( projectProperties, this.project );
				this.project.refreshLocal( IResource.DEPTH_INFINITE, monitor );
				monitor.worked( 5 );
			}
		}

		monitor.worked( 10 );
	}
}
