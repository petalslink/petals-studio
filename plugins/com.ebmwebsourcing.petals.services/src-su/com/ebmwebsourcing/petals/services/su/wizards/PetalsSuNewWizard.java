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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.commons.jbi.internal.provisional.client.JbiArchiveGenerator;
import com.ebmwebsourcing.commons.jbi.internal.provisional.utils.FileImporter.FileImportsException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.Messages;
import com.ebmwebsourcing.petals.services.su.extensions.RegisteredContributors;
import com.ebmwebsourcing.petals.services.su.extensions.RegisteredContributors.CustomPagePosition;
import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;
import com.ebmwebsourcing.petals.services.su.utils.FileImportManager;
import com.ebmwebsourcing.petals.services.su.utils.WsdlUtilsLegacy;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.generation.LastActionsPerformer;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.ChoicePage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.ProjectPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.consume.bc.BcConsumeCdkPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.consume.bc.BcConsumeJbiPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.consume.bc.BcConsumeSpecificPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.consume.se.SeConsumeCdkPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.consume.se.SeConsumeJbiPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.consume.se.SeConsumeSpecificPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.provide.bc.BcProvideCdkPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.provide.bc.BcProvideJbiPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.provide.bc.BcProvideSpecificPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.provide.se.SeProvideCdkPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.provide.se.SeProvideJbiPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.provide.se.SeProvideSpecificPage;
import com.ebmwebsourcing.petals.services.utils.PetalsServicesProjectUtils;

