/******************************************************************************
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.bpel;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.w3c.dom.Document;

import com.ebmwebsourcing.petals.common.generation.JbiXmlGenerationHelper;
import com.ebmwebsourcing.petals.common.generation.cdk5.components.BpelProvides10;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.services.bpel.designer.builder.PetalsBpelNature;
import com.ebmwebsourcing.petals.services.bpel.designer.provisional.PetalsBpelModules;
import com.ebmwebsourcing.petals.services.bpel.designer.provisional.PetalsBpelModules.JbiXmlBean;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BpelWizard10 extends ComponentWizardHandler {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new BpelDescription10();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getOverridenWizardSettings()
	 */
	@Override
	public Map<String, String> getOverridenWizardSettings() {

		Map<String,String> result = new HashMap<String,String> ();
		result.put( SuWizardSettings.SHOW_JBI_PAGE, "false" );
		return result;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(
			IFolder resourceFolder, AbstractEndpoint abstractEndpoint,
			IProgressMonitor monitor, List<Object> resourcesToSelect ) {

//		String bpelName = null;
//
//		// Get the BPEL element
//		XmlElement bpelElement = null;
//		for( XmlElement elt : eclipseSuBean.specificElements ) {
//			if( "bpel-engine:bpel".equals( elt.getName())) {
//				bpelElement = elt;
//				break;
//			}
//		}
//
//		// Create the BPEL file
//		BpelCreationMode creationMode = (BpelCreationMode) eclipseSuBean.customObjects.get( "generateBpel" );
//		if( creationMode != null ) {
//			if( creationMode == BpelCreationMode.createAll ) {
//				String processName = eclipseSuBean.getProjectName();
//
//				// Hack for SU names
//				if( processName.startsWith( "su-BPEL-" )) {
//					processName = processName.substring( 8 );
//					processName = processName.replaceFirst( "-provide.*", "" );
//				}
//				// End of hack
//
//				processName = processName.replaceAll( "\\W", "_" );
//				bpelName = processName + ".bpel";
//
//				IFile file = resourceFolder.getFile( processName + "Definition.wsdl" );
//				String result = new ProcessWsdl().generate( processName );
//				createFile( file, result, monitor );
//
//				file = resourceFolder.getFile( processName + ".bpel" );
//				result = new Process().generate( processName );
//				createFile( file, result, monitor );
//
//				file = resourceFolder.getFile( processName + "Artifacts.wsdl" );
//				result = new ProcessArtifacts().generate( processName );
//				createFile( file, result, monitor );
//			}
//
//			else if( creationMode == BpelCreationMode.generateBpel ) {
//				try {
//					Description desc = (Description) eclipseSuBean.customObjects.get( "wsdlDescription" );
//					QName interfaceName = (QName) eclipseSuBean.customObjects.get( "wsdlName" );
//					bpelName = bpelElement != null ? bpelElement.getValue() : null;
//
//					BPELGenerator generator = new BPELGeneratorImpl();
//					BPELProject project = generator.generateDefaultBPELProjectFromInterface( interfaceName, desc );
//					if( bpelName == null )
//						bpelName = project.getBpelFileName();
//
//
//					// Do not write the WSDL of the BPEL: imports are not processed.
//					// Instead, we have to import it in the project.
//					Map<String,File> urlToFile = new WsdlImportUtils().importWsdlOrXsdAndDependencies(
//								resourceFolder.getLocation().toFile(),
//								eclipseSuBean.getWsdlUrl());
//
//					// Prepare the file names to update the generated documents
//					String wsdlName = urlToFile.get( eclipseSuBean.getWsdlUrl()).getName();
//					String artifactsName =
//						resourceFolder.getFile( bpelName ).getLocation()
//						.removeFileExtension().lastSegment() + "Artifacts.wsdl";
//
//
//					// Write the WSDL artifacts
//					Document doc = WSDL4BPELFactory.newInstance().newWSDLWriter().getDocument( project.getWsdlArtifacts());
//					String content = write( doc );
//					content = content.replaceAll( Pattern.quote( project.getWsdlFileName()), wsdlName );
//
//					IFile file = resourceFolder.getFile( artifactsName );
//					if( file.exists())
//						file.setContents( new ByteArrayInputStream( content.getBytes()), true, true, monitor );
//					else
//						file.create( new ByteArrayInputStream( content.getBytes()), true, monitor );
//
//
//					// Write the BPEL
//					doc = new BPELWriterImpl().getDocument( project.getBpelProcess());
//					content = write( doc );
//					content = content.replaceAll( Pattern.quote( project.getWsdlFileName()), wsdlName );
//					content = content.replaceAll( Pattern.quote( project.getArtifactFileName()), artifactsName );
//
//					// Hack for bug PETALSSTUD-16 (not yet fixed in EasyBPEL)
//					content = content.replaceAll( "myRole=\"mainPartnerRole\"", "myRole=\"" + interfaceName.getLocalPart() + "Role\"" );
//					String abstractNsPattern = Pattern.quote( "xmlns:ns3=\"http://docs.oasis-open.org/wsbpel/2.0/process/abstract\"" );
//					content = content.replaceAll( abstractNsPattern, "" );
//					content = content.replaceAll( "ns3:", "ns2:" );
//					// Hack for bug PETALSSTUD-16
//
//					file = resourceFolder.getFile( bpelName );
//					if( file.exists())
//						file.setContents( new ByteArrayInputStream( content.getBytes()), true, true, monitor );
//					else
//						file.create( new ByteArrayInputStream( content.getBytes()), true, monitor );
//
//				} catch( Exception e ) {
//					PetalsBpelPlugin.log( e, IStatus.ERROR );
//				}
//			}
//		}


		// Add the nature "BPEL extension for Petals"
		try {
			IProjectDescription description = resourceFolder.getProject().getDescription();
			String[] natures = description.getNatureIds();

			String[] newNatures = new String[ natures.length + 1 ];
			System.arraycopy( natures, 0, newNatures, 0, natures.length );
			newNatures[ natures.length ] = PetalsBpelNature.NATURE_ID;

			description.setNatureIds( newNatures );
			resourceFolder.getProject().setDescription( description, monitor );
			resourceFolder.getProject().refreshLocal( IResource.DEPTH_INFINITE, monitor );

		} catch( CoreException e ) {
			PetalsBpelPlugin.log( e, IStatus.ERROR );
		}


		// Get the BPEL file name
//		if( bpelName == null )
//			bpelName = bpelElement != null ? bpelElement.getValue() : null;
//
//		IFile bpelFile = resourceFolder.getFile( bpelName );
//
//
//		// SU project => create the jbi.xml file
//		try {
//			String urlAsString = UriUtils.convertFilePathToUrl( bpelFile.getLocation().toFile().getAbsolutePath());
//			createJbiXml( bpelName, new URL( urlAsString ), resourceFolder.getProject(), monitor );
//
//		} catch( MalformedURLException e ) {
//			PetalsBpelPlugin.log( e, IStatus.ERROR );
//
//		} catch( CoreException e ) {
//			PetalsBpelPlugin.log( e, IStatus.ERROR );
//		}


		// Select the BPEL file
//		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//		try {
//			IDE.openEditor( page, bpelFile );
//			resourcesToSelect.add( bpelFile );
//
//		} catch( PartInitException e ) {
//			PetalsBpelPlugin.log( e, IStatus.ERROR );
//		}

		return Status.OK_STATUS;
	}


	/**
	 * Creates a default jbi.xml for a brand new BPEL project.
	 * @param bpelName the name of the BPEL file
	 * @param bpelUrl the URL of the BPEL file
	 * @param suProject the SU project
	 * @param monitor a progress monitor
	 * @throws CoreException
	 */
	private void createJbiXml( String bpelName, URL bpelUrl, IProject suProject, IProgressMonitor monitor )
	throws CoreException {

		List<JbiXmlBean> jbiXmlBeans = PetalsBpelModules.getJbiXmlBean( bpelUrl );
		List<BpelProvides10> bpelProvides = new ArrayList<BpelProvides10>();
		for( JbiXmlBean jxb : jbiXmlBeans ) {

			BpelProvides10 bp = new BpelProvides10();
			bp.setEndpointName( jxb.getEndpointName());
			bp.setBc( false );
			bp.setPoolSize( 1 );
			bp.setProcessName( bpelName );
			bp.setProvides( true );
			bp.setValidateWsdl( true );

			QName itf = jxb.getInterfaceName();
			if( itf != null ) {
				bp.setInterfaceName( itf.getLocalPart());
				bp.setInterfaceNamespace( itf.getNamespaceURI());
			}

			QName srv = jxb.getServiceName();
			if( srv != null ) {
				bp.setServiceName( srv.getLocalPart());
				bp.setServiceNamespace( srv.getNamespaceURI());
			}

			URI wsdlUri = jxb.getWsdlUri();
			if( wsdlUri != null ) {
				URI uri = UriUtils.getRelativeLocationToUri( UriUtils.urlToUri( bpelUrl ), wsdlUri );
				bp.setWsdl( uri.toString());
			}

			bpelProvides.add( bp );
		}

		JbiXmlGenerationHelper genDelegate = new JbiXmlGenerationHelper();
		genDelegate.setComponentName( "petals-se-bpel" );
		BpelProvides10[] bps = new BpelProvides10[ bpelProvides.size()];
		String jbiXmlContent = genDelegate.createJbiDescriptor( bpelProvides.toArray( bps )).toString();

		IFile jbiXmlFile = suProject.getFile( PetalsConstants.LOC_JBI_FILE );
		if( ! jbiXmlFile.exists())
			jbiXmlFile.create( new ByteArrayInputStream( jbiXmlContent.getBytes()), true, monitor );
		else
			jbiXmlFile.setContents( new ByteArrayInputStream( jbiXmlContent.getBytes()), true, true, monitor );
	}


	/**
	 * Writes a DOM document into an IFile.
	 * @param doc
	 * @param file
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	private String write( Document doc )
	throws TransformerFactoryConfigurationError, TransformerException {

		Source source = new DOMSource( doc );
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult( writer );

		Transformer xformer = TransformerFactory.newInstance().newTransformer();
		xformer.setOutputProperty( OutputKeys.INDENT, "yes" );
		xformer.setOutputProperty( "{http://xml.apache.org/xslt}indent-amount", "4" );
		xformer.transform( source, result );

		return writer.toString();
	}
}
