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
import java.net.URISyntaxException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.bpel.common.wsdl.importhelpers.WsdlImportHelper;
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
import org.xml.sax.SAXException;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.services.Messages;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.JbiConsumePage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.JbiProvidePage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.ProjectPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.JbiFactory;

/**
 * The specialized wizard for the Petals service units.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractServiceUnitWizard extends Wizard implements IExecutableExtension {

	/**
	 * The Petals mode associated with this instance of the wizard.
	 */
	protected PetalsMode petalsMode;

	/**
	 * The instance of the JBI object model.
	 */
	protected AbstractEndpoint endpoint;

	protected ProjectPage projectPage;
	protected JbiProvidePage jbiProvidePage;

	protected FinishServiceCreationStrategy finishStrategy;
	protected SuWizardSettings settings;


	/**
	 * Constructor.
	 */
	public AbstractServiceUnitWizard() {
		setNeedsProgressMonitor( true );
		setForcePreviousAndNextButtons( true );
		setDefaultPageImageDescriptor( PetalsServicesPlugin.getImageDescriptor( "icons/wizban/wiz_service_unit.png" ));
		this.settings = new SuWizardSettings();
	}


	/**
	 * Sets the strategy.
	 * @param strategy
	 */
	public void setStrategy(FinishServiceCreationStrategy strategy) {
		this.finishStrategy = strategy;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IExecutableExtension
	 * #setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
	 */
	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		if (propertyName.toLowerCase().contains("provide")) {
			this.petalsMode = PetalsMode.provides;
			this.endpoint = JbiFactory.eINSTANCE.createProvides();
			setWindowTitle(Messages.provideTitle);

		} else {
			this.petalsMode = PetalsMode.consumes;
			this.endpoint = JbiFactory.eINSTANCE.createConsumes();
			setWindowTitle(Messages.consumeTitle);
		}

		presetServiceValues(this.endpoint);
		this.finishStrategy = new CreateJBIStrategy();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	@Override
	public void addPage(IWizardPage page) {
		super.addPage(page);
		page.setWizard(this);

		String title = this.petalsMode == PetalsMode.provides ? "Petals Service Provider" : "Petals Service Consumer";
		title += " (" + getComponentVersionDescription().getComponentAlias() + ")";
		page.setTitle( title );

		if( page.getDescription() == null ) {
			if (this.petalsMode == PetalsMode.consumes)
				page.setDescription(getComponentVersionDescription().getConsumeDescription());
			else if (this.petalsMode == PetalsMode.provides)
				page.setDescription(getComponentVersionDescription().getProvideDescription());
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPages()
	 */
	@Override
	public void addPages() {
		AbstractSuWizardPage[] pages = this.getCustomWizardPagesBeforeProject();
		if (pages != null) {
			for (IWizardPage page : pages)
				addPage( page );
		}

		if( this.settings.showJbiPage) {
			if (this.petalsMode == PetalsMode.consumes)
				addPage( new JbiConsumePage());

			else if (this.petalsMode == PetalsMode.provides) {
				this.jbiProvidePage = new JbiProvidePage();
				addPage(this.jbiProvidePage);
			}
		}

		if( this.finishStrategy instanceof CreateJBIStrategy ) {
			this.projectPage = new ProjectPage();
			addPage( this.projectPage );
		}

		pages = getCustomWizardPagesAfterProject();
		if( pages != null ) {
			for (IWizardPage page : pages)
				addPage(page);
		}

		pages = getCustomWizardPagesAfterJbi();
		if( pages != null ) {
			for (IWizardPage page : this.getCustomWizardPagesAfterJbi())
				addPage(page);
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
					this.project = AbstractServiceUnitWizard.this.finishStrategy.getSUProject(AbstractServiceUnitWizard.this, monitor);
					doFinish(monitor );

				} catch( Exception e ) {
					PetalsServicesPlugin.log( e, IStatus.ERROR );

				} finally {
					this.project.refreshLocal( IResource.DEPTH_INFINITE, null );
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

		// Common stuff
		IProject project = this.finishStrategy.getSUProject(this, monitor);
		IFile jbiFile = project.getFile( PetalsConstants.LOC_JBI_FILE );

		// Import the WSDL and update the jbi.xml file in consequence
		final IFolder resourceDirectory = project.getFolder( PetalsConstants.LOC_RES_FOLDER );
		if( this.petalsMode == PetalsMode.provides && this.settings.showWsdl && this.settings.showJbiPage) {
			importWSDLFileInProvideSUProject(monitor, jbiFile, resourceDirectory);
		}

		monitor.subTask( "Importing additional files..." );
		importAdditionalFiles( resourceDirectory, monitor );
		monitor.worked( 1 );

		monitor.subTask( "Performing extra-actions..." );
		resourceDirectory.refreshLocal( IResource.DEPTH_INFINITE, monitor );
		performLastActions(resourceDirectory, this.endpoint, monitor);

		// last action so that previous ones can keep on modifying JBI
		this.finishStrategy.finishWizard(this, this.endpoint, monitor);
	}


	/**
	 * Imports a WSDL file in the created project.
	 * @param monitor
	 * @param jbiFile
	 * @param resourceDirectory
	 */
	public void importWSDLFileInProvideSUProject(IProgressMonitor monitor, IFile jbiFile, IFolder resourceDirectory) {

		File wsdlFile = null;
		monitor.subTask( "Importing the WSDL..." );
		String wsdlFileLocation = getSelectedWSDLForProvide();
		if( wsdlFileLocation != null && this.jbiProvidePage.isImportWsdl()) {

			try {
				WsdlImportHelper helper = new WsdlImportHelper();
				Map<String,File> fileToUrl = helper.importWsdlOrXsdAndDependencies(
						resourceDirectory.getLocation().toFile(),
						getSelectedWSDLForProvide());

				wsdlFile = fileToUrl.get( getSelectedWSDLForProvide());
				wsdlFileLocation = IoUtils.getRelativeLocationToFile( jbiFile.getLocation().toFile(), wsdlFile );
				monitor.subTask( "Updating the WSDL..." );
				WsdlUtils.INSTANCE.updateEndpointNameInWsdl( wsdlFile, this.endpoint.getServiceName(), this.endpoint.getEndpointName());

			} catch( ParserConfigurationException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );

			} catch( IOException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );

			} catch( URISyntaxException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );

			} catch( SAXException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );
			}

			monitor.worked( 1 );
		}
	}


	/**
	 * @return the WSDL URL or null if it was not provided
	 */
	public String getSelectedWSDLForProvide() {
		return this.jbiProvidePage != null ? this.jbiProvidePage.getWsdlUrl() : null;
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


	/**
	 * @return the Petals mode
	 */
	public PetalsMode getPetalsMode() {
		return this.petalsMode;
	}


	/**
	 * @return the newly created end-point (provides or consumes)
	 */
	public AbstractEndpoint getNewlyCreatedEndpoint() {
		return this.endpoint;
	}


	/**
	 * @return the wizard settings
	 */
	public SuWizardSettings getSettings() {
		return this.settings;
	}

	// Component business methods
	protected abstract void presetServiceValues(AbstractEndpoint endpoint);
	protected abstract AbstractSuWizardPage[] getCustomWizardPagesAfterJbi();
	protected abstract AbstractSuWizardPage[] getCustomWizardPagesAfterProject();
	protected abstract AbstractSuWizardPage[] getCustomWizardPagesBeforeProject();
	protected abstract IStatus importAdditionalFiles(IFolder resourceDirectory, IProgressMonitor monitor);
	protected abstract IStatus performLastActions(IFolder resourceDirectory, AbstractEndpoint newlyCreatedEndpoint, IProgressMonitor monitor);
	protected abstract boolean isJavaProject() ;
	public abstract ComponentVersionDescription getComponentVersionDescription();

}
