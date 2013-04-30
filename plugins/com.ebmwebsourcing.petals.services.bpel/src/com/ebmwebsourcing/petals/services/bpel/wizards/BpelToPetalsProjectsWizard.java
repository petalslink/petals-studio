/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.bpel.wizards;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.bpel.common.wsdl.importhelpers.WsdlImportHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.generation.JbiXmlGenerationHelper;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.SoapConsumes40;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.SoapProvides40;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.SoapProvides40.SoapVersion;
import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenBean;
import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.CollectionUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StatusUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.JbiBasicBean;
import com.ebmwebsourcing.petals.services.bpel.PetalsBpelHelper;
import com.ebmwebsourcing.petals.services.bpel.PetalsBpelPlugin;
import com.ebmwebsourcing.petals.services.utils.PetalsServicesProjectUtils;
import com.ebmwebsourcing.petals.services.wizards.beans.SaImportBean;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBean;
import com.sun.java.xml.ns.jbi.Jbi;

/**
 * A wizard to create a set of Petals Maven projects from a BPEL process.
 * @author Vincent Zurczak - EBM WebSourcing
 * FIXME: this class is a mess, the code could be organized in a better way.
 */
public class BpelToPetalsProjectsWizard extends Wizard implements IExportWizard {

	private final List<IStatus> errors = new ArrayList<IStatus> ();
	private final List<IResource> resourcesToSelect = new ArrayList<IResource> ();
	private BpelToPetalsProjectsWizardPage page;
	private WsdlImportHelper wsdlImportHelper;


