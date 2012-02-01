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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsCopyHandler extends AbstractHandler {

	private final List<IResource> resourcesToCopy = new ArrayList<IResource> ();


	/**
	 * Constructor.
	 */
	public PetalsCopyHandler() {
		// nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		if( ! this.resourcesToCopy.isEmpty()) {
			Clipboard clipboard = PetalsCommonPlugin.getDefault().getClipboard( Display.getDefault());
			String[] data = new String[ this.resourcesToCopy.size()];
			for( int i=0; i<this.resourcesToCopy.size(); i++ )
				data[ i ] = this.resourcesToCopy.get( i ).getLocation().toOSString();

			clipboard.setContents( new Object[] { data }, new Transfer[] { FileTransfer.getInstance()});
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

		this.resourcesToCopy.clear();

		// Check the target view
		IWorkbenchPart part =
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();

		if( part == null ||
					! PetalsConstants.PETALS_PROJECT_EXPLORER_VIEW_ID.equals( part.getSite().getId())) {
			super.setBaseEnabled( false );
			return;
		}

		// Check the selection
		ISelection s = null;
		try {
			s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
		} catch( Exception e1 ) {
			// nothing
		}

		if( s == null || s.isEmpty() || !( s instanceof IStructuredSelection )) {
			super.setBaseEnabled( false );
			return;
		}

		// The selection cannot contain any project.
		// Be cooler with resources (e.g. for IJavaElements)
		boolean fine = true;
		for( Iterator<?> it=((IStructuredSelection) s).iterator(); fine && it.hasNext(); ) {
			IResource res = (IResource) PlatformUtils.getAdapter( it.next(), IResource.class );
			if( res == null ) {
				fine = false;
				continue;
			}

			this.resourcesToCopy.add( res );
			if( res instanceof IProject )
				fine = false;
		}

		super.setBaseEnabled( fine );
	}
}
