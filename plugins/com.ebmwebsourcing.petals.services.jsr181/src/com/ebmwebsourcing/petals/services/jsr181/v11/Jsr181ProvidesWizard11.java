/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.jsr181.v11;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.bpel.common.wsdl.importhelpers.WsdlImportHelper;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JaxWsUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JaxWsUtils.JaxWsException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.JbiBasicBean;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.jsr181.Jsr181Constants;
import com.ebmwebsourcing.petals.services.jsr181.PetalsJsr181Plugin;
import com.ebmwebsourcing.petals.services.jsr181.generated.JaxWsImplementation;
import com.ebmwebsourcing.petals.services.jsr181.jsr181.Jsr181Package;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Jsr181ProvidesWizard11 extends AbstractServiceUnitWizard {

	private Jsr181ProvidePage11 page;


	/**
	 * Constructor.
	 */
	public Jsr181ProvidesWizard11() {
		super();
		this.settings.showJbiPage = false;
		this.settings.openJbiEditor = false;
	}


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
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint,
	 * org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {

		IStatus result = Status.OK_STATUS;
		try {
			// Java project
			IJavaProject jp = JavaUtils.createJavaProject( resourceFolder.getProject());

			// Start working on the JAX-WS part
			if( this.page.isWsdlFirst()) {
				wsdlFirstApproach( jp, abstractEndpoint, monitor );
			} else {
				implementationFirstApproach( jp.getProject(), abstractEndpoint, monitor );
			}

		} catch( CoreException e ) {
			result = new Status( Status.ERROR, PetalsJsr181Plugin.PLUGIN_ID, "Jsr181 Error", e );
		}

		return result;
	}


	/**
	 * Completes the wizard for an implementation-first approach.
	 *
	 * @param project
	 * @param ae
	 * @param resourcesToSelect
	 * @param monitor
	 */
	private void implementationFirstApproach( IProject project, AbstractEndpoint ae, IProgressMonitor monitor ) {

		IFolder srcFolder = project.getFolder( PetalsConstants.LOC_SRC_FOLDER );

		String className = this.page.getClassToGenerate();
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


		// Set values
		String[] nsParts = packageName.trim().split( "\\." );
		StringBuffer nsStringBuffer = new StringBuffer( "http://" );
		for( int i=nsParts.length - 1; i>0; i-- )
			nsStringBuffer.append( nsParts[ i ] + "." );
		nsStringBuffer.append( nsParts[ 0 ]);
		String namespace = nsStringBuffer.toString();

		ae.setServiceName( new QName(namespace, simpleClassName) );
		ae.setInterfaceName( new QName(namespace, simpleClassName) );
		ae.setEndpointName(simpleClassName + "Port");
		ae.eSet( Jsr181Package.Literals.JSR181_PROVIDES__CLAZZ, className );


		// Fill-in the generation properties
		Properties generationProperties = new Properties();
		generationProperties.put( Jsr181Constants.INTERFACE_NAME, simpleClassName + "Interface" );
		generationProperties.put( Jsr181Constants.CLASS_NAME, simpleClassName );
		generationProperties.put( Jsr181Constants.PACKAGE_NAME, packageName );
		generationProperties.put( Jsr181Constants.PROJECT_NAME, project.getName());
		generationProperties.put( Jsr181Constants.PROJECT_LOCATION, srcFolder.getProject().getLocation().toString());

		generationProperties.put( Jsr181Constants.SERVICE_NAME, ae.getServiceName().getLocalPart());
		generationProperties.put( Jsr181Constants.PORT_NAME, ae.getEndpointName());
		generationProperties.put( Jsr181Constants.TNS, ae.getServiceName().getNamespaceURI());


		// Create the package
		StringBuffer sb = new StringBuffer();
		for( String path : packageName.trim().split( "\\." )) {
			sb.append( path + "/" );
		}

		File packageDirectory = srcFolder.getLocation().append( sb.toString()).toFile();
		if( ! packageDirectory.exists()
				&& ! packageDirectory.mkdirs()) {
			PetalsJsr181Plugin.log( new IOException( "Could not create the Java package." ), IStatus.ERROR );
			return;
		}

		try {
			srcFolder.refreshLocal( IResource.DEPTH_INFINITE, monitor );
		} catch( CoreException e ) {
			// nothing
		}


		// Create the JAX-WS implementation
		final IFile file = srcFolder.getFile( sb.toString() + simpleClassName + ".java" );
		createFile( file, new JaxWsImplementation().generate( generationProperties ), monitor );

		// Open the implementation in the Java editor
		Display.getDefault().asyncExec( new Runnable() {
			@Override
			public void run() {

				try {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					IDE.openEditor( page, file );

				} catch( PartInitException e ) {
					PetalsJsr181Plugin.log( e, IStatus.ERROR );

				}
			}
		});

		// Select the created file
		ResourceUtils.selectResourceInPetalsExplorer( true, file );
	}


	/**
	 * Completes the wizard for a WSDL-first approach.
	 *
	 * @param jp the Java project
	 * @param ae
	 * @param resourcesToSelect
	 * @param monitor
	 */
	private void wsdlFirstApproach( IJavaProject jp, AbstractEndpoint ae, IProgressMonitor monitor ) {

		try {
			// Create the WS implementation
			IProject project = jp.getProject();
			URI wsdlUri = UriAndUrlHelper.urlToUri( this.page.getWsdlUriAsString());

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

			// Update JBI
			JbiBasicBean bean = WsdlUtils.INSTANCE.parse( this.page.getWsdlUriAsString()).get( 0 );
			ae.eSet(JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME, bean.getInterfaceName());
			ae.eSet(JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME, bean.getServiceName());
			ae.eSet(JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME, bean.getEndpointName());
			ae.eSet(Jsr181Package.Literals.JSR181_PROVIDES__CLAZZ, serviceNameToClassName.values().iterator().next());

			// Import the WSDL in the project
			IFolder resFolder = project.getFolder( PetalsConstants.LOC_RES_FOLDER );
			File resFile = resFolder.getLocation().toFile();
			Map<String,File> uriToFile = new WsdlImportHelper().importWsdlOrXsdAndDependencies( resFile, this.page.getWsdlUriAsString());
			File f = uriToFile.get( this.page.getWsdlUriAsString());
			String wsdlName = f == null ? wsdlUri.toURL().getFile() : f.getName();
			ae.eSet( Cdk5Package.Literals.CDK5_PROVIDES__WSDL, wsdlName );

			resFolder.refreshLocal( IResource.DEPTH_INFINITE, monitor );

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

		} catch( InvocationTargetException e ) {
			PetalsJsr181Plugin.log( e, IStatus.ERROR );

		} catch( URISyntaxException e ) {
			PetalsJsr181Plugin.log( e, IStatus.ERROR );

		} catch( SAXException e ) {
			PetalsJsr181Plugin.log( e, IStatus.ERROR );

		} catch( ParserConfigurationException e ) {
			PetalsJsr181Plugin.log( e, IStatus.ERROR );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #presetServiceValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	protected void presetServiceValues(AbstractEndpoint endpoint) {
		Cdk5Utils.setInitialProvidesValues((Provides)endpoint);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getCustomWizardPagesAfterProject()
	 */
	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterProject() {
		this.page = new Jsr181ProvidePage11();
		return new AbstractSuWizardPage[] { this.page };
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #isJavaProject()
	 */
	@Override
	protected boolean isJavaProject() {
		return true;
	}
}