	/**
	 * Constructor.
	 */
	public BpelToPetalsProjectsWizard() {
		super();
		setNeedsProgressMonitor( true );
		setWindowTitle( "From BPEL to Petals Projects" );

		try {
			this.wsdlImportHelper = new WsdlImportHelper();

		} catch( ParserConfigurationException e ) {
			PetalsBpelPlugin.log( e, IStatus.ERROR );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPages()
	 */
	@Override
	public void addPages() {
		this.page = new BpelToPetalsProjectsWizardPage();
		addPage( this.page );
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
			IResource[] res = new IResource[ this.resourcesToSelect.size()];
			ResourceUtils.selectResourceInPetalsExplorer( true, this.resourcesToSelect.toArray( res ));

		} catch( Exception e ) {
			PetalsBpelPlugin.log( e, IStatus.ERROR );
			return false;

		} finally {
			this.resourcesToSelect.clear();
		}

		// Display errors
		if( ! this.errors.isEmpty()) {
			IStatus[] children = new IStatus[ this.errors.size()];
			MultiStatus status = new MultiStatus(
						PetalsBpelPlugin.PLUGIN_ID, 0,
						this.errors.toArray( children ),
						"The conversion encountered errors.", null );

			ErrorDialog.openError( new Shell(), "Conversion Error", "", status );
		}

		return true;
	}


	/**
	 * Creates the selected projects.
	 * @param monitor
	 * @throws CoreException
	 */
	protected void doFinish( IProgressMonitor monitor ) throws CoreException {

		monitor.beginTask( "Creating the projects...", IProgressMonitor.UNKNOWN );
		List<SaImportBean> importBeans = this.page.getImportsBeans();


		// Create the SU projects
		List<MavenBean> suMavenBeans = new ArrayList<MavenBean>();
		Map<String,String> suNameToComponent = new HashMap<String,String> ();
		for( SaImportBean saBean : importBeans ) {
			for( SuImportBean suBean : saBean.getSuBeans()) {

				if( ! suBean.isToCreate())
					continue;

				URI pLocUri = null;
				if( ! this.page.isAtDefaultLocation())
					pLocUri = new File( this.page.getProjectLocation(), suBean.getProjectName()).toURI();

				try {
					// Delete the project?
					if( suBean.isToOverwrite()) {
						IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject( suBean.getProjectName());
						if( p.exists())
							p.delete( true, monitor );
					}

					// Create the project
					if( "petals-bc-soap".equalsIgnoreCase( suBean.getComponentName())) {
						createSuSoapProject( suBean, pLocUri, suBean.isConsume(), monitor );
						suNameToComponent.put( suBean.getProjectName(), "petals-bc-soap" );
					}
					else {
						createSuBpelProject( suBean, pLocUri, monitor );
						suNameToComponent.put( suBean.getProjectName(), "petals-se-bpel" );
					}

					// Create the POM artifacts for the SA
					// Elements match what is set in PetalsServicesProjectUtils#createPetalsSuProjectStructure().
					MavenBean suMavenBean = new MavenBean();
					suMavenBean.setArtifactId( suBean.getProjectName());
					suMavenBeans.add( suMavenBean );

				}  catch( Exception e ) {
					PetalsBpelPlugin.log( e, IStatus.ERROR );
					IStatus status = StatusUtils.createStatus( e, "" );
					this.errors.add( status );
				}
			}


			// Create the SA?
			if( saBean.isToCreate()) {
				URI pLocUri = null;
				if( ! this.page.isAtDefaultLocation())
					pLocUri = new File( this.page.getProjectLocation(), saBean.getProjectName()).toURI();

				try {
					// Delete the project?
					if( saBean.isToOverwrite()) {
						IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject( saBean.getProjectName());
						if( p.exists())
							p.delete( true, monitor );
					}

					// Create the project
					createSaProject( saBean, pLocUri, monitor, suMavenBeans, suNameToComponent );

				} catch( Exception e ) {
					PetalsBpelPlugin.log( e, IStatus.ERROR );
					IStatus status = StatusUtils.createStatus( e, "" );
					this.errors.add( status );
				}
			}
		}
	}


	/**
	 * Creates a SU BPEL project for the BPEL sketch conversion process.
	 * @param suBean
	 * @param pLocUri
	 * @param monitor
	 * @throws CoreException
	 * @throws Exception
	 */
	private void createSuBpelProject( SuImportBean suBean, URI pLocUri, IProgressMonitor monitor )
	throws CoreException, Exception {

		IProject project = PetalsServicesProjectUtils.createSuProject(
					suBean.getProjectName(),
					pLocUri,
					suBean.getComponentName(),
					suBean.getComponentVersion(),
					suBean.getSuType(),
					false,
					monitor );


		// Copy the BPEL file
		monitor.subTask( "Copying the BPEL process..." );
		URI bpelUri = this.page.getHelper().getBpelUri();
		InputStream is = bpelUri.toURL().openStream();
		String bpelName = UriAndUrlHelper.extractOrGenerateFileName( bpelUri.toString());
		File target = project.getFolder( PetalsConstants.LOC_RES_FOLDER ).getFile( bpelName ).getLocation().toFile();
		IoUtils.copyStream( is, target );
		monitor.worked( 5 );


		// Import the WSDL files
		Set<String> urisToImport = new HashSet<String> ();
		for( String s : this.page.getHelper().findPartnerImports( true )) {
			URI uri = UriAndUrlHelper.buildNewURI( bpelUri, s );
			urisToImport.add( uri.toString());
		}

		for( String s : this.page.getHelper().findProcessInterfaceImports( false )) {
			URI uri = UriAndUrlHelper.buildNewURI( bpelUri, s );
			urisToImport.add( uri.toString());
		}

		for( String s : this.page.getHelper().findOtherImports( false )) {
			URI uri = UriAndUrlHelper.buildNewURI( bpelUri, s );
			urisToImport.add( uri.toString());
		}

		this.wsdlImportHelper.importWsdlOrXsdAndDependencies(
				target.getParentFile(),
				CollectionUtils.convertToArray( urisToImport, String.class ));


		// Create the jbi.xml file
		monitor.subTask( "Generating the jbi.xml file..." );
		final IFile jbiFile = project.getFile( PetalsConstants.LOC_JBI_FILE );
		Jbi jbiInstance = new PetalsBpelHelper( target ).createJbiXml( jbiFile, bpelName );
		JbiXmlUtils.writeJbiXmlModel( jbiInstance, jbiFile.getLocation().toFile());


		// Select the project
		this.resourcesToSelect.add( project );
		try {
			project.refreshLocal( IResource.DEPTH_INFINITE, monitor );

		} catch( Exception e ) {
			PetalsBpelPlugin.log( e, IStatus.WARNING );
		}
	}


	/**
	 * Creates a SU SOAP project for the BPEL sketch conversion process.
	 * @param suBean
	 * @param pLocUri
	 * @param consume
	 * @param monitor
	 * @throws CoreException
	 * @throws Exception
	 */
	private void createSuSoapProject( SuImportBean suBean, URI pLocUri, boolean consume, IProgressMonitor monitor )
	throws CoreException, Exception {

		IProject project = PetalsServicesProjectUtils.createSuProject(
					suBean.getProjectName(),
					pLocUri,
					suBean.getComponentName(),
					suBean.getComponentVersion(),
					suBean.getSuType(),
					false,
					monitor );

		// Create the jbi.xml file
		JbiBasicBean jbiBean = (JbiBasicBean) suBean.getKeyToObject().get( BpelToPetalsProjectsWizardPage.JBI_BEAN );
		String jbiXmlContent;
		if( consume ) {
			SoapConsumes40 sp = new SoapConsumes40();
			sp.setEndpointName( jbiBean.getEndpointName());
			sp.setInterfaceName( jbiBean.getInterfaceName().getLocalPart());
			sp.setInterfaceNamespace( jbiBean.getInterfaceName().getNamespaceURI());
			sp.setServiceName( jbiBean.getServiceName().getLocalPart());
			sp.setServiceNamespace( jbiBean.getServiceName().getNamespaceURI());
			sp.setAddress( jbiBean.getSoapAddress());

			// No MEP and operation
			// The builder will signal a problem

			JbiXmlGenerationHelper genDelegate = new JbiXmlGenerationHelper();
			genDelegate.setComponentName( suBean.getComponentName());
			jbiXmlContent = genDelegate.createJbiDescriptor( sp ).toString();
		}
		else {
			// Import the WSDL
			IFolder resFolder = project.getFolder( PetalsConstants.LOC_RES_FOLDER );
			String wsdlLocation = (String) suBean.getKeyToObject().get( BpelToPetalsProjectsWizardPage.WSDL_LOCATION );

			Map<String,File> urlToFile =
				this.wsdlImportHelper.importWsdlOrXsdAndDependencies( resFolder.getLocation().toFile(), wsdlLocation );

			File importedWsdlFile = urlToFile.get( wsdlLocation );

			// Create the jbi.xml
			SoapProvides40 sp = new SoapProvides40();
			sp.setEndpointName( jbiBean.getEndpointName());
			sp.setInterfaceName( jbiBean.getInterfaceName().getLocalPart());
			sp.setInterfaceNamespace( jbiBean.getInterfaceName().getNamespaceURI());
			sp.setServiceName( jbiBean.getServiceName().getLocalPart());
			sp.setServiceNamespace( jbiBean.getServiceName().getNamespaceURI());

			sp.setValidateWsdl( true );
			sp.setWsdl( importedWsdlFile.getName());
			sp.setServiceAddress( jbiBean.getSoapAddress());
			if( jbiBean.getSoapVersion() == WsdlUtils.SoapVersion.v11 )
				sp.setSoapVersion( SoapVersion.v11 );
			else
				sp.setSoapVersion( SoapVersion.v12 );

			JbiXmlGenerationHelper genDelegate = new JbiXmlGenerationHelper();
			genDelegate.setComponentName( suBean.getComponentName());
			jbiXmlContent = genDelegate.createJbiDescriptor( sp ).toString();
		}

		IFile jbiXmlFile = project.getFile( PetalsConstants.LOC_JBI_FILE );
		if( ! jbiXmlFile.exists())
			jbiXmlFile.create( new ByteArrayInputStream( jbiXmlContent.getBytes()), true, monitor );
		else
			jbiXmlFile.setContents( new ByteArrayInputStream( jbiXmlContent.getBytes()), true, true, monitor );

		try {
			project.refreshLocal( IResource.DEPTH_INFINITE, monitor );

		} catch( Exception e ) {
			PetalsBpelPlugin.log( e, IStatus.WARNING );
		}
	}


	/**
	 * Creates a SA project.
	 * @param saBean
	 * @param pLocUri
	 * @param monitor
	 * @param suNameToComponentName
	 * @throws Exception
	 * @throws CoreException
	 */
	private void createSaProject(
				SaImportBean saBean,
				URI pLocUri,
				IProgressMonitor monitor,
				List<MavenBean> suMavenBeans,
				Map<String,String> suNameToComponentName )
	throws CoreException, Exception {

		// Generate a basic POM
		MavenBean mavenBean = new MavenBean();
		mavenBean.setName( saBean.getProjectName());
		mavenBean.setArtifactId( mavenBean.getName());

		// Get the parent pom.xml
		MavenBean parentBean = MavenUtils.getPomParentElements();
		if( parentBean != null ) {
			mavenBean.setParentArtifactId( parentBean.getArtifactId());
			mavenBean.setParentGroupId( parentBean.getGroupId());
			mavenBean.setParentVersion( parentBean.getVersion());
		}

		// Create the project
		IProject project = PetalsServicesProjectUtils.createSaProject(
					saBean.getProjectName(),
					pLocUri,
					mavenBean,
					monitor );

		// Create the jbi.xml
		String jbiXmlContent = JbiUtils.generateJbiXmlForSA( saBean.getProjectName(), suNameToComponentName );
		IFile jbiXmlFile = project.getFile( PetalsConstants.LOC_JBI_FILE );
		if( ! jbiXmlFile.exists())
			jbiXmlFile.create( new ByteArrayInputStream( jbiXmlContent.getBytes()), true, monitor );
		else
			jbiXmlFile.setContents( new ByteArrayInputStream( jbiXmlContent.getBytes()), true, true, monitor );

		// SU dependencies
		try {
			IProjectDescription desc = project.getDescription();
			IProject[] projects = new IProject[ suNameToComponentName.size()];
			int i=0;
			IWorkspaceRoot iwr = ResourcesPlugin.getWorkspace().getRoot();
			for( String name : suNameToComponentName.keySet() ) {
				projects[ i ] = iwr.getProject( name );
				i ++;
			}

			desc.setReferencedProjects( projects );
			project.setDescription( desc, null );

		} catch( CoreException e ) {
			PetalsBpelPlugin.log( e, IStatus.ERROR );
		}

		try {
			project.refreshLocal( IResource.DEPTH_INFINITE, monitor );

		} catch( Exception e ) {
			PetalsBpelPlugin.log( e, IStatus.WARNING );
		}

		// Select it
		this.resourcesToSelect.clear();
		this.resourcesToSelect.add( project );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.IWorkbenchWizard
	 * #init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init( IWorkbench workbench, IStructuredSelection selection ) {
		//
	}
}
