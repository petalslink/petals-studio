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

package com.ebmwebsourcing.petals.common.internal.provisional.wizards;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlImportUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class WsdlImportWizard extends Wizard implements IImportWizard {

	private UriResourceImportWizardPage page;
	private IStructuredSelection selection;
	private Map<String,File> uriToFile;

	private String initialWsdlUri;
	private IContainer initialContainer;
	private File wsdlFileAfterImport;


	/**
	 * Constructor.
	 */
	public WsdlImportWizard() {
		super();
		setNeedsProgressMonitor( true );
		setWindowTitle( "WSDL Import" );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPages()
	 */
	@Override
	public void addPages() {

		if( this.initialContainer != null )
			this.selection = new StructuredSelection( this.initialContainer );

		this.page = new UriResourceImportWizardPage(
					this.selection, "WSDL",
					new String[] { "WSDL (*.wsdl)" },
					new String[] { "*.wsdl" });

		this.page.setTitle( "WSDL Import" );
		this.page.setDescription( "Import a WSDL and all its dependencies." );
		this.page.setInitialUri( this.initialWsdlUri );
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
					String wsdlUri = WsdlImportWizard.this.page.getUriToImport().toString();
					monitor.beginTask( "Importing " + wsdlUri, IProgressMonitor.UNKNOWN );
					File outputContainer = WsdlImportWizard.this.page.getOutputContainer().getLocation().toFile();
					WsdlImportWizard.this.uriToFile = new WsdlImportUtils().importWsdlOrXsdAndDependencies(
								outputContainer, wsdlUri );

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

			this.wsdlFileAfterImport = this.uriToFile.get( this.page.getUriToImport().toString());
			if( this.wsdlFileAfterImport != null ) {
				IFile f = ResourceUtils.getIFile( this.wsdlFileAfterImport );
				ResourceUtils.selectResourceInPetalsExplorer( true, f != null ? f : outputContainer );
			}

		} catch( InterruptedException e ) {

			// Delete imported resources
			if( this.uriToFile != null ) {
				for( File f : this.uriToFile.values()) {
					if( ! f.delete())
						PetalsCommonPlugin.log( "Could not delete " + f.getAbsolutePath() + ".", IStatus.INFO );
				}
			}

			try {
				outputContainer.refreshLocal( IResource.DEPTH_INFINITE, new NullProgressMonitor ());
			} catch( CoreException ee ) {
				// nothing
			}

		} catch( InvocationTargetException e ) {
			PetalsCommonPlugin.log( "A WSDL file could not be imported ("
						+ this.page.getUriToImport().toString() + ".",
						IStatus.ERROR );
			MessageDialog.openError( getShell(), "Import Error", "The WSDL file could not be imported." );
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
	 * @param initialWsdlUri the initialWsdlUri to set
	 */
	public void setInitialWsdlUri( String initialWsdlUri ) {
		this.initialWsdlUri = initialWsdlUri;
	}


	/**
	 * @param initialContainer the initialContainer to set
	 */
	public void setInitialContainer( IContainer initialContainer ) {
		this.initialContainer = initialContainer;
	}


	/**
	 * @return the wsdlFileAfterImport
	 */
	public File getWsdlFileAfterImport() {
		return this.wsdlFileAfterImport;
	}
}
