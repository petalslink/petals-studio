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

package com.ebmwebsourcing.petals.common.extensions.internal.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.common.extensions.PetalsCommonWsdlExtPlugin;
import com.ebmwebsourcing.petals.common.extensions.internal.provisional.WsdlExtUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;

/**
 * The WSDL to Java wizard.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class WebServiceClientWizard extends Wizard implements INewWizard {

	private final static String CLIENT_PATTERN = ".*_Client\\.java";;

	private WSDLtoJavaWizardPage mainPage;
	private List<IFile> resourcesToSelect;


	/**
	 * Constructor with no argument.
	 */
	public WebServiceClientWizard() {
		super();
		setWindowTitle( "Web Service Client" ); //NON-NLS-1 //$NON-NLS-1$
		setNeedsProgressMonitor( true );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {

		final IContainer container = this.mainPage.getOutputContainer();
		final String wsdlUrl = this.mainPage.getWsdlUri().toString();

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run( IProgressMonitor monitor )
			throws InvocationTargetException, InterruptedException {

				try {
					monitor.beginTask( "Java generation in progress...", 10 );

					// Count the "clients" that are already in the container
					container.refreshLocal( IResource.DEPTH_INFINITE, monitor );
					List<IFile> clientsBefore = ResourceUtils.getFilesByRegexp( container, CLIENT_PATTERN );
					monitor.worked( 2 );

					// Generate the Java client
					monitor.worked( 2 );
					WsdlExtUtils.generateWebServiceClient( wsdlUrl, container.getLocation().toString());
					monitor.worked( 3 );

					// Refresh the project (and build it if required)
					container.refreshLocal( IResource.DEPTH_INFINITE, monitor );
					if( container.getProject().hasNature( JavaCore.NATURE_ID ))
						container.getProject().build( IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor );

					// Find the client class
					WebServiceClientWizard.this.resourcesToSelect = ResourceUtils.getFilesByRegexp( container, CLIENT_PATTERN );
					WebServiceClientWizard.this.resourcesToSelect.removeAll( clientsBefore );
					monitor.worked( 2 );

				} catch( Exception e ) {
					throw new InvocationTargetException( e );

				} finally {
					monitor.done();
				}
			}
		};

		try {
			// Generate the client
			getContainer().run( true, false, op );

			// Open the client file
			Display.getDefault().asyncExec( new Runnable() {
				public void run() {

					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					try {
						for( IFile f : WebServiceClientWizard.this.resourcesToSelect ) {
							IDE.openEditor( page, f, true );
						}

					} catch( PartInitException e ) {
						PetalsCommonWsdlExtPlugin.log( e, IStatus.WARNING );
					}
				}
			});

			// Select it in the explorer
			ResourceUtils.selectResourceInJavaView( true, this.resourcesToSelect );

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
		this.mainPage = new WSDLtoJavaWizardPage( "Web Service Client", selection );  //$NON-NLS-1$
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	@Override
	public void addPages() {
		super.addPages();
		addPage( this.mainPage );
	}
}
