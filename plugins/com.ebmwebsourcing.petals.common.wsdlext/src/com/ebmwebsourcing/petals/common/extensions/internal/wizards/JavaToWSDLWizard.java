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
 
package com.ebmwebsourcing.petals.common.extensions.internal.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import com.ebmwebsourcing.petals.common.extensions.PetalsCommonWsdlExtPlugin;
import com.ebmwebsourcing.petals.common.extensions.internal.provisional.WsdlExtUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;

/**
 * The Java to WSDL wizard.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JavaToWSDLWizard extends Wizard implements INewWizard {

	private JavaToWSDLWizardPage mainPage;
	private WizardNewFileCreationPage wsdlPage;


	/**
	 * Constructor with no argument.
	 */
	public JavaToWSDLWizard() {
		super();
		setWindowTitle( "Java to WSDL" ); //NON-NLS-1
		setNeedsProgressMonitor( true );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {

		// Get the fields here (invalid thread access otherwise)
		final String wsdlName = this.wsdlPage.getFileName();
		final IResource folder = ResourcesPlugin.getWorkspace().getRoot().findMember( this.wsdlPage.getContainerFullPath());

		final String className = this.mainPage.getClassName();
		final IJavaProject jp = this.mainPage.getJavaProject();

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run( IProgressMonitor monitor )
			throws InvocationTargetException, InterruptedException {

				try {
					monitor.beginTask( "WSDL generation in progress...", 6 );
					monitor.worked( 2 );
					WsdlExtUtils.generateWsdlFile( wsdlName, className, folder.getLocation().toOSString(), jp );
					monitor.worked( 3 );

				} catch( Exception e ) {
					throw new InvocationTargetException( e );

				} finally {
					monitor.done();
				}
			}
		};

		try {
			getContainer().run( true, false, op );

			IResource wsdlFile = ((IContainer) folder).getFile( new Path( this.wsdlPage.getFileName()));
			wsdlFile.getProject().refreshLocal( IResource.DEPTH_INFINITE, null );
			ResourceUtils.selectResourceInJavaView( true, wsdlFile );

		} catch( Exception e ) {
			PetalsCommonWsdlExtPlugin.log( e, IStatus.ERROR );
		}

		return true;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard
	 * #init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init( IWorkbench workbench, IStructuredSelection selection ) {
		this.mainPage = new JavaToWSDLWizardPage( "Java page", selection );  //$NON-NLS-1$
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	@Override
	public void addPages() {

		this.wsdlPage = new WizardNewFileCreationPage( "Target WSDL", new StructuredSelection());
		this.wsdlPage.setAllowExistingResources( true );
		this.wsdlPage.setDescription( "Indicate where to generate the WSDL." );
		this.wsdlPage.setFileExtension( "wsdl" ); //$NON-NLS-1$
		this.wsdlPage.setTitle( "Target WSDL" );

		super.addPages();
		addPage( this.mainPage );
		addPage( this.wsdlPage );
	}
}
