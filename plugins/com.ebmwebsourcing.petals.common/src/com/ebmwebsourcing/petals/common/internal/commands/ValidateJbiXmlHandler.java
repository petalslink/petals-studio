/****************************************************************************
 * 
 * Copyright (c) 2010-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * The default handler for the command "Validate jbi.xml".
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ValidateJbiXmlHandler extends AbstractHandler {

	/**
	 * The jbi.xml to validate.
	 */
	private IFile jbiXmlFile;


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		if( this.jbiXmlFile != null ) {
			try {
				IProgressMonitor monitor = new NullProgressMonitor();
				this.jbiXmlFile.refreshLocal( IResource.DEPTH_ZERO, monitor );
				this.jbiXmlFile.touch( monitor );

			} catch( CoreException e ) {
				PetalsCommonPlugin.log( e, IStatus.ERROR );
			}
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

		// Check the selection
		ISelection s = null;
		try {
			s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
		} catch( Exception e1 ) {
			// nothing
		}

		boolean fine;
		if( s == null || s.isEmpty() || !( s instanceof IStructuredSelection )) {
			fine = false;

		} else if(((IStructuredSelection) s).size() != 1 ) {
			fine = false;

		} else {
			// Get the selection's content
			Object o = ((IStructuredSelection) s).getFirstElement();
			if( o instanceof IFile
						&& "jbi.xml".equals(((IFile) o).getName())) {
				fine = true;
				this.jbiXmlFile = (IFile) o;
			}
			else {
				fine = false;
				this.jbiXmlFile = null;
			}
		}

		super.setBaseEnabled( fine );
	}
}
