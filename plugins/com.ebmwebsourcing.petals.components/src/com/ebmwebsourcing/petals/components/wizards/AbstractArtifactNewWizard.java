/****************************************************************************
 *
 * Copyright (c) 2009-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.components.wizards;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.components.PetalsComponentsPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractArtifactNewWizard extends Wizard implements INewWizard {

	/**
	 * @return the project to create
	 */
	abstract IProject getProject();


	/**
	 * Performs the real actions.
	 */
	abstract void doFinish( IProgressMonitor monitor ) throws Exception;

	/**
	 * Replaces keys in templates.
	 * @param string the string to update
	 * @return the updated string
	 */
	abstract String replaceKeywords( String string );


	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	@Override
	public boolean performFinish() {

		// Generate the creation script
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
			// Create the project
			getContainer().run( true, false, op );

			// Select the jbi.xml in the explorer
			IFile jbiXmlFile = getProject().getFile( PetalsConstants.LOC_JBI_FILE );
			if( ! jbiXmlFile.exists())
				jbiXmlFile = getProject().getFile( PetalsConstants.NEW_LOC_JBI_FILE );

			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			try {
				IDE.openEditor( activePage, jbiXmlFile, true );
				ResourceUtils.selectResourceInPetalsExplorer( true, jbiXmlFile );

			} catch( PartInitException e ) {
				// nothing
			}

		} catch( InterruptedException e ) {
			return false;

		} catch( InvocationTargetException e ) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError( getShell(), "Error", realException.getMessage());
			return false;
		}

		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard
	 * #init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init( IWorkbench workbench, IStructuredSelection selection ) {
		// nothing
	}


	/**
	 * Copies a template file
	 * @param src
	 * @param targetFile
	 * @param monitor
	 */
	protected void copyTemplate( String src, IFile targetFile, IProgressMonitor monitor ) {

		try {
			// Prepare the target directory
			IContainer parent = targetFile.getParent();
			if( parent instanceof IFolder && ! parent.exists())
				prepareFolder((IFolder) parent, monitor );

			// Load the template
			URL srcUrl = new URL( "platform:/plugin/" + PetalsComponentsPlugin.PLUGIN_ID + "/" + src );
			InputStream is = srcUrl.openStream();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			IoUtils.copyStream( is, os );
			IoUtils.closeQuietly( is );

			// Create the file
			is = new ByteArrayInputStream( replaceKeywords( os.toString()).getBytes());
			targetFile.create( is, true, monitor );

		} catch( IOException e ) {
			PetalsComponentsPlugin.log( e, IStatus.ERROR );

		} catch( CoreException e ) {
			PetalsComponentsPlugin.log( e, IStatus.ERROR );
		}
	}


	/**
	 * Makes sure a folder exists.
	 * @param folder
	 * @param monitor
	 * @throws CoreException
	 */
	public void prepareFolder( IFolder folder, IProgressMonitor monitor ) throws CoreException {

	  IContainer parent = folder.getParent();
	  if( parent instanceof IFolder )
	    prepareFolder((IFolder) parent, monitor );

	  if( ! folder.exists())
	    folder.create( true, true, monitor );
	}
}
