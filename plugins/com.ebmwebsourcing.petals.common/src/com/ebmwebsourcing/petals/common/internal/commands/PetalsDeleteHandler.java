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
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.DeleteResourceAction;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsDeleteHandler extends AbstractHandler {

	private final DeleteResourceAction deleteAction;
	private final List<IResource> resourcesToDelete = new ArrayList<IResource> ();


	/**
	 * Constructor.
	 */
	public PetalsDeleteHandler() {

		final Shell shell = new Shell( Display.getDefault());
		this.deleteAction = new DeleteResourceAction( new IShellProvider() {
			@Override
			public Shell getShell() {
				return shell;
			}
		});
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		this.deleteAction.selectionChanged( new StructuredSelection( this.resourcesToDelete ));
		this.deleteAction.run();
		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #setEnabled(java.lang.Object)
	 */
	@Override
	public void setEnabled( Object evaluationContext ) {

		this.resourcesToDelete.clear();

		// Check the target view
		IWorkbenchPart part = null;
		try {
			part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
		} catch( Exception e ) {
			// nothing
		}

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

		// The selection must either contain only project, or non-projects
		boolean haveProject = false;
		boolean haveOthers = false;
		for( Iterator<?> it=((IStructuredSelection) s).iterator(); it.hasNext(); ) {
			Object o = it.next();
			IResource res = (IResource) PlatformUtils.getAdapter( o, IResource.class );

			if( res != null ) {
				this.resourcesToDelete.add( res );
				if( res instanceof IProject )
					haveProject = true;
				else
					haveOthers = true;
			}
		}

		super.setBaseEnabled( haveOthers != haveProject );
	}
}
