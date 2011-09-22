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

package com.ebmwebsourcing.petals.services.monitoring.wizard;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean;
import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.generation.JbiXmlGenerationHelper;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.KpiConsumes10;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.KpiConsumes10.FlowType;
import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenBean;
import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.generated.KpiStylesheet;
import com.ebmwebsourcing.petals.services.monitoring.wizard.KpiProjectBean.KpiFlowBean;
import com.ebmwebsourcing.petals.services.su.wizards.SettingConstants;
import com.ebmwebsourcing.petals.services.utils.PetalsServicesProjectUtils;

/**
 * The wizard for the KPI component.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class KpiNewWizard extends Wizard implements INewWizard {

	private KpiProjectWizardPage projectPage;
	private final List<IResource> resourcesToSelect = new ArrayList<IResource> ();


	/**
	 * Constructor.
	 */
	public KpiNewWizard() {

		super();
		setNeedsProgressMonitor( true );
		setWindowTitle( "New Watching Configuration" );
		setForcePreviousAndNextButtons( true );

		ImageDescriptor desc = PetalsServicesPlugin.getImageDescriptor( "icons/wizban/wiz_kpi.png" );
		setDefaultPageImageDescriptor( desc );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPages()
	 */
	@Override
	public void addPages() {

		initializeSettings();
		this.projectPage = new KpiProjectWizardPage();
		this.projectPage.setTitle( "Deployment Configuration" );
		this.projectPage.setDescription( "Define the properties of the Service Assembly to create." );
		addPage( this.projectPage );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #getNextPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	@Override
	public IWizardPage getNextPage( IWizardPage page ) {

		IWizardPage nextPage = null;
		if( page != null )
			nextPage = page.getNextPage();

		return nextPage;
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

			// Reveal the projects in the explorer
			IResource[] res = new IResource[ this.resourcesToSelect.size()];
			ResourceUtils.selectResourceInPetalsExplorer( true, this.resourcesToSelect.toArray( res ));

			// If there is only one project, open its jbi.xml file
			if( this.resourcesToSelect.size() == 1 ) {
				try {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

					IProject p = this.resourcesToSelect.get( 0 ).getProject();
					IFile jbiXmlFile = JbiXmlUtils.getJbiXmlFile( p );

					IDE.openEditor( page, jbiXmlFile );
					ResourceUtils.selectResourceInPetalsExplorer( true, jbiXmlFile );

				} catch( Exception e ) {
					PetalsServicesPlugin.log( e, IStatus.WARNING );
				}
			}

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
			return false;
		}

		return true;
	}


	/**
	 * Creates all the required resources.
	 * @param bean
	 * @param monitor
	 * @throws CoreException
	 */
	protected void doFinish( IProgressMonitor monitor ) throws Exception {

		Collection<KpiProjectBean> kpiProjects = this.projectPage.getProjectsToCreate();
		monitor.beginTask( "Creating the projects...", kpiProjects.size());
		String xslContent = new KpiStylesheet().generate( this.projectPage.getProcessType());


		// Store the process name
		Set<String> processNameSet = new LinkedHashSet<String>();
		String mergedNames = PetalsServicesPlugin.getDefault().getPreferenceStore().getString( KpiConstants.PREF_PROCESS_NAMES );
		if( ! StringUtils.isEmpty( mergedNames )) {
			List<String> processNames = Arrays.asList( mergedNames.split( KpiConstants.PREF_PROCESS_SEPARATOR ));
			processNameSet.addAll( processNames );
		}

		// The insertion order matters
		processNameSet.add( this.projectPage.getProcessName());

		StringBuilder sb = new StringBuilder();
		for( Iterator<String> it = processNameSet.iterator(); it.hasNext(); ) {
			sb.append( it.next());
			if( it.hasNext())
				sb.append( KpiConstants.PREF_PROCESS_SEPARATOR );
		}

		PetalsServicesPlugin.getDefault().getPreferenceStore().setValue( KpiConstants.PREF_PROCESS_NAMES, sb.toString());


		// Create the SU projects
		List<IProject> suDependencies = new ArrayList<IProject> ();
		List<MavenBean> suMavenBeans = new ArrayList<MavenBean>();
		for( KpiProjectBean kpb : kpiProjects ) {

			if( ! kpb.isToCreate())
				continue;

			monitor.subTask( kpb.getProjectName());
			String pLoc = this.projectPage.getProjectLocation();
			URI projectLocationURI = pLoc == null ? null : new File( pLoc, kpb.getProjectName()).toURI();
			IProject project = PetalsServicesProjectUtils.createSuProject(
						kpb.getProjectName(),
						projectLocationURI,
						"petals-se-kpi",
						"1.0",
						"KPI",
						false,
						monitor );

			suDependencies.add( project );

			// Create the POM artifacts for the SA
			// Elements match what is set in PetalsServicesProjectUtils#createPetalsSuProjectStructure().
			MavenBean suMavenBean = new MavenBean();
			suMavenBean.setArtifactId( project.getName());
			suMavenBeans.add( suMavenBean );

			// Go through the collection of flows
			List<KpiConsumes10> kpiConsumeBlocks = new ArrayList<KpiConsumes10>( KpiConstants.FLOW_NAMES.length );
			for( KpiFlowBean kpiFlowBean : kpb.getFlowBeans()) {

				if( ! kpiFlowBean.isToCreate())
					continue;

				// Create the XSL style sheet
				IFile xslFile = project.getFile( PetalsConstants.LOC_RES_FOLDER + "/" + kpiFlowBean.getFlowName() + ".xsl" );
				if( ! xslFile.exists())
					xslFile.create( new ByteArrayInputStream( xslContent.getBytes( "UTF-8" )), true, monitor );
				else
					xslFile.setContents( new ByteArrayInputStream( xslContent.getBytes( "UTF-8" )), true, true, monitor );

				// Create the jbi.xml file
				KpiConsumes10 kpiContent = new KpiConsumes10();
				kpiConsumeBlocks.add( kpiContent );
				kpiContent.setXslFile( xslFile.getLocation().toFile());
				kpiContent.setFlow( FlowType.valueOf( kpiFlowBean.getFlowName().toLowerCase()));

				if( this.projectPage.isConsumeConfiguration()) {
					kpiContent.setConsumedInterface( kpiFlowBean.getBean().getInterfaceName());
					kpiContent.setConsumedService( kpiFlowBean.getBean().getServiceName());
					kpiContent.setConsumedEndpoint( kpiFlowBean.getBean().getEndpointName());
				}
				else
					kpiContent.setXpathExpression( kpiFlowBean.getXpathExpression());
			}

			JbiXmlGenerationHelper ajx = new JbiXmlGenerationHelper();
			ajx.setComponentName( "petals-se-kpi" );
			IFile jbiXmlFile = project.getFile( PetalsConstants.LOC_JBI_FILE );
			AbstractJbiXmlBean[] delegates = new AbstractJbiXmlBean[ kpiConsumeBlocks.size()];
			String jbiXmlContent = ajx.createJbiDescriptor( kpiConsumeBlocks.toArray( delegates )).toString();
			if( ! jbiXmlFile.exists())
				jbiXmlFile.create( new ByteArrayInputStream( jbiXmlContent.getBytes( "UTF-8" )), true, monitor );
			else
				jbiXmlFile.setContents( new ByteArrayInputStream( jbiXmlContent.getBytes( "UTF-8" )), true, true, monitor );

			// Refresh the project and prepare the selection
			monitor.worked( 1 );
			project.refreshLocal( IResource.DEPTH_INFINITE, monitor );
		}


		// Use an existing SA? => update the POM and the jbi.xml
		if( this.projectPage.isAppendToProcess()) {
			IProject saProject = ResourcesPlugin.getWorkspace().getRoot().getProject( this.projectPage.getProjectName());
			for( IProject p : saProject.getReferencedProjects()) {

				// jbi.xml
				suDependencies.add( p );

				// pom.xml
				MavenBean mavenBean = MavenUtils.getPomElements( p.getFile( PetalsConstants.LOC_POM_FILE ));
				suMavenBeans.add( mavenBean );
			}
		}


		// Generate a basic POM for the SA
		MavenBean mavenBean = new MavenBean();
		mavenBean.setName( this.projectPage.getProjectName());
		mavenBean.setArtifactId( mavenBean.getName());
		mavenBean.dependencies.addAll( suMavenBeans );


		// Get the parent pom.xml
		MavenBean parentBean = MavenUtils.getPomParentElements();
		if( parentBean != null ) {
			mavenBean.setParentArtifactId( parentBean.getArtifactId());
			mavenBean.setParentGroupId( parentBean.getGroupId());
			mavenBean.setParentVersion( parentBean.getVersion());
		}


		// Create the SA project
		IProject saProject = null;
		monitor.subTask( this.projectPage.getProjectName());
		if( this.projectPage.isAppendToProcess()) {
			saProject = ResourcesPlugin.getWorkspace().getRoot().getProject( this.projectPage.getProjectName());
		}
		else {
			String pLoc = this.projectPage.getProjectLocation();
			URI projectLocationURI = pLoc == null ? null : new File( pLoc, this.projectPage.getProjectName()).toURI();
			saProject = PetalsServicesProjectUtils.createSaProject(
						this.projectPage.getProjectName(),
						projectLocationURI,
						mavenBean,
						monitor );
		}

		// Create the jbi.xml
		String[] suNames = new String[ suDependencies.size()];
		for( int i=0; i<suDependencies.size(); i++ )
			suNames[ i ] = suDependencies.get( i ).getName();

		String content = JbiUtils.generateJbiXmlForSA( "petals-se-kpi", this.projectPage.getProjectName(), suNames );
		IFile jbiXmlFile = saProject.getFile( PetalsConstants.LOC_JBI_FILE );
		if( jbiXmlFile.exists())
			jbiXmlFile.setContents( new ByteArrayInputStream( content.getBytes( "UTF-8" )), true, true, monitor );
		else
			jbiXmlFile.create( new ByteArrayInputStream( content.getBytes( "UTF-8" )), true, monitor );


		// SU dependencies
		try {
			IProjectDescription desc = saProject.getDescription();
			IProject[] projects = new IProject[ suDependencies.size()];
			desc.setReferencedProjects( suDependencies.toArray( projects ));
			saProject.setDescription( desc, null );

		} catch( CoreException e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}

		// Refresh the project and prepare the selection
		monitor.worked( 1 );
		saProject.refreshLocal( IResource.DEPTH_INFINITE, monitor );
		this.resourcesToSelect.add( jbiXmlFile );
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
	 * @see org.eclipse.jface.wizard.Wizard
	 * #canFinish()
	 */
	@Override
	public boolean canFinish() {
		IWizardPage page = getContainer().getCurrentPage();
		return page instanceof KpiConfigurationWizardPage && page.isPageComplete();
	}


	/**
	 * Initializes the dialog settings.
	 */
	private void initializeSettings() {

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
}
