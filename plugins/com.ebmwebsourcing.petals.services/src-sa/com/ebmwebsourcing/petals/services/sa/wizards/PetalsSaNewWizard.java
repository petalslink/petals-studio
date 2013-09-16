/******************************************************************************
 * Copyright (c) 2008-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.sa.wizards;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenBean;
import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.jbiproperties.PetalsSPPropertiesManager;
import com.ebmwebsourcing.petals.services.utils.PetalsServicesProjectUtils;

/**
 * The wizard to create a project for an SA Maven project (to be used with the Petals Maven plug-in).
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsSaNewWizard extends Wizard implements INewWizard {

	private PetalsSaNewWizardPage mavenPage;
	private PetalsSaSusWizardPage susPage;
	private IProject createdProject;
	private final List<String> selectedProjects = new ArrayList<String> ();


	/**
	 * Constructor for ScaProjectWizard.
	 */
	public PetalsSaNewWizard () {

		super();
		setNeedsProgressMonitor( true );
		setWindowTitle( "New Service Assembly" );

		ImageDescriptor desc = PetalsServicesPlugin.getImageDescriptor( "icons/wizban/wiz_service_assembly.png" );
		setDefaultPageImageDescriptor( desc );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {

		this.mavenPage = new PetalsSaNewWizardPage( "SaMavenProjectPage" );
		this.mavenPage.setTitle( "Service Assembly" );
		this.mavenPage.setDescription( "Define the Service Assembly properties." );
		addPage( this.mavenPage );

		this.susPage = new PetalsSaSusWizardPage( "SaSusPage" );
		this.susPage.setTitle( "Service Assembly" );
		this.susPage.setDescription( "Select the Service Unit(s) to associate with the created artifact." );
		addPage( this.susPage );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {

		final MavenBean bean = collectPagesData();

		// Define the wizard completion process.
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			@Override
			protected void execute( IProgressMonitor monitor )
			throws CoreException, InvocationTargetException, InterruptedException {

				try {
					// Create the project
					monitor.beginTask( "Creating the SA project...", 5 );
					PetalsSaNewWizard.this.createdProject = PetalsServicesProjectUtils.createSaProject(
								PetalsSaNewWizard.this.mavenPage.getSaName(),
								PetalsSaNewWizard.this.mavenPage.getProjectLocationURI(),
								bean, monitor );

					// Create the jbi.xml
					Map<String,String> suNameToComponentName = new HashMap<String,String> ();
					for( IProject p : PetalsSaNewWizard.this.susPage.getSuProjectsToAdd()) {
						Properties properties = PetalsSPPropertiesManager.getProperties( p );
						String componentName = properties.getProperty( PetalsSPPropertiesManager.COMPONENT_DEPLOYMENT_ID, null );
						if( componentName != null )
							suNameToComponentName.put( p.getName(), componentName );
					}

					String content = JbiUtils.generateJbiXmlForSA(
								PetalsSaNewWizard.this.createdProject.getName(),
								suNameToComponentName );

					IFile jbiXmlFile = PetalsSaNewWizard.this.createdProject.getFile( PetalsConstants.LOC_JBI_FILE );
					if( jbiXmlFile.exists())
						jbiXmlFile.setContents( new ByteArrayInputStream( content.getBytes()), true, true, monitor );
					else
						jbiXmlFile.create( new ByteArrayInputStream( content.getBytes()), true, monitor );

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

			// Open the jbi.xml file
			IFile jbiXmlFile = this.createdProject.getFile( PetalsConstants.LOC_JBI_FILE );
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			try {
				IDE.openEditor( page, jbiXmlFile );

			} catch( PartInitException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );
			}

			// Reveal the file in the explorer
			ResourceUtils.selectResourceInPetalsExplorer( true, jbiXmlFile );

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
			return false;
		}

		return true;
	}


	/**
	 * Collect the data from the wizard pages.
	 * @return a Maven bean with all the collected data
	 */
	private MavenBean collectPagesData() {

		MavenBean bean = new MavenBean ();
		bean.setName( this.mavenPage.getSaName());
		bean.setDescription( this.mavenPage.getSaDescription());
		bean.setArtifactId( this.mavenPage.getArtifactId());
		bean.setGroupId( this.mavenPage.getGroupId());
		bean.setVersion( this.mavenPage.getVersion());

		// Get the parent pom.xml
		MavenBean parentBean = MavenUtils.getPomParentElements();
		if( parentBean != null ) {
			bean.setParentArtifactId( parentBean.getArtifactId());
			bean.setParentGroupId( parentBean.getGroupId());
			bean.setParentVersion( parentBean.getVersion());
		}

		// Set the SU dependencies
		for( IProject p : this.susPage.getSuProjectsToAdd()) {
			MavenBean mavenBean = MavenUtils.getPomElements( p.getFile( PetalsConstants.LOC_POM_FILE ));
			bean.dependencies.add( mavenBean );
		}

		return bean;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard
	 * #init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init( IWorkbench workbench, IStructuredSelection selection ) {

		if( selection.isEmpty())
			return;

		Iterator<?> it = selection.iterator();
		while( it.hasNext()) {
			Object o = it.next();
			if( o instanceof IProject )
				this.selectedProjects.add(((IProject) o).getName());
		}
	}
}
