/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.su.export;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.emf.InvalidJbiXmlException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.jbiproperties.PetalsSPPropertiesManager;
import com.ebmwebsourcing.petals.services.utils.ExportUtils;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuBulkExportWizard extends Wizard implements IExportWizard {

	private SuBulkExportWizardPage page;
	private IStructuredSelection selection;


	/**
	 * Constructor.
	 */
	public SuBulkExportWizard() {
		super();
		setNeedsProgressMonitor( true );
		setWindowTitle( "Bulk Services" );

		ImageDescriptor desc = PetalsServicesPlugin.getImageDescriptor( "icons/wizban/wiz_jbi_export.png" );
		setDefaultPageImageDescriptor( desc );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPages()
	 */
	@Override
	public void addPages() {
		this.page = new SuBulkExportWizardPage( this.selection );
		addPage( this.page );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #performFinish()
	 */
	@Override
	public boolean performFinish() {

		// Define the wizard completion process
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run( IProgressMonitor monitor )
					throws InvocationTargetException, InterruptedException {
				try {
					doFinish( monitor );

				} catch( Exception e ) {
					throw new InvocationTargetException( e );

				} finally {
					monitor.done();
				}
			}
		};

		// Run the operation
		try {
			getContainer().run( true, false, op );

		} catch( InterruptedException e ) {
			// nothing

		} catch( InvocationTargetException e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR, "An error occurred during the bulk export." );
			MessageDialog.openError( getShell(), "Export Error", "An error occurred during the bulk export." );
		}

		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard
	 * #init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init( IWorkbench workbench, IStructuredSelection selection ) {
		this.selection = selection;
	}


	/**
	 * Completes the wizard.
	 * @param monitor
	 * @throws IOException
	 * @throws InvalidJbiXmlException
	 */
	private void doFinish( IProgressMonitor monitor )
			throws IOException, InvalidJbiXmlException {

		// Get different values to monitor proression
		int start = this.page.getStartId();
		int end = this.page.getEndId();
		int length = end - start + 1;

		int tmp = length;
		StringBuilder printfPattern = new StringBuilder();
		while( tmp >= 1 ) {
			tmp /= 10;
			printfPattern.append( "0" );
		}

		NumberFormat formatter = new DecimalFormat( printfPattern.toString());
		monitor.beginTask( "Bulk export in progress...", length + 3 );

		// Duplicate the project
		File tempDir = new File( System.getProperty( "java.io.tmpdir" ), UUID.randomUUID().toString());

		try {
			File srcDir = this.page.getSuProject().getLocation().toFile();
			IoUtils.copyFile( srcDir, tempDir, false );
			monitor.worked( 2 );

			// Parse the jbi.xml
			File jbiXmlFile = new File( tempDir, PetalsConstants.LOC_JBI_FILE );
			Jbi jbi = JbiXmlUtils.getJbiXmlModel( jbiXmlFile );
			if( jbi.getServices() == null)
				throw new NullPointerException( "The base jbi.xml contains no service." );

			String suffix = this.page.getSuffix();
			if( suffix == null )
				suffix = "";
			suffix = "-" + suffix + "-";

			Properties prop = PetalsSPPropertiesManager.getProperties( this.page.getSuProject());
			String componentName = prop.getProperty( PetalsSPPropertiesManager.COMPONENT_DEPLOYMENT_ID, "" );

			// JBI serialization options
			Map<Object,Object> saveOptions = new HashMap<Object,Object>();
			saveOptions.put( XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl( false ));

			// Prepare the SU name
			String sStart = "su-BULK";
			String sMiddle = "";
			String sEnd = "";

			String suName = this.page.getSuProject().getName();
			int index = suName.lastIndexOf( '-' );
			if( index > 0 ) {
				sEnd = suName.substring( index );
				index = suName.lastIndexOf( '-' );
				if( index > 0 ) {
					sStart = suName.substring( 0, index );
					sMiddle = suName.substring( index );
				}
			}

			// Do not use it anymore
			suName = null;

			// Start the loop:
			// 1 - Update all the provides and consumes
			// 2 - Update the provides' WSDLs in consequence
			// 3 - Package it
			for( int i=start; i<=end; i++ ) {

				if( monitor.isCanceled())
					break;

				// Display
				monitor.subTask( "Creating service " + (i-start) + " / " + length );

				// Consumes
				if( jbi.getServices().getConsumes() != null ) {
					for( Consumes consumes : jbi.getServices().getConsumes()) {

						QName srv = consumes.getServiceName();
						if( srv != null  && ! this.page.isKeepSrv()) {
							String localPart = srv.getLocalPart();
							index = localPart.indexOf( suffix + (i-1));
							if( index > 0 )
								localPart = localPart.substring( 0, index );

							localPart += suffix + i;
							srv = new QName( srv.getNamespaceURI(), localPart );
							consumes.setServiceName( srv );
						}

						String edpt = consumes.getEndpointName();
						if( edpt != null ) {
							index = edpt.indexOf( suffix + (i-1));
							if( index > 0 )
								edpt = edpt.substring( 0, index );

							edpt += suffix + i;
							consumes.setEndpointName( edpt );
						}
					}
				}

				// Provides
				if( jbi.getServices().getProvides() != null ) {
					for( Provides provides : jbi.getServices().getProvides()) {

						// Get the WSDL
						String wsdlValue = JbiXmlUtils.getWsdlValue( provides );
						File wsdlFile = null;
						if( wsdlValue != null )
							wsdlFile = JbiXmlUtils.getWsdlFile( tempDir, wsdlValue );

						// No WSDL or off-line WSDL (e.g. on a server) => skip it
						if( wsdlFile == null )
							continue;

						// JBI attributes
						QName srv = provides.getServiceName();
						QName newSrv = null;
						if( srv != null && ! this.page.isKeepSrv()) {
							String localPart = srv.getLocalPart();
							index = localPart.indexOf( suffix + (i-1));
							if( index > 0 )
								localPart = localPart.substring( 0, index );

							localPart += suffix + i;
							newSrv = new QName( srv.getNamespaceURI(), localPart );
							provides.setServiceName( newSrv );
						}

						String edpt = provides.getEndpointName();
						if( edpt != null ) {
							index = edpt.indexOf( suffix + (i-1));
							if( index > 0 )
								edpt = edpt.substring( 0, index );

							edpt += suffix + i;
							provides.setEndpointName( edpt );
						}

						// Update the WSDL...
						if( newSrv != null )
							WsdlUtils.INSTANCE.updateEndpointAndServiceNamesInWsdl( wsdlFile, srv, newSrv, edpt );
						else
							WsdlUtils.INSTANCE.updateEndpointNameInWsdl( wsdlFile, srv, edpt );
					}

					// Write the new jbi.xml file
					URI uri = URI.createFileURI( jbiXmlFile.getAbsolutePath());
					jbi.eResource().setURI( uri );
					jbi.eResource().save( saveOptions );

					// Package it as a SA
					String newSuName = sStart + sMiddle + suffix + formatter.format( Integer.valueOf( i ).doubleValue()) + sEnd;
					String saName = JbiUtils.createSaName( newSuName );

					File saFile = new File( this.page.getOutputDirectory(), saName + ".zip" );
					if( saFile.exists()) {
						if( ! this.page.isOverride())
							continue;
					}

					String targetSaPath = saFile.getAbsolutePath();
					ExportUtils.exportSuBulkProject( newSuName, saName, targetSaPath, componentName, tempDir );
				}

				// Progress
				monitor.worked( 1 );
			}

			// Save memory
			jbi = null;

		} finally {

			// Delete the temporary directory
			IoUtils.deleteFilesRecursively( tempDir );
			monitor.worked( 1 );
		}
	}
}
