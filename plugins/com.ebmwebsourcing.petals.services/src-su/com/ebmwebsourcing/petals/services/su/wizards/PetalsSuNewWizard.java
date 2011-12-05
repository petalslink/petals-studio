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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlImportUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler.CustomPagePosition;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.ChoicePage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.JbiAbstractPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.JbiConsumePage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.JbiProvidePage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.ProjectPage;
import com.ebmwebsourcing.petals.services.utils.PetalsServicesProjectUtils;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.JbiFactory;
import com.sun.java.xml.ns.jbi.Provides;
import com.sun.java.xml.ns.jbi.Services;

/**
 * The specialized wizard for the Petals service units.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsSuNewWizard extends Wizard implements INewWizard, IExecutableExtension {

	/**
	 * The Petals mode associated with this instance of the wizard.
	 */
	private PetalsMode petalsMode;

	/**
	 * The object which manages the pages.
	 */
	private final PageManager pageManager = new PageManager();

	/**
	 * The instance of the JBI object model.
	 */
	private Jbi jbiInstance;

	/**
	 * The wizard handler for the selected component version.
	 */
	private ComponentWizardHandler wizardHandler;



	/**
	 * Constructor.
	 */
	public PetalsSuNewWizard() {
		super();
		setNeedsProgressMonitor( true );
		setForcePreviousAndNextButtons( true );
		setDefaultPageImageDescriptor( PetalsServicesPlugin.getImageDescriptor( "icons/wizban/wiz_service_unit.png" ));
	}


	/**
	 * @return the jbiInstance
	 */
	public Jbi getJbiInstance() {
		return this.jbiInstance;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IExecutableExtension
	 * #setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
	 */
	@Override
	public void setInitializationData( IConfigurationElement config, String propertyName, Object data )
	throws CoreException {
		this.petalsMode = "provides".equalsIgnoreCase( String.valueOf( data )) ? PetalsMode.provides : PetalsMode.consumes;
		setWindowTitle( isProvides() ? "Petals Service" : "Petals Service Consumer" );

		this.jbiInstance = JbiFactory.eINSTANCE.createJbi();
		this.jbiInstance.setServices( JbiFactory.eINSTANCE.createServices());
	}


	/**
	 * Adds the first page of the wizard.
	 */
	@Override
	public void addPages() {
		addPage( new ChoicePage( this.petalsMode ));
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	@Override
	public void addPage( IWizardPage page ) {

		if( page != null ) {
			String title = getWindowTitle();
			if( this.wizardHandler != null )
				title += " (" + this.wizardHandler.getComponentVersionDescription().getComponentAlias() + ")";

			page.setTitle( title );
			if( page instanceof AbstractSuPage )
				this.pageManager.addPage((AbstractSuPage) page );

			super.addPage( page );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #getNextPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	@Override
	public IWizardPage getNextPage( IWizardPage page ) {

		if( page instanceof AbstractSuPage ) {
			AbstractSuPage suPage = (AbstractSuPage) page;
			return this.pageManager.getNextPage( suPage );
		}

		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #getPage(java.lang.String)
	 */
	@Override
	public IWizardPage getPage( String name ) {
		return this.pageManager.getPage( name );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard
	 * #init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init( IWorkbench workbench, IStructuredSelection selection ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #canFinish()
	 */
	@Override
	public boolean canFinish() {

		boolean canFinish = false;
		if( this.getContainer().getCurrentPage() != null ) {
			boolean isLastPage = getNextPage( getContainer().getCurrentPage()) == null;
			boolean isComplete = this.getContainer().getCurrentPage().isPageComplete();
			canFinish = isComplete && isLastPage;
		}

		return canFinish;
	}


	/**
	 * Defines and registers all the wizard pages after the version page.
	 * <p>
	 * This method should be called by the version page each time a version is chosen.
	 * </p>
	 */
	private void registerPagesAfterChoicePage() {

		// Clear all the pages, except the choice page
		this.pageManager.clearAllPagesExcept( ChoicePage.PAGE_NAME );

		// Update the dialog settings: default < component override < context override
		IDialogSettings settings = SuWizardSettings.createDefaultSettings();
		for( Map.Entry<String,String> entry : this.wizardHandler.getOverridenWizardSettings().entrySet()) {
			settings.put( entry.getKey(), entry.getValue());
		}

		setDialogSettings( settings );

		// JBI page
		registerCustomPages( CustomPagePosition.beforeJbiPage );
		boolean displayJbiPage = getDialogSettings().getBoolean( SuWizardSettings.SHOW_JBI_PAGE );
		if( displayJbiPage ) {
			IWizardPage page;
			if( isProvides())
				page = new JbiProvidePage();
			else
				page = new JbiConsumePage();

			addPage( page );
		}

		// Project page
		registerCustomPages( CustomPagePosition.beforeProjectPage );
		addPage( new ProjectPage());
		registerCustomPages( CustomPagePosition.afterProjectPage );
	}


	/**
	 * Should be called by {@link #registerPagesAfterChoicePage()}.
	 * @param customPagePosition
	 */
	protected void registerCustomPages( CustomPagePosition customPagePosition ) {

		List<AbstractSuPage> pages = new ArrayList<AbstractSuPage> ();
		getWizardHandler().registerCustomWizardPages( customPagePosition, pages );
		for( AbstractSuPage page : pages )
			addPage( page );
	}


	/**
	 * Creates the Petals project and selects and open the required elements.
	 */
	@Override
	public boolean performFinish() {

		ProjectPage projectPage = (ProjectPage) this.pageManager.getPage( ProjectPage.PAGE_NAME );
		final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject( projectPage.getProjectName());
		final List<Object> elementsToSelect = new ArrayList<Object> ();

		// Define the wizard completion process
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			@Override
			protected void execute( IProgressMonitor monitor )
			throws CoreException, InterruptedException {
				try {
					doFinish( elementsToSelect, monitor );

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

			// Open the jbi.xml?
			// Do not open it in the WorkspaceModifyOperation
			// The project viewer must be updated before selecting anything in it
			final IFile jbiXmlFile = project.getFile( PetalsConstants.LOC_JBI_FILE );
			if( getDialogSettings().getBoolean( SuWizardSettings.OPEN_JBI_XML )) {
				try {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					IDE.openEditor( page, jbiXmlFile );
					elementsToSelect.add( jbiXmlFile );

				} catch( PartInitException e ) {
					PetalsServicesPlugin.log( e, IStatus.ERROR );
				}
			}

			// Update the selection
			ResourceUtils.selectResourceInPetalsExplorer( true, elementsToSelect );

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
	private void doFinish( final List<Object> elementsToSelect, final IProgressMonitor monitor )
	throws CoreException, IOException {

		// Create the SU project
		monitor.beginTask( "", IProgressMonitor.UNKNOWN );
		monitor.subTask( "Creating the project structure..." );

		ProjectPage projectPage = (ProjectPage) this.pageManager.getPage( ProjectPage.PAGE_NAME );
		URI locationURI = projectPage.isAtDefaultlocation() ? null : projectPage.computeProjectLocation().toURI();
		IProject project = PetalsServicesProjectUtils.createSuProject(
				projectPage.getProjectName(),
				locationURI,
				this.wizardHandler.getComponentVersionDescription().getComponentName(),
				this.wizardHandler.getComponentVersionDescription().getComponentVersion(),
				this.wizardHandler.getComponentVersionDescription().getComponentAlias(),
				this.wizardHandler.isJavaProject(),
				monitor );


		// Import the WSDL and update the jbi.xml file in consequence
		final IFolder resourceDirectory = project.getFolder( PetalsConstants.LOC_RES_FOLDER );
		final File jbiXmlFile = project.getFile( PetalsConstants.LOC_JBI_FILE ).getLocation().toFile();
		File wsdlFile = null;
		if( isProvides()) {
			monitor.subTask( "Importing the WSDL..." );
			JbiProvidePage jbiPage = (JbiProvidePage) this.pageManager.getPage( JbiAbstractPage.PAGE_NAME );

			String wsdlFileLocation = jbiPage.getWsdlUrl();
			if( jbiPage.isImportWsdl() && wsdlFileLocation != null ) {
				WsdlImportUtils wsdlImportUtils = new WsdlImportUtils();
				Map<String,File> fileToUrl = wsdlImportUtils.importWsdlOrXsdAndDependencies( resourceDirectory.getLocation().toFile(), jbiPage.getWsdlUrl());
				wsdlFile = fileToUrl.get( jbiPage.getWsdlUrl());
				wsdlFileLocation = IoUtils.getRelativeLocationToFile( jbiXmlFile, wsdlFile );
			}

			// TODO: set the WSDL URL
			monitor.worked( 1 );
		}


		// Import the files that need to be imported - done by the contributor
		monitor.subTask( "Importing files..." );
		this.wizardHandler.performActionsBeforeWrittingJbiXml( resourceDirectory, this.jbiInstance, monitor );
		monitor.worked( 1 );


		// Update the end-point in the WSDL file?
		if( isProvides()) {
			if( wsdlFile == null ) {
				PetalsServicesPlugin.log( "A WSDL file was expected but none was found.", IStatus.WARNING );

			} else {
				monitor.subTask( "Updating the WSDL..." );
				AbstractEndpoint ae = getFirstProvideOrConsume();
				WsdlUtils.INSTANCE.updateEndpointNameInWsdl( wsdlFile, ae.getServiceName(), ae.getEndpointName());
				monitor.worked( 1 );
			}
		}


		// Create the jbi.xml file
		monitor.subTask( "Creating the jbi.xml..." );
		ResourceSet resourceSet = new ResourceSetImpl();
		org.eclipse.emf.common.util.URI emfUri = org.eclipse.emf.common.util.URI.createFileURI( jbiXmlFile.getAbsolutePath());

		Resource resource = resourceSet.createResource( emfUri );
		resource.getContents().add( this.jbiInstance );
		resource.save( null );
		monitor.worked( 1 );


		// Perform the last actions
		monitor.subTask( "Performing extra-actions..." );
		try {
			getShell().getDisplay().syncExec( new Runnable() {
				@Override
				public void run() {

					try {
						resourceDirectory.refreshLocal( IResource.DEPTH_INFINITE, monitor );
						PetalsSuNewWizard.this.wizardHandler.performLastActions(
								 resourceDirectory, getFirstProvideOrConsume(),
								 monitor, elementsToSelect );

					} catch( CoreException e ) {
						PetalsServicesPlugin.log( e, IStatus.ERROR );
					}
				}
			});

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );

		} finally {
			project.refreshLocal( IResource.DEPTH_INFINITE, monitor );
			monitor.worked( 1 );
		}
	}


	/**
	 * @return true if this wizard defines a service provider, false for a consumer
	 */
	public boolean isProvides() {
		return this.petalsMode == PetalsMode.provides;
	}


	/**
	 * @return the componentVersionDescription
	 */
	public ComponentWizardHandler getWizardHandler() {
		return this.wizardHandler;
	}


	/**
	 * @return the first provides or consumes block (not null)
	 */
	public AbstractEndpoint getFirstProvideOrConsume() {
		Services services = this.jbiInstance.getServices();
		AbstractEndpoint result = isProvides() ? services.getProvides().get( 0 ) : services.getConsumes().get( 0 );

		// Should not be null, the wizard should set the required intermediate structures
		Assert.isNotNull( result );
		return result;
	}


	/**
	 * Sets the wizard handler.
	 * <p>
	 * Only instances of {@link ChoicePage} should invoke this method.
	 * </p>
	 * @param wizardHandler the wizardHandler to set
	 */
	public void setWizardHandler( ComponentWizardHandler wizardHandler ) {

		// Set the wizard handler
		this.wizardHandler = wizardHandler;
		this.wizardHandler.setServiceProvider( isProvides());

		// Update the services part
		this.jbiInstance.getServices().setBindingComponent( wizardHandler.getComponentVersionDescription().isBc());

		// Refresh the model and predefine values
		this.jbiInstance.getServices().getProvides().clear();
		this.jbiInstance.getServices().getConsumes().clear();
		if( isProvides()) {
			Provides provides = JbiFactory.eINSTANCE.createProvides();
			this.jbiInstance.getServices().getProvides().add( provides );
			wizardHandler.predefineJbiValues( provides );

		} else {
			Consumes consumes = JbiFactory.eINSTANCE.createConsumes();
			this.jbiInstance.getServices().getConsumes().add( consumes );
			wizardHandler.predefineJbiValues( consumes );
		}

		// Register new pages
		registerPagesAfterChoicePage();
	}
}
