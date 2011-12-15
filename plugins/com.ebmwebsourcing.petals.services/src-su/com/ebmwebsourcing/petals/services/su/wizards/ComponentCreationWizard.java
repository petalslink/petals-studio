/****************************************************************************
 *
 * Copyright (c) 2008-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlImportUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.JbiConsumePage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.JbiProvidePage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.ProjectPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.JbiFactory;

/**
 * The specialized wizard for the Petals service units.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class ComponentCreationWizard extends Wizard implements IExecutableExtension {

	/**
	 * The Petals mode associated with this instance of the wizard.
	 */
	protected PetalsMode petalsMode;

	/**
	 * The instance of the JBI object model.
	 */
	protected AbstractEndpoint endpoint;

	ProjectPage projectPage;
	private JbiProvidePage jbiProvidePage;

	private FinishServiceCreationStrategy finishStrategy;


	/**
	 * Constructor.
	 */
	public ComponentCreationWizard() {
		setNeedsProgressMonitor( true );
		setForcePreviousAndNextButtons( true );
		setDefaultPageImageDescriptor( PetalsServicesPlugin.getImageDescriptor( "icons/wizban/wiz_service_unit.png" ));
		setDialogSettings(SuWizardSettings.createDefaultSettings());
	}
	
	public void setStrategy(FinishServiceCreationStrategy strategy) {
		this.finishStrategy = strategy;
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		if (propertyName.toLowerCase().contains("provide")) {
			this.petalsMode = PetalsMode.provides;
			endpoint = JbiFactory.eINSTANCE.createProvides();
		} else {
			this.petalsMode = PetalsMode.consumes;
			endpoint = JbiFactory.eINSTANCE.createConsumes();
		}
		presetServiceValues(endpoint);
		this.finishStrategy = new CreateJBIStrategy();
	}

	@Override
	public void addPage(IWizardPage page) {
		super.addPage(page);
		page.setWizard(this);
	}
	
	@Override
	public void addPages() {
		AbstractSuPage[] pages = this.getCustomWizardPagesBeforeProject();
		if (pages != null) {
			for (IWizardPage page : pages) {
				addPage(page);
			}
		}
		
		if (finishStrategy instanceof CreateJBIStrategy) {
			projectPage = new ProjectPage();
			addPage(projectPage);
		}
		
		pages = getCustomWizardPagesAfterProject();
		if (pages != null) {
			for (IWizardPage page : pages) {
				addPage(page);
			}
		}
		
		if (petalsMode == PetalsMode.consumes) {
			addPage(new JbiConsumePage());
		} else if (petalsMode == PetalsMode.provides) {
			jbiProvidePage = new JbiProvidePage();
			addPage(jbiProvidePage);
		}
		
		pages = getCustomWizardPagesAfterJbi();
		if (pages != null) {
			for (IWizardPage page : this.getCustomWizardPagesAfterJbi()) {
				addPage(page);
			}
		}
	}


	/**
	 * Creates the Petals project and selects and open the required elements.
	 */
	@Override
	public boolean performFinish() {

		// Define the wizard completion process
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			IProject project;
			
			@Override
			protected void execute( IProgressMonitor monitor )
			throws CoreException, InterruptedException {
				try {
					project = finishStrategy.getSUProject(ComponentCreationWizard.this, monitor);
					doFinish(monitor );

				} catch( Exception e ) {
					PetalsServicesPlugin.log( e, IStatus.ERROR );

				} finally {
					project.refreshLocal( IResource.DEPTH_INFINITE, null );
					monitor.done();
				}
			}
		};

		// Run it and perform the UI actions
		try {
			// Run the operation.
			getContainer().run( true, false, op );

		} catch( InterruptedException e ) {
			// nothing

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}

		return true;
	}

	/**
	 * Creates the project.
	 * @param elementsToSelect the elements to select (not null)
	 * @param monitor the progress monitor
	 * @throws CoreException
	 * @throws IOException
	 */
	private void doFinish(final IProgressMonitor monitor ) throws Exception {
		finishStrategy.finishWizard(this, endpoint, monitor);
		
		// Common stuff
		IProject project = finishStrategy.getSUProject(this, monitor);
		IFile jbiFile = project.getFile( PetalsConstants.LOC_JBI_FILE );
		
		// Import the WSDL and update the jbi.xml file in consequence
		final IFolder resourceDirectory = project.getFolder( PetalsConstants.LOC_RES_FOLDER );
		if( petalsMode == PetalsMode.provides) {
			importWSDLFileInProvideSUProject(monitor, jbiFile, resourceDirectory);
		}

		monitor.subTask( "Importing additional files..." );
		importAdditionalFiles( resourceDirectory, monitor );
		monitor.worked( 1 );

		monitor.subTask( "Performing extra-actions..." );
		resourceDirectory.refreshLocal( IResource.DEPTH_INFINITE, monitor );
		performLastActions(resourceDirectory, endpoint, monitor);
	}

	public void importWSDLFileInProvideSUProject(IProgressMonitor monitor, IFile jbiFile, IFolder resourceDirectory) {
		File wsdlFile = null;
		monitor.subTask( "Importing the WSDL..." );

		String wsdlFileLocation = jbiProvidePage.getWsdlUrl();
		if( jbiProvidePage.isImportWsdl() && wsdlFileLocation != null ) {
			WsdlImportUtils wsdlImportUtils = new WsdlImportUtils();
			Map<String,File> fileToUrl = wsdlImportUtils.importWsdlOrXsdAndDependencies( resourceDirectory.getLocation().toFile(), jbiProvidePage.getWsdlUrl());
			wsdlFile = fileToUrl.get( jbiProvidePage.getWsdlUrl());
			wsdlFileLocation = IoUtils.getRelativeLocationToFile(jbiFile.getLocation().toFile(), wsdlFile );
			monitor.subTask( "Updating the WSDL..." );
			WsdlUtils.INSTANCE.updateEndpointNameInWsdl( wsdlFile, endpoint.getServiceName(), endpoint.getEndpointName());
			monitor.worked( 1 );
		}

		// TODO: set the WSDL URL
		monitor.worked( 1 );
	}


	/**
	 * Creates the file and write its content.
	 * @param targetFile the target file
	 * @param content the content to write in the file
	 * @param monitor a progress monitor
	 */
	protected void createFile( IFile targetFile, String content, IProgressMonitor monitor ) {
		try {
			if( content == null )
				content = "Result was null. Make your code correct.";

			ByteArrayInputStream inputStream = new ByteArrayInputStream( content.getBytes());
			if( ! targetFile.exists())
				targetFile.create( inputStream, true, monitor );
			else
				targetFile.setContents( inputStream, true, true, monitor );

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}
	}


	public PetalsMode getPetalsMode() {
		return this.petalsMode;
	}
	
	public AbstractEndpoint getNewlyCreatedEndpoint() {
		return this.endpoint;
	}
	
	// Component business methods
	protected abstract void presetServiceValues(AbstractEndpoint endpoint);
	protected abstract AbstractSuPage[] getCustomWizardPagesAfterJbi();
	protected abstract AbstractSuPage[] getCustomWizardPagesAfterProject();
	protected abstract AbstractSuPage[] getCustomWizardPagesBeforeProject();
	protected abstract IStatus importAdditionalFiles(IFolder resourceDirectory, IProgressMonitor monitor);
	protected abstract IStatus performLastActions(IFolder resourceDirectory, AbstractEndpoint newlyCreatedEndpoint, IProgressMonitor monitor);
	protected abstract boolean isJavaProject() ;
	public abstract ComponentVersionDescription getComponentVersionDescription();

}
