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
 
package com.ebmwebsourcing.petals.common.internal.provisional.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.refactoring.AbstractRefactoringWizardPage;
import com.ebmwebsourcing.petals.common.internal.provisional.refactoring.MavenProjectRefactoringInfo;
import com.ebmwebsourcing.petals.common.internal.provisional.refactoring.MavenProjectRefactoringProcessor;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.refactoring.MavenProjectRefactoring;
import com.ebmwebsourcing.petals.common.internal.refactoring.MavenProjectRefactoringWizard;

/**
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractProjectRefactorHandler extends AbstractHandler {

	private IProject project;


	/**
	 * @return a refactoring processor
	 */
	public abstract MavenProjectRefactoringProcessor getRefactoringProcessor();


	/**
	 * @return the user input page
	 */
	public abstract AbstractRefactoringWizardPage getUserInputPage();


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		// Prepare the arguments for the wizard
		MavenProjectRefactoringInfo info = new MavenProjectRefactoringInfo();
		info.setProject( this.project );
		info.setNewName( this.project.getName());

		AbstractRefactoringWizardPage userInputPage = getUserInputPage();
		userInputPage.setInfo( info );

		MavenProjectRefactoringProcessor processor = getRefactoringProcessor();
		processor.setInfo( info );

		MavenProjectRefactoring refactoring =
			new MavenProjectRefactoring( processor );

		// Create the refactoring wizard
		MavenProjectRefactoringWizard wizard =
			new MavenProjectRefactoringWizard( refactoring, userInputPage );

		wizard.setNeedsProgressMonitor( true );
		wizard.setForcePreviewReview( true );

		// Launch the wizard
		RefactoringWizardOpenOperation op = new RefactoringWizardOpenOperation( wizard );
		try {
			Shell shell;
			try {
				shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

			} catch( Exception e ) {
				PetalsCommonPlugin.log( e, IStatus.WARNING );
				shell = new Shell();
			}

			op.run( shell, "Refactor..." );

		} catch( final InterruptedException irex ) {
			// nothing
		}

		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #setEnabled(java.lang.Object)
	 */
	@Override
	public void setEnabled( Object evaluationContext ) {

		// Get the selected project
		this.project = null;
		try {
			ISelection s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
			if( s instanceof IStructuredSelection ) {
				Object o = ((IStructuredSelection) s).getFirstElement();
				if( o instanceof IProject
							&& ((IProject) o).getFile( PetalsConstants.LOC_POM_FILE ).exists())
					this.project = (IProject) o;
			}

		} catch( Exception e1 ) {
			// nothing
		}

		super.setBaseEnabled( this.project != null );
	}
}
