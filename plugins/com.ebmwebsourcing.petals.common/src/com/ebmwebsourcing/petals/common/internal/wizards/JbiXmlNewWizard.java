/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.internal.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ISetSelectionTarget;

import com.ebmwebsourcing.petals.common.internal.Messages;
import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * A wizard to create the skeleton of a 'jbi.xml' file.
 * @author Vincent Zurczak - EBM WebSourcing
 * @deprecated is this wizard really used? Not IMO.
 * If no complaint is received in between times, delete this class from the plug-in (for v1.4.0)
 */
@Deprecated
public class JbiXmlNewWizard extends Wizard implements INewWizard {

	/** The unique page of the wizard. */
	private JbiXmlNewWizardPage page;

	/** The initial selection. */
	private IStructuredSelection selection;

	/** The workbench. */
	private IWorkbench workbench;


	/**
	 * Constructor.
	 */
	public JbiXmlNewWizard() {
		super();
		setWindowTitle( "New JBI Descriptor" );
		setNeedsProgressMonitor( true );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		this.page = new JbiXmlNewWizardPage( "MainPage", this.selection ); //$NON-NLS-1$
		addPage( this.page );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		try {
			// Create the file.
			final IFile createdFile = this.page.getSelectedContainer().getFile( new Path( "jbi.xml" )); //$NON-NLS-1$
			WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
				@Override
				protected void execute( IProgressMonitor monitor )
				throws CoreException, InvocationTargetException, InterruptedException {

					try {
						monitor.beginTask( "Creating the JBI descriptor...", IProgressMonitor.UNKNOWN );
						if( createdFile.exists())
							createdFile.setContents(
										JbiXmlNewWizard.this.page.getInitialContents(), true, true, monitor );
						else
							createdFile.create(
										JbiXmlNewWizard.this.page.getInitialContents(), true, monitor );

					} catch( Exception e ) {
						throw new InvocationTargetException( e );

					} finally {
						monitor.done();
					}
				}
			};

			try {
				getContainer().run( true, false, op );
			} catch( Exception e ) {
				return false;
			}

			// Select the file in the current view.
			IWorkbenchWindow workbenchWindow = this.workbench.getActiveWorkbenchWindow();
			IWorkbenchPage page = workbenchWindow.getActivePage();
			final IWorkbenchPart activePart = page.getActivePart();

			if( activePart instanceof ISetSelectionTarget ) {
				final ISelection targetSelection = new StructuredSelection( createdFile );
				getShell().getDisplay().asyncExec( new Runnable() {
					@Override
					public void run() {
						((ISetSelectionTarget) activePart).selectReveal( targetSelection );
					}
				});
			}

			// Open the file in the default editor.
			try {
				IDE.openEditor( page, createdFile );

			} catch( PartInitException exception ) {
				MessageDialog.openError(
							workbenchWindow.getShell(),
							Messages.NewJbiXmlWizard_0,
							exception.getMessage());
				return false;
			}

			return true;

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return false;
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

		ImageDescriptor desc = PetalsCommonPlugin.getImageDescriptor( "icons/wizban/wiz_jbi_descriptor.png" ); //$NON-NLS-1$
		setDefaultPageImageDescriptor( desc );
	}
}
