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

package com.ebmwebsourcing.petals.services.jsr181;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JaxWsUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JaxWsUtils.JaxWsException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlImportUtils;
import com.ebmwebsourcing.petals.services.jsr181.generated.JaxWsImplementation;
import com.ebmwebsourcing.petals.services.jsr181.handlers.Jsr181GenerationHandler;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Jsr181Wizard11 extends ComponentWizardHandler {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new Jsr181Description11();
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
		result.put( SuWizardSettings.OPEN_JBI_XML, "false" );

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

		IStatus result = Status.OK_STATUS;
		try {
			// Java project
			IJavaProject jp = JavaUtils.createJavaProject( resourceFolder.getProject());

			// Start working on the JAX-WS part
//			boolean wsdlFirst = (Boolean) eclipseSuBean.customObjects.get( Jsr181ProvideSpecificPage10.CONFIG_WSDL_FIRST );
//			if( wsdlFirst )
//				wsdlFirstApproach( jp, eclipseSuBean, resourcesToSelect, monitor );
//			else
//				implementationFirstApproach( jp.getProject(), eclipseSuBean, resourcesToSelect, monitor );

		} catch( CoreException e ) {
			result = new Status( Status.ERROR, PetalsJsr181Plugin.PLUGIN_ID, "Jsr181 Error", e );
		}

		return result;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #validatePrerequisites()
	 */
	@Override
	public String validatePrerequisites() {

		String errorMsg = null;
		try {
			JaxWsUtils.getJavaExecutable( true );
			JaxWsUtils.getJavaExecutable( false );

		} catch( IOException e ) {
			errorMsg = e.getMessage();
		}

		return errorMsg;
	}


	/**
	 * Completes the wizard for an implementation-first approach.
	 *
	 * @param project
	 * @param ae
	 * @param resourcesToSelect
	 * @param monitor
	 * TODO: fix it
	 */
	private void implementationFirstApproach( IProject project, AbstractEndpoint ae, List<Object> resourcesToSelect, IProgressMonitor monitor ) {

		IFolder srcFolder = project.getFolder( PetalsConstants.LOC_SRC_FOLDER );
		Properties generationProperties = new Properties();

		String className = "class name (FIXME)";
		int lastDotIndex = className.lastIndexOf( '.' );
		String packageName, simpleClassName;

		// The page validation guarantees that the class name respects Java conventions
		if( lastDotIndex > 0 ) {
			simpleClassName = className.substring( lastDotIndex + 1 );
			packageName = className.substring( 0, lastDotIndex );
		}
		else {
			simpleClassName = className;
			packageName = "";
		}

		// Fill-in the generation properties
		generationProperties.put( Jsr181Constants.INTERFACE_NAME, simpleClassName + "Interface" );
		generationProperties.put( Jsr181Constants.CLASS_NAME, simpleClassName );
		generationProperties.put( Jsr181Constants.PACKAGE_NAME, packageName );
		generationProperties.put( Jsr181Constants.PROJECT_NAME, "FIXME" );
		generationProperties.put( Jsr181Constants.PROJECT_LOCATION, srcFolder.getProject().getLocation().toString());

		generationProperties.put( Jsr181Constants.SERVICE_NAME, ae.getServiceName().getLocalPart());
		generationProperties.put( Jsr181Constants.PORT_NAME, ae.getEndpointName());
		generationProperties.put( Jsr181Constants.TNS, ae.getServiceName().getNamespaceURI());

		// Create the package
		StringBuffer sb = new StringBuffer();
		for( String path : packageName.trim().split( "\\." ))
			sb.append( path + "/" );

				File packageDirectory = srcFolder.getLocation().append( sb.toString()).toFile();
				if( ! packageDirectory.exists()
						&& ! packageDirectory.mkdirs()) {
					PetalsJsr181Plugin.log( new IOException( "Could not create the Java package." ), IStatus.ERROR );
				}

				try {
					srcFolder.refreshLocal( IResource.DEPTH_INFINITE, monitor );
				} catch( CoreException e ) {
					// nothing
				}

				if( packageDirectory.exists() && ! packageDirectory.isFile()) {

					// Create the JAX-WS implementation
					IFile file = srcFolder.getFile( sb.toString() + simpleClassName + ".java" );
					createFile( file, new JaxWsImplementation().generate( generationProperties ), monitor );

					// Open the implementation in the Java editor
					try {
						IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
						IDE.openEditor( page, file );

					} catch( PartInitException e ) {
						PetalsJsr181Plugin.log( e, IStatus.ERROR );

					} finally {
						resourcesToSelect.add( file );
					}
				}
	}


	/**
	 * Completes the wizard for a WSDL-first approach.
	 *
	 * @param jp the Java project
	 * @param ae
	 * @param resourcesToSelect
	 * @param monitor
	 * TODO: fix it
	 */
	private void wsdlFirstApproach( IJavaProject jp, AbstractEndpoint ae, List<Object> resourcesToSelect, IProgressMonitor monitor ) {

		try {
			// Create the WS implementation
			IProject project = jp.getProject();
			String uriAsString = "FIXME"; //(String) eclipseSuBean.customObjects.get( Jsr181ProvideSpecificPage10.CONFIG_WSDL_URI );
			URI wsdlUri = UriUtils.urlToUri( uriAsString );

			IFolder srcFolder = project.getFolder( PetalsConstants.LOC_SRC_FOLDER );
			File srcDirectory = srcFolder.getLocation().toFile();

			Map<String,String> buildOptions = new HashMap<String,String> ();
			JaxWsUtils.INSTANCE.generateWsClient( wsdlUri, srcDirectory );
			srcFolder.refreshLocal( IResource.DEPTH_INFINITE, monitor );
			project.build( IncrementalProjectBuilder.FULL_BUILD, JavaCore.BUILDER_ID, buildOptions, monitor );

			srcFolder.refreshLocal( IResource.DEPTH_INFINITE, monitor );
			JaxWsUtils.removeWebServiceClient( jp, monitor );
			Map<String,String> serviceNameToClassName = JaxWsUtils.createJaxWsImplementation( jp, monitor );
			srcFolder.refreshLocal( IResource.DEPTH_INFINITE, monitor );
			project.build( IncrementalProjectBuilder.FULL_BUILD, JavaCore.BUILDER_ID, buildOptions, monitor );


			// Import the WSDL in the project
			IFolder resFolder = project.getFolder( PetalsConstants.LOC_RES_FOLDER );
			File resFile = resFolder.getLocation().toFile();
			Map<String,File> uriToFile = new WsdlImportUtils().importWsdlOrXsdAndDependencies( resFile, uriAsString );
			String wsdlName = uriToFile.get( uriAsString ).getName();
			resFolder.refreshLocal( IResource.DEPTH_INFINITE, monitor );


			// Create the jbi.xml
			Map<String,String> classNameToWsdlName = new HashMap<String,String> ();
			for( String className : serviceNameToClassName.values())
				classNameToWsdlName.put( className, wsdlName );

			Jsr181GenerationHandler.generateJbiXml( project, classNameToWsdlName, monitor );

			// Open the implementations in the Java editor
			project.refreshLocal( IResource.DEPTH_INFINITE, monitor );
			// IFile file = project.getFile( PetalsConstants.LOC_JBI_FILE );

			List<IFile> javaFiles = new ArrayList<IFile> ();
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			for( String className : serviceNameToClassName.values()) {
				IFile javaFile = srcFolder.getFile( className.replaceAll( "\\.", "/" ) + ".java" );
				javaFiles.add( javaFile );
				IDE.openEditor( page, javaFile );
			}

			resourcesToSelect.addAll( javaFiles );

		} catch( PartInitException e ) {
			PetalsJsr181Plugin.log( e, IStatus.ERROR );

		} catch( CoreException e ) {
			PetalsJsr181Plugin.log( e, IStatus.ERROR );

		} catch( JaxWsException e ) {
			PetalsJsr181Plugin.log( e, IStatus.ERROR );

		} catch( IOException e ) {
			PetalsJsr181Plugin.log( e, IStatus.ERROR );

		} catch( InterruptedException e ) {
			PetalsJsr181Plugin.log( e, IStatus.ERROR );
		}
	}
}
