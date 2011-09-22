/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
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
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RenameResourceAction;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsRenameHandler extends AbstractHandler {

	private final RenameResourceAction renameAction;
	private IResource resourceToRename;


	/**
	 * Constructor.
	 */
	public PetalsRenameHandler() {

		final Shell shell = new Shell( Display.getDefault());
		this.renameAction = new RenameResourceAction( new IShellProvider() {
			public Shell getShell() {
				return shell;
			}
		});
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		this.renameAction.selectionChanged( new StructuredSelection( this.resourceToRename ));
		this.renameAction.run();
		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #setEnabled(java.lang.Object)
	 */
	@Override
	public void setEnabled( Object evaluationContext ) {

		this.resourceToRename = null;

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
		// The selection must only contain one resource.
		if(((IStructuredSelection) s).size() == 1 ) {
			Object o = ((IStructuredSelection) s).getFirstElement();
			if( o instanceof IResource )
				this.resourceToRename = (IResource) o;
		}

		super.setBaseEnabled( this.resourceToRename != null );
	}
}
