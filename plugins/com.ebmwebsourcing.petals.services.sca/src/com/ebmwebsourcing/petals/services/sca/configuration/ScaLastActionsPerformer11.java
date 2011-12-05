/****************************************************************************
 *
 * Copyright (c) 2009-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.sca.configuration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.soa.sca.sca1_0.diagram.customactions.DiagramGeneration;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JaxWsUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JaxWsUtils.JaxWsException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlImportUtils;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.sca.Constants;
import com.ebmwebsourcing.petals.services.sca.PetalsScaPlugin;
import com.ebmwebsourcing.petals.services.sca.configuration.beans.ScaWizardBean;
import com.ebmwebsourcing.petals.services.sca.configuration.beans.ScaWizardReferenceBean;
import com.ebmwebsourcing.petals.services.sca.generated.DefaultComposite;
import com.ebmwebsourcing.petals.services.sca.generated.DefaultServiceImpl;
import com.ebmwebsourcing.petals.services.sca.generated.DefaultServiceInterface;
import com.ebmwebsourcing.petals.services.su.extensions.LastActionsPerformer;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;

/**
 * The class called at the end of the wizard to perform additional processing (version 1.1 of the SCA component).
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ScaLastActionsPerformer11 extends LastActionsPerformer {

	private static final String SCA_EXT = ".composite";
	private static final String JAVA_EXT = ".java";
	private static final int WORK_STEP = 5;


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.generation.LastActionsPerformer
	 * #performLastActions(org.eclipse.core.resources.IFolder,
	 * com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean,
	 * java.util.List, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void performLastActions(
			IFolder resourceFolder,
			EclipseSuBean eclipseSuBean,
			List<Object> resourcesToSelect,
			IProgressMonitor monitor ) {

		// Create the composite file
		String compositeName = (String) eclipseSuBean.customObjects.get( "CompositeName" );
		String compositeTns = (String) eclipseSuBean.customObjects.get( "CompositeTns" );
		ScaPattern scaPattern = (ScaPattern) eclipseSuBean.customObjects.get( "ScaPattern" );

		// Report what's happening
		SubMonitor subMonitor = SubMonitor.convert( monitor, IProgressMonitor.UNKNOWN );

		// Generate the SCA files
		try {
			IFile compositeFile;
			switch( scaPattern ) {
			case EMPTY:
				compositeFile = createEmptyComposite(
							compositeName, compositeTns,
							resourceFolder.getProject(),
							subMonitor );
				break;

			case NATIVE:
				compositeFile = createDefaultComposite(
							compositeName, compositeTns,
							resourceFolder.getProject(),
							new ArrayList<ScaWizardReferenceBean> (),
							subMonitor );
				break;

			case COMPOSITION:
				Set<EndpointBean> edptBeans = (Set<EndpointBean>) eclipseSuBean.customObjects.get( "Endpoints" );
				List<ScaWizardReferenceBean> references = buildReferences(
							edptBeans,
							resourceFolder.getProject(),
							subMonitor );

				compositeFile = createDefaultComposite(
							compositeName, compositeTns,
							resourceFolder.getProject(),
							references,
							subMonitor );
				break;

			default:
				compositeFile = null;
				return;
			}


			// Create the diagram
			final IFile diagramFile = createDiagramFile( compositeFile, subMonitor );

			// Add the SCA nature to the project
			try {
				IProjectDescription description = resourceFolder.getProject().getDescription();
				String[] natures = description.getNatureIds();

				String[] newNatures = new String[ natures.length + 1 ];
				System.arraycopy( natures, 0, newNatures, 0, natures.length );
				newNatures[ natures.length ] = Constants.SCA_NATURE_ID;

				description.setNatureIds( newNatures );
				resourceFolder.getProject().setDescription( description, subMonitor );

			} catch( CoreException e ) {
				PetalsScaPlugin.log( e, IStatus.ERROR );
			}

			// Generate a default jbi.xml
			createEmptyJbiXml( resourceFolder, subMonitor );

			// Open the composite diagram file.
			Display.getDefault().asyncExec( new Runnable() {
				public void run() {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					try {
						IDE.openEditor( page, diagramFile, true );

					} catch( PartInitException e ) {
						PetalsScaPlugin.log( e, IStatus.WARNING );
					}
				}
			});

			// Select the file in the explorer.
			resourcesToSelect.add( diagramFile );

		} catch( ExecutionException e ) {
			PetalsScaPlugin.log( e, IStatus.ERROR );

		} catch( CoreException e ) {
			PetalsScaPlugin.log( e, IStatus.ERROR );

		} catch( IOException e ) {
			PetalsScaPlugin.log( e, IStatus.ERROR );
		}
	}


	/**
	 *
	 * @param compositeName
	 * @param compositeTns
	 * @param compositeFileName
	 * @param scaProject
	 * @param monitor
	 * @return
	 */
	private IFile createEmptyComposite(
				String compositeName,
				String compositeTns,
				IProject scaProject,
				IProgressMonitor monitor ) {

		// Report
		monitor.setTaskName( "Creating an empty composite" );

		// Here is the template
		String tpl = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<sca:composite\n\t"
			+ "xmlns=\"http://www.osoa.org/xmlns/sca/1.0\"\n\t"
			+ "xmlns:sca=\"http://www.osoa.org/xmlns/sca/1.0\"\n\t"
			+ "xmlns:frascati=\"http://frascati.ow2.org/xmlns/sca/1.1\"\n\t"
			+ "name=\"" + compositeName + "\" "
			+ "targetNamespace=\"" + compositeTns + "\">\n\n"
			+ "</sca:composite>";

		// Write it in the new project
		IFolder srcFolder = scaProject.getFolder( PetalsConstants.LOC_SRC_FOLDER );
		final IFile file = srcFolder.getFile( compositeName + SCA_EXT );
		try {
			byte[] input = tpl.getBytes();
			file.create( new ByteArrayInputStream( input ), true, monitor );

		} catch( CoreException e ) {
			PetalsScaPlugin.log( e, IStatus.ERROR );
		}

		monitor.worked( WORK_STEP );
		return file;
	}


	/**
	 *
	 * @param compositeName
	 * @param compositeTns
	 * @param scaProject
	 * @param references
	 * @param monitor
	 * @throws CoreException
	 * @return
	 */
	private IFile createDefaultComposite(
				String compositeName,
				String compositeTns,
				IProject scaProject,
				List<ScaWizardReferenceBean> references,
				IProgressMonitor monitor )
	throws CoreException {

		// Reporting
		monitor.setTaskName( "Creating the SCA composite" );

		// Create the bean
		ScaWizardBean bean = new ScaWizardBean();
		bean.setCompositeName( compositeName );
		bean.setCompositeTns( compositeTns );
		bean.setPackageName( "org.ow2.petals" );
		bean.setServiceImplName( "MainServiceImpl" );
		bean.setServiceInterfaceName( "MainService" );
		bean.addAllReferences( references );

		// Create the package
		IFolder srcFolder = scaProject.getFolder( PetalsConstants.LOC_SRC_FOLDER );
		for( String packagePart : bean.getPackageName().split( "\\." )) {
			srcFolder = srcFolder.getFolder( packagePart );
			if( ! srcFolder.exists())
				srcFolder.create( true, true, monitor );
		}

		// Create the service interface
		IFile file = srcFolder.getFile( bean.getServiceInterfaceName() + JAVA_EXT );
		String tpl = new DefaultServiceInterface().generate( bean );
		file.create( new ByteArrayInputStream( tpl.getBytes()), true, monitor );

		// Create the service implementation
		file = srcFolder.getFile( bean.getServiceImplName() + JAVA_EXT );
		tpl = new DefaultServiceImpl().generate( bean );
		file.create( new ByteArrayInputStream( tpl.getBytes()), true, monitor );

		// Create the composite
		srcFolder = scaProject.getFolder( PetalsConstants.LOC_SRC_FOLDER );
		file = srcFolder.getFile( compositeName + SCA_EXT );
		tpl = new DefaultComposite().generate( bean );
		file.create( new ByteArrayInputStream( tpl.getBytes()), true, monitor );

		monitor.worked( WORK_STEP );
		return file;
	}


	/**
	 *
	 * @param edptBeans
	 * @return
	 * @throws CoreException
	 */
	private List<ScaWizardReferenceBean> buildReferences(
				Set<EndpointBean> edptBeans,
				IProject scaProject,
				IProgressMonitor monitor ) throws CoreException {

		// Reporting
		monitor.setTaskName( "Building the SCA references" );

		// Create a new source folder
		IJavaProject jp = JavaCore.create( scaProject );
		IFolder newSourceFolder = JavaUtils.createSourceFolder( jp, "src-references", monitor );
		File newSourceFile = newSourceFolder.getLocation().toFile();

		// Keep the location of the resource folder
		File resFile = scaProject.getFolder( PetalsConstants.LOC_RES_FOLDER ).getLocation().toFile();

		// Keep the generated JAX-WS classes
		Set<String> jaxWsInterfaces = new HashSet<String> ();

		// Process the references
		List<ScaWizardReferenceBean> result = new ArrayList<ScaWizardReferenceBean> ();
		IFolder jbiFolder = scaProject.getFolder( PetalsConstants.LOC_RES_FOLDER );
		File jbiFile = jbiFolder.getLocation().toFile();
		for( EndpointBean bean : edptBeans ) {

			// Import the WSDL in the JBI directory
			java.net.URI wsdlUri = bean.getWsdlUri();
			if( wsdlUri == null ) {
				String msg = "No WSDL could be found for a Petals end-point to be referenced by a SCA application.";
				PetalsScaPlugin.log( msg, IStatus.ERROR );
				continue;
			}

			monitor.worked( 1 );
			Map<String,File> importResultMap =
				new WsdlImportUtils().importWsdlOrXsdAndDependencies( jbiFile, wsdlUri.toString());

			File wsdlFile = importResultMap.get( wsdlUri.toString());
			if( wsdlFile == null
						|| ! wsdlFile.exists()) {
				String msg = "The WSDL import of a Petals end-point failed (SCA creation).";
				PetalsScaPlugin.log( msg, IStatus.ERROR );
				continue;
			}

			// Generate the Java classes
			try {
				monitor.worked( 1 );
				JaxWsUtils.INSTANCE.generateWsClient( wsdlFile.toURI(), newSourceFile );

			} catch( IOException e ) {
				PetalsScaPlugin.log( e, IStatus.ERROR );
				continue;

			} catch( InterruptedException e ) {
				PetalsScaPlugin.log( e, IStatus.ERROR );
				continue;

			} catch( JaxWsException e ) {
				PetalsScaPlugin.log( e, IStatus.ERROR );
				continue;
			}

			// Find the new generated JAX-WS interface
			monitor.worked( 1 );
			Set<String> newJaxWsInterfaces =
				JaxWsUtils.getJaxAnnotatedJavaTypes( jp, monitor, false, true ).keySet();

			newJaxWsInterfaces.removeAll( jaxWsInterfaces );
			if( newJaxWsInterfaces.size() != 1 ) {
				String msg = "Unexpected number of Jax-WS interfaces (" + newJaxWsInterfaces.size() + ").";
				PetalsScaPlugin.log( msg, IStatus.ERROR );
				continue;
			}

			// Build the reference bean
			monitor.worked( 1 );
			ScaWizardReferenceBean refBean = new ScaWizardReferenceBean();
			String interfaceClassName = newJaxWsInterfaces.iterator().next();
			refBean.setInterfaceClassName( interfaceClassName );
			jaxWsInterfaces.add( interfaceClassName );

			refBean.setEndpointName( bean.getEndpointName());
			if( bean.getInterfaceName() != null ) {
				refBean.setInterfaceName( bean.getInterfaceName().getLocalPart());
				refBean.setInterfaceNamespace( bean.getInterfaceName().getNamespaceURI());
			}

			if( bean.getServiceName() != null ) {
				refBean.setServiceName( bean.getServiceName().getLocalPart());
				refBean.setServiceNamespace( bean.getServiceName().getNamespaceURI());
			}

			String simpleName = interfaceClassName;
			int index = simpleName.lastIndexOf( '.' );
			if( index != -1
						&& index + 1 < simpleName.length())
				simpleName = simpleName.substring( index + 1 );

			String start = String.valueOf( simpleName.charAt( 0 )).toLowerCase();
			refBean.setSimpleInterfaceClassName( simpleName );
			refBean.setReferenceName( start + simpleName.substring( 1 ));

			String wsdlLocation = IoUtils.getRelativeLocationToFile( resFile, wsdlFile );
			refBean.setWsdlLocation( wsdlLocation );
			result.add( refBean );
		}

		// Remove the Web Service clients
		monitor.worked( WORK_STEP );
		JaxWsUtils.removeWebServiceClient( jp, monitor );
		scaProject.refreshLocal( IResource.DEPTH_INFINITE, monitor );
		scaProject.build( IncrementalProjectBuilder.FULL_BUILD, monitor );

		return result;
	}


	/**
	 *
	 * @param compositeFile
	 * @param monitor
	 * @return
	 * @throws ExecutionException
	 * @throws CoreException
	 * @throws IOException
	 */
	private IFile createDiagramFile( IFile compositeFile, IProgressMonitor monitor )
	throws ExecutionException, CoreException, IOException {

		// Reporting
		monitor.setTaskName( "Creating the SCA diagram" );

		// Create the diagram file
		DiagramGeneration generateAction = new DiagramGeneration();
		generateAction.runExternal( compositeFile );

		// Guess what is the diagram file
		IPath path = new Path( compositeFile.getName()).removeFileExtension().addFileExtension( "composite_diagram" );
		IFile diagramFile = compositeFile.getParent().getFile( path );

		monitor.worked( WORK_STEP );
		return diagramFile;
	}


	/**
	 *
	 * @param resourceFolder
	 * @param monitor
	 * @throws CoreException
	 */
	private void createEmptyJbiXml( IFolder resourceFolder, IProgressMonitor monitor )
	throws CoreException {

		monitor.setTaskName( "Creating an empty jbi.xml" );

		IFile file = resourceFolder.getFile( "jbi.xml" );
		StringBuilder sb = new StringBuilder();
		sb.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" );
		sb.append( "<jbi:jbi version=\"1.0\" \n" );
		sb.append( "\txmlns:jbi=\"http://java.sun.com/xml/ns/jbi\"\n" );
		sb.append( "\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" );
		sb.append( "\t\t<jbi:services binding-component=\"false\">\n" );
		sb.append( "\\t</jbi:services>\n" );
		sb.append( "</jbi:jbi>\n" );

		file.create( new ByteArrayInputStream( sb.toString().getBytes()), true, monitor );
		monitor.worked( WORK_STEP );
	}
}
