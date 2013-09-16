/******************************************************************************
 * Copyright (c) 2012, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.studio.dev.properties.internal.wizards;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import com.ebmwebsourcing.petals.studio.dev.properties.internal.Utils;

/**
 * A wizard to create a new properties model.
 * @author Vincent Zurczak - Linagora
 */
public class NewPropertiesModelWizard extends Wizard implements INewWizard {

	/** The unique page of the wizard. */
	private WizardNewFileCreationPage page;

	/** The initial selection. */
	private IStructuredSelection selection;

	/** The workbench. */
	private IWorkbench workbench;


	/**
	 * Constructor.
	 */
	public NewPropertiesModelWizard() {
		super();
		setWindowTitle( "New Properties Model" );
		setNeedsProgressMonitor( true );

		// ImageDescriptor desc = PetalsStudioDevPlugin.getImageDescriptor( "icons/wizban/ant_wiz.png" ); //$NON-NLS-1$
		// setDefaultPageImageDescriptor( desc );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		this.page = new WizardNewFileCreationPage( "MainPage", this.selection ); //$NON-NLS-1$
		this.page.setFileName( "model.properties" );
		this.page.setFileExtension( "properties" );
		this.page.setTitle( "Properties Model" );
		this.page.setDescription( "Define the location of the properties model to create." );
		addPage( this.page );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {

		// Prepare the creation
		String content;
		InputStream in = getClass().getResourceAsStream( "model.properties" );
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			Utils.copyStream( in, os );
			content = os.toString( "UTF-8" );

		} catch( IOException e1 ) {
			content = "";

		} finally {
			try {
				in.close();
			} catch( IOException e ) {
				// nothing
			}
		}

		// Create the file
		final StringBuilder sb = new StringBuilder( content );
		final IFile createdFile = this.page.createNewFile();
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			@Override
			protected void execute( IProgressMonitor monitor )
			throws CoreException, InvocationTargetException, InterruptedException {

				try {
					monitor.beginTask( "Creating the content...", IProgressMonitor.UNKNOWN );
					InputStream in = new ByteArrayInputStream( sb.toString().getBytes());
					if( createdFile.exists())
						createdFile.setContents( in, true, true, monitor );
					else
						createdFile.create( in, true, monitor );

				} catch( Exception e ) {
					throw new InvocationTargetException( e );

				} finally {
					monitor.done();
				}
			}
		};

		try {
			getContainer().run( true, false, op );
			IWorkbenchWindow window = this.workbench.getActiveWorkbenchWindow();
			BasicNewResourceWizard.selectAndReveal( createdFile, window );
			IDE.openEditor( window.getActivePage(), createdFile );

		} catch( InterruptedException e ) {
			// nothing

		} catch( Exception exception ) {
			MessageDialog.openError(
					this.workbench.getActiveWorkbenchWindow().getShell(),
					"Error",
					"An error occurred during the creation. Check the logs for more details." );
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
		this.selection = selection;
		this.workbench = workbench;
	}
}
