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
package com.ebmwebsourcing.petals.services.bpel.wizards;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.FileImporter;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlImportUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.wizards.UriResourceImportWizardPage;
import com.ebmwebsourcing.petals.services.bpel.PetalsBpelPlugin;
import com.ebmwebsourcing.petals.services.bpel.designer.provisional.PetalsBpelModules;

/**
 * A wizard to import a BPEL file.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BpelImportWizard extends Wizard implements IImportWizard {

	private UriResourceImportWizardPage page;
	private IStructuredSelection selection;
	private File fileAfterImport;


	/**
	 * Constructor.
	 */
	public BpelImportWizard() {
		super();
		setNeedsProgressMonitor( true );
		setWindowTitle( "BPEL Import" );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPages()
	 */
	@Override
	public void addPages() {

		this.page = new UriResourceImportWizardPage(
					this.selection, "BPEL",
					new String[] { "BPEL (*.bpel)" },
					new String[] { "*.bpel" });

		this.page.setTitle( "BPEL Import" );
		this.page.setDescription( "Import a BPEL and all its dependencies." );
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
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			@Override
			protected void execute( IProgressMonitor monitor )
			throws CoreException, InvocationTargetException, InterruptedException {
				try {
					final URL url = BpelImportWizard.this.page.getUriToImport().toURL();

					// Get the imports
					monitor.beginTask( "Resolving imports...", IProgressMonitor.UNKNOWN );
					Set<String> uris = new HashSet<String> ();
					for( URI uri : PetalsBpelModules.getWsdlImportUris( url, true, true ).values())
						uris.add( uri.toString());

					for( URI uri : PetalsBpelModules.getWsdlImportUris( url, false, false ).values())
						uris.add( uri.toString());

					// Import the WSDL files
					File outputContainer = BpelImportWizard.this.page.getOutputContainer().getLocation().toFile();
					WsdlImportUtils importUtils = new WsdlImportUtils();
					for( String uri : uris ) {
						monitor.beginTask( "Importing " + uri + "...", IProgressMonitor.UNKNOWN );
						importUtils.importWsdlOrXsdAndDependencies( outputContainer, uri );
					}

					// Eventually, import the BPEL file
					monitor.beginTask( "Importing the BPEL file...", IProgressMonitor.UNKNOWN );
					FileImporter importer = new FileImporter( outputContainer );
					BpelImportWizard.this.fileAfterImport = importer.importFile( url.toString(), null, outputContainer, false, true, false );

				} catch( Exception e ) {
					throw new InvocationTargetException( e );

				} finally {
					monitor.done();
				}
			}
		};

		// Run the operation
		IContainer outputContainer = this.page.getOutputContainer();
		try {
			getContainer().run( true, false, op );
			try {
				outputContainer.refreshLocal( IResource.DEPTH_INFINITE, new NullProgressMonitor ());
			} catch( CoreException e ) {
				// nothing
			}

			if( this.fileAfterImport != null ) {
				IFile f = ResourceUtils.getIFile( this.fileAfterImport );
				ResourceUtils.selectResourceInPetalsExplorer( true, f != null ? f : outputContainer );
			}

		} catch( InterruptedException e ) {
			try {
				outputContainer.refreshLocal( IResource.DEPTH_INFINITE, new NullProgressMonitor ());

			} catch( CoreException ee ) {
				// nothing
			}

		} catch( InvocationTargetException e ) {
			PetalsBpelPlugin.log( "A BPEL file could not be imported ("
						+ this.page.getUriToImport().toString() + ".",
						IStatus.ERROR );
			MessageDialog.openError( getShell(), "Import Error", "The BPEL file could not be imported." );
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
}
