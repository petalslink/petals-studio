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
package com.ebmwebsourcing.petals.components.drivers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.InvalidJbiXmlException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ArchiveUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.components.PetalsComponentsPlugin;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.JbiFactory;
import com.sun.java.xml.ns.jbi.SharedLibraryType1;

/**
 * A wizard to configure a zipped component with one or several shared libraries.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ComponentConfigurationWizard extends Wizard implements INewWizard {

	private ComponentConfigurationWizardPage page;



	/**
	 * Constructor.
	 */
	public ComponentConfigurationWizard() {
		setNeedsProgressMonitor( true );
		setWindowTitle( "Configured Component" );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPages()
	 */
	@Override
	public void addPages() {
		this.page = new ComponentConfigurationWizardPage();
		addPage( this.page );
	}


	/* (non-Jsdoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #performFinish()
	 */
	@Override
	public boolean performFinish() {

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
			getContainer().run( true, false, op );
			MessageDialog.openInformation( getShell(), "Success", "The configuration completed successfully." );

		} catch( InterruptedException e ) {
			return false;

		} catch( InvocationTargetException e ) {
			PetalsComponentsPlugin.log( e, IStatus.ERROR );
			Throwable realException = e.getTargetException();
			MessageDialog.openError( getShell(), "Error", realException.getMessage());
			return false;
		}

		return true;
	}


	/**
	 * @param monitor
	 * @throws IOException
	 * @throws InvalidJbiXmlException
	 */
	private void doFinish( IProgressMonitor monitor ) throws IOException, InvalidJbiXmlException {

		monitor.beginTask( "Configuring the component...", 8 );

		// Copy the file on the disk?
		boolean isTempFile = false;
		File originalFile;
		URI uri = UriUtils.urlToUri( this.page.getComponentUrl());
		try {
			originalFile = new File( uri );

		} catch( Exception e1 ) {

			// Remote file, copy it on the disk
			isTempFile = true;
			monitor.subTask( "Copying the component for configuration..." );
			URL url = uri.toURL();
			InputStream is = url.openStream();
			originalFile = File.createTempFile( "petals-", ".zip" );
			IoUtils.copyStream( is, originalFile );
			monitor.worked( 2 );
		}

		// Update the jbi.xml file to reference the right shared-libraries
		monitor.subTask( "Parsing the JBI descriptor..." );
		Jbi jbi = JbiXmlUtils.getJbiXmlModel( originalFile.toURI());
		if( jbi != null
				&& jbi.getComponent() != null ) {

			Set<String> slAlreadyIn = new HashSet<String> ();
			for( SharedLibraryType1 sl : jbi.getComponent().getSharedLibrary()) {
				FeatureMap featureMap = sl.getMixed();
				for( int j=0; j<featureMap.size(); j++ ) {
					EStructuralFeature esf = featureMap.getEStructuralFeature( j );
					if( FeatureMapUtil.isText( esf )) {
						String slName = featureMap.getValue( j ).toString().trim();
						slAlreadyIn.add( slName );
					}
				}
			}

			for( Map.Entry<String,String> entry : this.page.getSlNameToVersion().entrySet()) {
				if( slAlreadyIn.contains( entry.getKey()))
					continue;

				SharedLibraryType1 sl = JbiFactory.eINSTANCE.createSharedLibraryType1();
				sl.setVersion( entry.getValue());
				FeatureMapUtil.addText( sl.getMixed(), entry.getKey());
				jbi.getComponent().getSharedLibrary().add( sl );
			}

		} else {
			IOException e = new IOException( "The jbi.xml file is not the one of a component project." );
			PetalsComponentsPlugin.log( e, IStatus.ERROR );
			throw e;
		}

		// Save the jbi.xml file
		monitor.subTask( "Updating the archive..." );
		monitor.worked( 2 );
		Map<Object,Object> saveOptions = new HashMap<Object,Object> ();
		saveOptions.put( XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl( false ));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		jbi.eResource().save( os, saveOptions );
		monitor.worked( 2 );

		// Create the final archive
		ArchiveUtils.copyAndUpdatePetalsArchive(
				originalFile,
				new File( this.page.getOutputLocation()),
				new ByteArrayInputStream( os.toString().getBytes()));
		monitor.worked( 2 );

		// Delete the temporary file
		if( isTempFile && ! originalFile.delete())
			originalFile.deleteOnExit();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.IWorkbenchWizard
	 * #init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init( IWorkbench workbench, IStructuredSelection selection ) {
		// nothing
	}
}
