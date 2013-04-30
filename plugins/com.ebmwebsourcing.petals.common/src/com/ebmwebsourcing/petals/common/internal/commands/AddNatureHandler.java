/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.commands;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils;

/**
 * The default handler for the command "Add Petals Nature".
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class AddNatureHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		ISelection s = null;
		try {
			s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
		} catch( Exception e1 ) {
			// nothing
		}

		if( s instanceof IStructuredSelection ) {
			for( Iterator<?> it=((IStructuredSelection) s).iterator(); it.hasNext(); ) {
				Object o = it.next();
				if( !( o instanceof IProject ))
					continue;

				IProject p = (IProject) o;
				String natureId = event.getParameter( "natureId" );
				try {
					if( natureId != null
								&& p.isAccessible()
								&& ! p.hasNature( natureId )) {

						// Add the nature in the project description
						IProjectDescription description = p.getDescription();
						String[] natures = description.getNatureIds();
						String[] newNatures = new String[ natures.length + 1 ];

						System.arraycopy( natures, 0, newNatures, 0, natures.length );
						newNatures[ natures.length ] = natureId;
						description.setNatureIds( newNatures );
						p.setDescription( description, null );

						// Hack for the Java nature
						// Define the SRC folder
						if( JavaCore.NATURE_ID.equals( natureId ))
							JavaUtils.createJavaProject( p );
						// End of the hack
					}

				} catch( CoreException e ) {
					MessageDialog.openError(
								new Shell(), "Nature Error",
								"The nature " + natureId + " could not be added to the project " + p.getName() + "." );
				}
			}
		}

		return null;
	}
}