/**
 * The specialized wizard for the SU's.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsSuNewWizard extends Wizard implements INewWizard {

	/**
	 * The object which manages the pages.
	 */
	private final PageManager pageManager = new PageManager();

	/**
	 * The created project.
	 */
	private IProject createdProject;

	/**
	 * The wizard configuration.
	 */
	protected WizardConfiguration wc;

	/**
	 * The choice page.
	 */
	private ChoicePage page;

	/**
	 * A list of elements to select in the Petals projects view at the end of the wizard.
	 */
	private final List<Object> elementsToSelect = new ArrayList<Object> ();


	/**
	 * Constructor.
	 */
	public PetalsSuNewWizard() {
		super();
		setNeedsProgressMonitor( true );
		setForcePreviousAndNextButtons( true );

		setWindowTitle( Messages.AbstractSuWizard_0 );
		setDefaultPageImageDescriptor( PetalsServicesPlugin.getImageDescriptor( "icons/wizban/wiz_service_unit.png" ));
	}


	/**
	 * Adds the first page of the wizard.
	 */
	@Override
	public void addPages() {
		this.page = new ChoicePage();
		addPage( this.page );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	@Override
	public void addPage( IWizardPage page ) {

		if( page != null ) {
			if( page != this.page)
				page.setTitle( this.page.getSuType() + " Service Unit" );

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


	/**
	 * See later on if it required (Petals view).
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
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

		if( this.getContainer().getCurrentPage() != null ) {
			boolean isLastPage = getNextPage( getContainer().getCurrentPage()) == null;
			boolean isComplete = this.getContainer().getCurrentPage().isPageComplete();
			return isComplete && isLastPage;
		}

		return false;
	}


	/**
	 * Defines and registers all the wizard pages after the version page.
	 * <p>
	 * This method should be called by the version page each time a version is chosen.
	 * </p>
	 *
	 * @param suType
	 * @param suTypeVersion
	 */
	public void registerPagesAfterVersionPage( String suType, String suTypeVersion ) {

		clearAllPagesExcept( "ChoicePage" );
		setDialogSettings( null );
		boolean isConsume = ! this.page.isProvides();
		boolean isBc = this.page.isBc();

		initializeSettings( suType, suTypeVersion, isConsume );

		//
		// JBI pages.
		registerCustomPages( suType, suTypeVersion, isConsume, CustomPagePosition.beforeJbiPage );
		boolean displayJbiPage = getDialogSettings().getBoolean( SettingConstants.SHOW_JBI_PAGE );
		if( displayJbiPage ) {
			IWizardPage page;
			if( isBc ) {
				if( isConsume )
					page = new BcConsumeJbiPage( suType, suTypeVersion );
				else
					page = new BcProvideJbiPage( suType, suTypeVersion );
			}
			else {
				if( isConsume )
					page = new SeConsumeJbiPage( suType, suTypeVersion );
				else
					page = new SeProvideJbiPage( suType, suTypeVersion );
			}

			addPage( page );
		}


		//
		// Project page.
		ProjectPage projectPage = new ProjectPage( suType, suTypeVersion, isConsume );
		addPage( projectPage );

		boolean displayOtherPages = getDialogSettings().getBoolean( SettingConstants.SHOW_PAGES_AFTER_PROJECT_PAGE );
		if( displayOtherPages ) {

			//
			// Specific pages.
			registerCustomPages( suType, suTypeVersion, isConsume, CustomPagePosition.beforeSpecificPage );
			String pluginId = RegisteredContributors.getInstance().getPluginId( suType );
			AbstractSuPage specificPage =
				RegisteredContributors.getInstance().getCustomPage( suType, suTypeVersion, false );

			if( specificPage == null ) {
				if( isBc ) {
					if( isConsume )
						specificPage = new BcConsumeSpecificPage( suType, suTypeVersion, pluginId );
					else
						specificPage = new BcProvideSpecificPage( suType, suTypeVersion, pluginId );
				}
				else {
					if( isConsume )
						specificPage = new SeConsumeSpecificPage( suType, suTypeVersion, pluginId );
					else
						specificPage = new SeProvideSpecificPage( suType, suTypeVersion, pluginId );
				}

				if( specificPage.hasSomethingToDisplay())
					addPage( specificPage );
			}
			else {
				specificPage.setBasicFields( suType, suTypeVersion, pluginId );
				String desc;
				if( isConsume )
					desc = RegisteredContributors.getInstance().getConsumeDescription( suType, suTypeVersion );
				else
					desc = RegisteredContributors.getInstance().getProvideDescription( suType, suTypeVersion );

				specificPage.setDescription( desc );
				addPage( specificPage );
			}


			//
			// CDK pages.
			registerCustomPages( suType, suTypeVersion, isConsume, CustomPagePosition.beforeCdkPage );
			AbstractSuPage cdkPage =
				RegisteredContributors.getInstance().getCustomPage( suType, suTypeVersion, true );

			if( cdkPage == null ) {
				if( isBc ) {
					if( isConsume )
						cdkPage = new BcConsumeCdkPage( suType, suTypeVersion );
					else
						cdkPage = new BcProvideCdkPage( suType, suTypeVersion );
				}
				else {
					if( isConsume )
						cdkPage = new SeConsumeCdkPage( suType, suTypeVersion );
					else
						cdkPage = new SeProvideCdkPage( suType, suTypeVersion );
				}

				if( cdkPage.hasSomethingToDisplay())
					addPage( cdkPage );
			}
			else {
				cdkPage.setBasicFields( suType, suTypeVersion, pluginId );
				String desc;
				if( isConsume )
					desc = RegisteredContributors.getInstance().getConsumeDescription( suType, suTypeVersion );
				else
					desc = RegisteredContributors.getInstance().getProvideDescription( suType, suTypeVersion );

				cdkPage.setDescription( desc );
				addPage( cdkPage );
			}

			registerCustomPages( suType, suTypeVersion, isConsume, CustomPagePosition.afterCdkPage );
		}
	}


	/**
	 * Should be called by {@link #registerPagesAfterVersionPage(String, String)}.
	 *
	 * @param suType
	 * @param suVersion
	 * @param inConsumeMode
	 * @param customPagePosition
	 */
	protected void registerCustomPages(
				String suType, String suVersion,
				boolean inConsumeMode, CustomPagePosition customPagePosition ) {

		List<AbstractSuPage> pages = null;
		switch( customPagePosition ) {
		case afterCdkPage:
			pages = RegisteredContributors.getInstance().getCustomWizardPages(
						suType, suVersion, inConsumeMode, CustomPagePosition.afterCdkPage );
			break;
		case beforeJbiPage:
			pages = RegisteredContributors.getInstance().getCustomWizardPages(
						suType, suVersion, inConsumeMode, CustomPagePosition.beforeJbiPage );
			break;
		case beforeSpecificPage:
			pages = RegisteredContributors.getInstance().getCustomWizardPages(
						suType, suVersion, inConsumeMode, CustomPagePosition.beforeSpecificPage );
			break;
		case beforeCdkPage:
			pages = RegisteredContributors.getInstance().getCustomWizardPages(
						suType, suVersion, inConsumeMode, CustomPagePosition.beforeCdkPage );
			break;
		}

		if( pages == null )
			return;

		for( AbstractSuPage page : pages )
			addPage( page );
	}


	/**
	 * @param versionPageName the version page name
	 * @see PageManager#clearAllPagesExcept(String)
	 */
	public void clearAllPagesExcept( String versionPageName ) {
		this.pageManager.clearAllPagesExcept( versionPageName );
	}


	/**
	 * This method is called when 'Finish' button is pressed in the wizard.
	 * Consists in creating a SU project, a jbi.xml file and add on them the required properties and elements.
	 */
	@Override
	public boolean performFinish() {

		// Get the bean for the processing.
		final EclipseSuBean eclipseSuBean = this.pageManager.retrieveWizardData();

		// Add hook for the WSDL.
		WsdlUtilsLegacy.addHookForWsdlUrl( eclipseSuBean, FileImportManager.getFileImportManager());

		// Define the wizard completion process.
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			@Override
			protected void execute( IProgressMonitor monitor )
			throws CoreException, InterruptedException {
				try {
					doFinish( eclipseSuBean, monitor );

				} catch( InterruptedException e ) {
					// nothing

				} catch( Exception e ) {
					String errorMsg = e.getMessage();
					errorMsg = errorMsg != null ? errorMsg : "A problem occurred during wizard completion.";
					ErrorReporter.INSTANCE.registerError( "WIZARD-problem", errorMsg, IStatus.ERROR, e );

				} finally {
					PetalsSuNewWizard.this.createdProject.refreshLocal( IResource.DEPTH_INFINITE, monitor );
					monitor.done();
				}
			}
		};

		try {
			// Run the operation.
			getContainer().run( true, false, op );

			// Open the jbi.xml?
			// Do not open it in the WorkspaceModifyOperation
			// The project viewer must be updated before selecting anything in it
			final IFile jbiXmlFile = this.createdProject.getFile( PetalsConstants.LOC_JBI_FILE );
			if( getDialogSettings().getBoolean( SettingConstants.FILL_AND_OPEN_JBI_XML )
						&& getDialogSettings().getBoolean( SettingConstants.OPEN_JBI_XML )) {
				try {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					IDE.openEditor( page, jbiXmlFile );
					this.elementsToSelect.add( jbiXmlFile );

				} catch( PartInitException e ) {
					PetalsServicesPlugin.log( e, IStatus.ERROR );
				}
			}

			// Refresh the project
			this.createdProject.refreshLocal( IResource.DEPTH_INFINITE, null );

			// Update the selection
			ResourceUtils.selectResourceInPetalsExplorer( true, this.elementsToSelect );

		} catch( InterruptedException e ) {
			// nothing

		} catch( Exception e ) {
			String errorMsg = e.getMessage();
			errorMsg = errorMsg != null ? errorMsg : "A problem occurred during wizard completion.";
			ErrorReporter.INSTANCE.registerError( "WIZARD-problem", errorMsg, IStatus.ERROR, e );

		} finally {
			boolean thereWereErrors = ErrorReporter.INSTANCE.errorsOccured();

			ErrorReporter.INSTANCE.generateLog();
			if( thereWereErrors ) {
				getShell().setVisible( false );
				MessageDialog.openError( getShell(), Messages.AbstractSuWizard_19,
				"Errors occurred in the wizard. Check the log for more details." );
			}
		}

		return true;
	}


	/**
	 * This worker method finds the container, creates the skeleton of the SU project and creates and opens the jbi.xml file.
	 *
	 * @param eclipseSuBean the information retrieved from the wizard.
	 * @param monitor the monitor used to provide some interaction during the wizard ending.
	 * @throws CoreException
	 * @throws FileImportsException
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws Exception if the created project has an invalid structure.
	 * If thrown here, it means an error in the program. Appearing somewhere else may mean
	 * the user modified the project structure.
	 */
	private void doFinish( final EclipseSuBean eclipseSuBean, final IProgressMonitor monitor )
	throws CoreException, IllegalArgumentException, IOException, FileImportsException, Exception {

		//
		// Create the SU project skeleton.
		String projectName = eclipseSuBean.getProjectName();
		monitor.beginTask( "[ " + projectName + " ]", 11 );
		monitor.subTask( "Creating the project structure..." );
		this.createdProject = PetalsServicesProjectUtils.createSuProject( eclipseSuBean, monitor );
		// From now, project cannot be null.

		//
		// Import files before generating jbi.xml.
		monitor.subTask( "Importing files..." );
		FileImportManager.getFileImportManager().importFiles( this.createdProject );
		monitor.worked( 1 );

		// Update the end-point in the WSDL file.
		monitor.subTask( "Updating WSDL end-point..." );
		WsdlUtilsLegacy.updateWsdlEndpoint( eclipseSuBean, this.createdProject );
		monitor.worked( 1 );

		//
		// Update the value of the WSDL mark-up (if required).
		WsdlUtilsLegacy.addHookForWsdlMarkUp( eclipseSuBean );

		//
		// Create the jbi.xml file?
		if( getDialogSettings().getBoolean( SettingConstants.FILL_AND_OPEN_JBI_XML )) {

			// Create the content of the SU project and refresh the workspace.
			IPath metaInfPath = this.createdProject.getLocation();
			metaInfPath = metaInfPath.append( PetalsConstants.LOC_RES_FOLDER );
			File projectFolder = metaInfPath.toFile();

			JbiArchiveGenerator.getInstance().
			createJbiSuContent( eclipseSuBean, null, projectFolder );
		}

		monitor.worked( 1 );


		//
		// Perform extra-actions contributed by component plug-ins.
		monitor.subTask( "Performing extra-actions..." );
		final LastActionsPerformer performer =
			RegisteredContributors.getInstance().getLastActionsPerformerClass(
						eclipseSuBean.getSuType(),
						eclipseSuBean.getComponentVersion());

		try {
			if( performer != null ) {
				getShell().getDisplay().syncExec( new Runnable() {
					public void run() {

						IFolder resourceFolder = PetalsSuNewWizard.this.createdProject.getFolder( PetalsConstants.LOC_RES_FOLDER );
						try {
							resourceFolder.refreshLocal( IResource.DEPTH_INFINITE, monitor );
							performer.performLastActions( resourceFolder, eclipseSuBean, PetalsSuNewWizard.this.elementsToSelect, monitor );

						} catch( CoreException e ) {
							PetalsServicesPlugin.log( e, IStatus.WARNING );
						}
					}
				});
			}

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );

		} finally {
			this.createdProject.refreshLocal( IResource.DEPTH_INFINITE, monitor );
			monitor.worked( 1 );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performCancel()
	 */
	@Override
	public boolean performCancel() {
		ErrorReporter.INSTANCE.generateLog();
		return super.performCancel();
	}


	/**
	 * @return the wizard configuration
	 */
	public WizardConfiguration getWizardConfiguration() {
		return this.wc;
	}


	/**
	 * Initializes the dialog settings from the wizard configuration provided by component plug-ins.
	 * @param suType
	 * @param suTypeVersion
	 * @param isConsume
	 */
	protected void initializeSettings( String suType, String suTypeVersion, boolean isConsume ) {

		this.wc = RegisteredContributors.getInstance().getWizardConfigurationClass( suType, suTypeVersion, isConsume );
		IDialogSettings settings = new DialogSettings( "PetalsSection" );

		// Set default values
		settings.put( SettingConstants.ITF_NAME_ACTIVATE, "true" );
		settings.put( SettingConstants.ITF_NAME_VALUE, "" );
		settings.put( SettingConstants.ITF_NS_ACTIVATE, "true" );
		settings.put( SettingConstants.ITF_NS_VALUE, "" );
		settings.put( SettingConstants.ITF_VALIDATE, "true" );

		settings.put( SettingConstants.SRV_NAME_ACTIVATE, "true" );
		settings.put( SettingConstants.SRV_NAME_VALUE, "" );
		settings.put( SettingConstants.SRV_NS_ACTIVATE, "true" );
		settings.put( SettingConstants.SRV_NS_VALUE, "" );
		settings.put( SettingConstants.SRV_VALIDATE, "true" );

		settings.put( SettingConstants.EDPT_NAME_ACTIVATE, "true" );
		settings.put( SettingConstants.EDPT_NAME_VALUE, "" );
		settings.put( SettingConstants.EDPT_VALIDATE, "true" );
		settings.put( SettingConstants.EDPT_NAME_GEN_ACTIVATE, "true" );
		settings.put( SettingConstants.EDPT_NAME_GEN_VALUE, "false" );

		settings.put( SettingConstants.CREATE_JAVA_PROJECT, "false" );
		settings.put( SettingConstants.SHOW_JBI_PAGE, "true" );
		settings.put( SettingConstants.SHOW_PAGES_AFTER_PROJECT_PAGE, "true" );
		settings.put( SettingConstants.FILL_AND_OPEN_JBI_XML, "true" );
		settings.put( SettingConstants.OPEN_JBI_XML, "true" );
		settings.put( SettingConstants.SOAP_ADDRESS_VALUE, "" );
		settings.put( SettingConstants.SOAP_VERSION_VALUE, "" );
		settings.put( SettingConstants.SOAP_SERVICE_NAME, "" );

		settings.put( SettingConstants.WSDL_ACTIVATE, "true" );
		settings.put( SettingConstants.WSDL_HIDDEN_VALUE, "" );
		settings.put( SettingConstants.WSDL_SHOW, "true" );
		settings.put( SettingConstants.WSDL_TOOLTIP_VALUE, (String) null);
		settings.put( SettingConstants.PROVIDED_WSDL_URI, "" );
		settings.put( SettingConstants.CONSUMED_WSDL_URI, "" );


		// Use the configuration provided by the component plug-in
		for( Map.Entry<String,String> entry : this.wc.getWizardSettings().entrySet()) {
			settings.put( entry.getKey(), entry.getValue());
		}

		// Override values provided to the wizard - if launched manually
		IDialogSettings s = getDialogSettings();
		if( s != null ) {
			String value = null;

			if(( value = s.get( SettingConstants.ITF_NAME_ACTIVATE )) != null )
				settings.put( SettingConstants.ITF_NAME_ACTIVATE, value );
			if(( value = s.get( SettingConstants.ITF_NAME_VALUE )) != null )
				settings.put( SettingConstants.ITF_NAME_VALUE, value );
			if(( value = s.get( SettingConstants.ITF_NS_ACTIVATE )) != null )
				settings.put( SettingConstants.ITF_NS_ACTIVATE, value );
			if(( value = s.get( SettingConstants.ITF_NS_VALUE )) != null )
				settings.put( SettingConstants.ITF_NS_VALUE, value );
			if(( value = s.get( SettingConstants.ITF_VALIDATE )) != null )
				settings.put( SettingConstants.ITF_VALIDATE, value );

			if(( value = s.get( SettingConstants.SRV_NAME_ACTIVATE )) != null )
				settings.put( SettingConstants.SRV_NAME_ACTIVATE, value );
			if(( value = s.get( SettingConstants.SRV_NAME_VALUE )) != null )
				settings.put( SettingConstants.SRV_NAME_VALUE, value );
			if(( value = s.get( SettingConstants.SRV_NS_ACTIVATE )) != null )
				settings.put( SettingConstants.SRV_NS_ACTIVATE, value );
			if(( value = s.get( SettingConstants.SRV_NS_VALUE )) != null )
				settings.put( SettingConstants.SRV_NS_VALUE, value );
			if(( value = s.get( SettingConstants.SRV_VALIDATE )) != null )
				settings.put( SettingConstants.SRV_VALIDATE, value );

			if(( value = s.get( SettingConstants.EDPT_NAME_ACTIVATE )) != null )
				settings.put( SettingConstants.EDPT_NAME_ACTIVATE, value );
			if(( value = s.get( SettingConstants.EDPT_NAME_VALUE )) != null )
				settings.put( SettingConstants.EDPT_NAME_VALUE, value );
			if(( value = s.get( SettingConstants.EDPT_VALIDATE )) != null )
				settings.put( SettingConstants.EDPT_VALIDATE, value );

			if(( value = s.get( SettingConstants.CREATE_JAVA_PROJECT )) != null )
				settings.put( SettingConstants.CREATE_JAVA_PROJECT, value );
			if(( value = s.get( SettingConstants.SHOW_JBI_PAGE )) != null )
				settings.put( SettingConstants.SHOW_JBI_PAGE, value );
			if(( value = s.get( SettingConstants.FILL_AND_OPEN_JBI_XML )) != null )
				settings.put( SettingConstants.FILL_AND_OPEN_JBI_XML, value );
			if(( value = s.get( SettingConstants.SOAP_ADDRESS_VALUE )) != null )
				settings.put( SettingConstants.SOAP_ADDRESS_VALUE, value );

			if(( value = s.get( SettingConstants.WSDL_ACTIVATE )) != null )
				settings.put( SettingConstants.WSDL_ACTIVATE, value );
			if(( value = s.get( SettingConstants.WSDL_HIDDEN_VALUE )) != null )
				settings.put( SettingConstants.WSDL_HIDDEN_VALUE, value );
			if(( value = s.get( SettingConstants.WSDL_SHOW )) != null )
				settings.put( SettingConstants.WSDL_SHOW, value );
			if(( value = s.get( SettingConstants.WSDL_TOOLTIP_VALUE )) != null )
				settings.put( SettingConstants.WSDL_TOOLTIP_VALUE, value );

			if(( value = s.get( SettingConstants.PROVIDED_WSDL_URI )) != null )
				settings.put( SettingConstants.PROVIDED_WSDL_URI, value );
			if(( value = s.get( SettingConstants.CONSUMED_WSDL_URI )) != null )
				settings.put( SettingConstants.CONSUMED_WSDL_URI, value );
		}

		setDialogSettings( settings );
	}


	/**
	 * @return
	 */
	public boolean isProvides() {
		return this.page != null ? this.page.isProvides() : false;
	}
}
