/****************************************************************************
 *
 * Copyright (c) 2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.wizards;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
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
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenBean;
import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StatusUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlImportUtils;
import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.helpers.EipExportUtils;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipChain;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.utils.PetalsServicesProjectUtils;
import com.ebmwebsourcing.petals.services.wizards.beans.SaImportBean;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBean;

/**
 * A wizard to export an EIP chain as a set of Petals Maven projects
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MavenProjectExportWizard extends Wizard {

	private static final String[] EIP_VERSIONS = new String[] { "2.5" };
	private static final String EIP_OBJ = "eip.obj";

	private MavenProjectExportWizardPage page;
	private final EipChain eipChain;
	private final List<IStatus> errors = new ArrayList<IStatus> ();
	private final List<IResource> resourcesToSelect = new ArrayList<IResource> ();


	/**
	 * Constructor.
	 * @param eipChain
	 */
	public MavenProjectExportWizard( EipChain eipChain ) {
		super();
		this.eipChain = eipChain;

		setNeedsProgressMonitor( true );
		setWindowTitle( "EIP Chain Export" );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPages()
	 */
	@Override
	public void addPages() {
		this.page = new MavenProjectExportWizardPage();
		this.page.updateImportBeans( getProjectsToCreate(), true );
		addPage( this.page );
	}


	/* (non-Jsdoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
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
			ResourceUtils.selectResourceInPetalsExplorer(
						true, this.resourcesToSelect.toArray( res ));

		} catch( Exception e ) {
			PetalsEipPlugin.log( e, IStatus.ERROR );
			return false;

		} finally {

			// Clean resources
			this.resourcesToSelect.clear();
		}

		// Display errors
		if( ! this.errors.isEmpty()) {
			IStatus[] children = new IStatus[ this.errors.size()];
			MultiStatus status = new MultiStatus(
						PetalsEipPlugin.PLUGIN_ID, 0,
						this.errors.toArray( children ),
						"The croquis conversion encountered errors.", null );

			ErrorDialog.openError( new Shell(), "Conversion Error", "", status );
		}

		return true;
	}


	/**
	 * Create the selected projects.
	 * @param monitor
	 * @throws CoreException
	 */
	private void doFinish( IProgressMonitor monitor ) throws CoreException {

		monitor.beginTask( "Creating the concrete projects...", IProgressMonitor.UNKNOWN );
		List<SaImportBean> importBeans = this.page.getImportsBeans();


		// Create the SU
		List<MavenBean> suMavenBeans = new ArrayList<MavenBean> ();
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
					createSuEipProject( suBean, pLocUri, monitor );

					// Create the POM artifacts for the SA
					// Elements match what is set in PetalsServicesProjectUtils#createPetalsSuProjectStructure().
					MavenBean suMavenBean = new MavenBean();
					suMavenBean.setArtifactId( suBean.getProjectName());
					suMavenBeans.add( suMavenBean );

				}  catch( Exception e ) {
					PetalsEipPlugin.log( e, IStatus.ERROR );
					IStatus status = StatusUtils.createStatus( e, "" );
					this.errors.add( status );
				}
			}


			// Create the SA
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
					createSaProject( saBean, pLocUri, monitor, suMavenBeans );

				} catch( Exception e ) {
					PetalsEipPlugin.log( e, IStatus.ERROR );
					IStatus status = StatusUtils.createStatus( e, "" );
					this.errors.add( status );
				}
			}
		}
	}


	/**
	 * Creates an EIP project.
	 * @param suBean
	 * @param pLocUri
	 * @param monitor
	 * @throws Exception
	 * @throws CoreException
	 */
	private void createSuEipProject( SuImportBean suBean, URI pLocUri, IProgressMonitor monitor )
	throws CoreException, Exception {

		// Create the project
		IProject project = PetalsServicesProjectUtils.createSuProject(
					suBean.getProjectName(),
					pLocUri,
					suBean.getComponentName(),
					suBean.getComponentVersion(),
					suBean.getSuType(),
					false,
					monitor );


		// Import the WSDL, if any
		EipNode eip = (EipNode) suBean.getKeyToObject().get( EIP_OBJ );
		String newWsdlLocation = null;
		if( ! StringUtils.isEmpty( eip.getWsdlUri())) {

			WsdlImportUtils importUtils = new WsdlImportUtils();
			File targetDirectory = project.getFolder( PetalsConstants.LOC_RES_FOLDER ).getLocation().toFile();
			Map<String,File> map = importUtils.importWsdlOrXsdAndDependencies( targetDirectory, eip.getWsdlUri());

			File jbiXmlFile = project.getFile( PetalsConstants.LOC_JBI_FILE ).getLocation().toFile();
			newWsdlLocation = IoUtils.getRelativeLocationToFile( jbiXmlFile, map.get( eip.getWsdlUri()));
		}


		// Create the jbi.xml file
		String jbiXmlContent = EipExportUtils.createJbiXmlContent( eip, newWsdlLocation );
		IFile jbiXmlFile = project.getFile( PetalsConstants.LOC_JBI_FILE );
		if( ! jbiXmlFile.exists())
			jbiXmlFile.create( new ByteArrayInputStream( jbiXmlContent.getBytes()), true, monitor );
		else
			jbiXmlFile.setContents( new ByteArrayInputStream( jbiXmlContent.getBytes()), true, true, monitor );


		// Select the project
		this.resourcesToSelect.add( project );
		try {
			project.refreshLocal( IResource.DEPTH_INFINITE, monitor );

		} catch( Exception e ) {
			PetalsEipPlugin.log( e, IStatus.WARNING );
		}
	}


	/**
	 * Creates a SA project.
	 * @param saBean
	 * @param pLocUri
	 * @param monitor
	 * @throws Exception
	 * @throws CoreException
	 */
	private void createSaProject(
				SaImportBean saBean,
				URI pLocUri,
				IProgressMonitor monitor,
				List<MavenBean> suMavenBeans )
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
		String[] suNames = new String[ suMavenBeans.size()];
		for( int i=0; i<suMavenBeans.size(); i++ )
			suNames[ i ] = suMavenBeans.get( i ).getArtifactId();

		String jbiXmlContent = JbiUtils.generateJbiXmlForSA( "petals-se-eip", saBean.getProjectName(), suNames );
		IFile jbiXmlFile = project.getFile( PetalsConstants.LOC_JBI_FILE );
		if( ! jbiXmlFile.exists())
			jbiXmlFile.create( new ByteArrayInputStream( jbiXmlContent.getBytes()), true, monitor );
		else
			jbiXmlFile.setContents( new ByteArrayInputStream( jbiXmlContent.getBytes()), true, true, monitor );

		// SU dependencies
		try {
			IProjectDescription desc = project.getDescription();
			IProject[] projects = new IProject[ suMavenBeans.size()];
			IWorkspaceRoot iwr = ResourcesPlugin.getWorkspace().getRoot();
			for( int i=0; i<suMavenBeans.size(); i++ )
				projects[ i ] = iwr.getProject( suMavenBeans.get( i ).getArtifactId());

			desc.setReferencedProjects( projects );
			project.setDescription( desc, null );

		} catch( CoreException e ) {
			PetalsEipPlugin.log( e, IStatus.ERROR );
		}

		// Select the project
		this.resourcesToSelect.clear();
		this.resourcesToSelect.add( project );
		try {
			project.refreshLocal( IResource.DEPTH_INFINITE, monitor );

		} catch( Exception e ) {
			PetalsEipPlugin.log( e, IStatus.WARNING );
		}
	}


	/**
	 * @return a list of projects to create from the selected EIP chain
	 */
	private List<SaImportBean> getProjectsToCreate() {

		IWorkspaceRoot iwr = ResourcesPlugin.getWorkspace().getRoot();


		// Create the SU
		List<SuImportBean> suImportBeans = new ArrayList<SuImportBean> ();
		for( EipNode eip : this.eipChain.getEipNodes()) {

			SuImportBean suBean = new SuImportBean();
			suBean.setComponentName( "petals-se-eip" );
			suBean.setComponentVersion( EIP_VERSIONS[ 0 ]);
			suBean.setSupportedVersions( EIP_VERSIONS );
			suBean.setSuType( "EIP" );
			suBean.getKeyToObject().put( EIP_OBJ, eip );

			String name = JbiUtils.createSuName( "EIP", eip.getServiceName(), false );
			suBean.setProjectName( name );
			IProject p = iwr.getProject( name );
			suBean.setToCreate( ! p.exists());

			suImportBeans.add( suBean );
		}


		// Create the SA
		SaImportBean saBean = new SaImportBean();
		saBean.setJbiXmlLocation( null );
		saBean.addAllSuBeans( suImportBeans );

		String title = "EipChain";
		if( ! StringUtils.isEmpty( this.eipChain.getTitle())) {
			title = this.eipChain.getTitle().trim();
			title = title.replace( " ", "" ).replaceAll( "\\W", "_" );
		}

		String name = JbiUtils.createSaName( "EIP", title, false );
		saBean.setProjectName( name );

		IProject p = iwr.getProject( name );
		saBean.setToCreate( ! p.exists());

		return Arrays.asList( saBean );
	}
}
