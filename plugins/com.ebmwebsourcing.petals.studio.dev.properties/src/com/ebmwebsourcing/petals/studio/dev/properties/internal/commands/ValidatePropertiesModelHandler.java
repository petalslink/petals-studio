/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.studio.dev.properties.internal.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.studio.dev.properties.internal.AbstractModelValidator;

/**
 * The default handler for the command to validate a properties model.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ValidatePropertiesModelHandler extends AbstractHandler {

	protected IFile propertiesFile;
	protected boolean initialized = false;


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		if( ! this.initialized )
			setEnabled( null );

		AbstractModelValidator.validateAndMark( this.propertiesFile );
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
		this.initialized = true;
		ISelection s = null;
		try {
			s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
		} catch( Exception e1 ) {
			// nothing
		}

		boolean fine = false;
		if( s != null
				&& ! s.isEmpty()
				&& s instanceof IStructuredSelection
				&& ((IStructuredSelection) s).size() == 1 ) {

			Object o = ((IStructuredSelection) s).getFirstElement();
			if( o instanceof IFile
						&& ((IFile) o).getName().toLowerCase().endsWith( ".properties" )) {
				fine = true;
				this.propertiesFile = (IFile) o;
			}
		}

		super.setBaseEnabled( fine );
	}
}
