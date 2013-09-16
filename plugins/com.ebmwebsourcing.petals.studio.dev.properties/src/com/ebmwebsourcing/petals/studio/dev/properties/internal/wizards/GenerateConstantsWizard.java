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
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import com.ebmwebsourcing.petals.studio.dev.properties.AbstractModel;
import com.ebmwebsourcing.petals.studio.dev.properties.internal.ConstantsGenerator;
import com.ebmwebsourcing.petals.studio.dev.properties.internal.PetalsStudioDevPlugin;
import com.ebmwebsourcing.petals.studio.dev.properties.internal.Utils;

/**
 * A wizard to create a new properties model.
 * @author Vincent Zurczak - Linagora
 */
public class GenerateConstantsWizard extends Wizard implements INewWizard {

	private GenerateConstantsWizardPage page;
	private IProject project;
	private AbstractModel model;
	private IWorkbenchWindow workbenchWindow;


	/**
	 * Constructor.
	 */
	public GenerateConstantsWizard() {
		super();
		setWindowTitle( "Generate Java Constants" );
		setNeedsProgressMonitor( true );

		// ImageDescriptor desc = PetalsStudioDevPlugin.getImageDescriptor( "icons/wizban/ant_wiz.png" ); //$NON-NLS-1$
		// setDefaultPageImageDescriptor( desc );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPages()
	 */
	@Override
	public void addPages() {
		this.page = new GenerateConstantsWizardPage( this.project );
		addPage( this.page );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #performFinish()
	 */
	@Override
	public boolean performFinish() {

		// Prepare the creation
		String packageName = this.page.getJavaPackage();
		String className = this.page.getClassName() + "_"
				+ this.model.getModelVersion().replaceAll( "-|\\.", "_" );

		IPath path = this.page.getTarget().getResource().getFullPath();
		path = path.append( packageName.replace( '.', '/' ));
		path = path.append( className + ".java" );
		final IFile createdFile = ResourcesPlugin.getWorkspace().getRoot().getFile( path );
		final String content = new ConstantsGenerator().generateJavaClass( packageName, className, this.model );

		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			@Override
			protected void execute( IProgressMonitor monitor )
			throws CoreException, InvocationTargetException, InterruptedException {

				try {
					monitor.beginTask( "Creating the content...", IProgressMonitor.UNKNOWN );
					monitor.subTask( "Checking the package existence" );
					if( createdFile.exists())
						createdFile.setContents( new ByteArrayInputStream( content.getBytes()), true, true, monitor );
					else
						Utils.forceIFileCreation( createdFile, content, monitor );

				} catch( Exception e ) {
					PetalsStudioDevPlugin.log( e, IStatus.ERROR );
					throw new InvocationTargetException( e );

				} finally {
					monitor.done();
				}
			}
		};

		try {
			getContainer().run( true, false, op );
			BasicNewResourceWizard.selectAndReveal( createdFile, this.workbenchWindow );
			IDE.openEditor( this.workbenchWindow.getActivePage(), createdFile );

		} catch( InterruptedException e ) {
			// nothing

		} catch( Exception exception ) {
			MessageDialog.openError(
					this.workbenchWindow.getShell(),
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
		// nothing
	}


	/**
	 * @param project the project to set
	 */
	public void setProject( IProject project ) {
		this.project = project;
	}


	/**
	 * @param model the model to set
	 */
	public void setModel( AbstractModel model ) {
		this.model = model;
	}


	/**
	 * @param workbenchWindow the workbenchWindow to set
	 */
	public void setWorkbenchWindow( IWorkbenchWindow workbenchWindow ) {
		this.workbenchWindow = workbenchWindow;
	}
}
