/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.extensions.internal.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.ebmwebsourcing.petals.common.extensions.PetalsCommonWsdlExtPlugin;
import com.ebmwebsourcing.petals.common.extensions.internal.provisional.WsdlExtUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;

/**
 * The WSDL to Java wizard.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class WSDLtoJavaWizard extends Wizard implements INewWizard {

	private WSDLtoJavaWizardPage mainPage;


	/**
	 * Constructor with no argument.
	 */
	public WSDLtoJavaWizard() {
		super();
		setWindowTitle( "WSDL to Java" ); //NON-NLS-1 //$NON-NLS-1$
		setNeedsProgressMonitor( true );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {

		IContainer container = this.mainPage.getOutputContainer();
		final String location = container.getLocation().toString();
		final String wsdlUrl = this.mainPage.getWsdlUri().toString();

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run( IProgressMonitor monitor )
			throws InvocationTargetException, InterruptedException {

				try {
					monitor.beginTask( "Java generation in progress...", 6 );
					monitor.worked( 2 );
					WsdlExtUtils.generateJavaCode( wsdlUrl, location );
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
			container.refreshLocal( IResource.DEPTH_INFINITE, null );
			ResourceUtils.selectResourceInJavaView( true, container );

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
		this.mainPage = new WSDLtoJavaWizardPage( "WSDL to Java", selection );  //$NON-NLS-1$
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
