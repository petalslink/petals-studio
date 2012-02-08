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

import java.io.File;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsPasteHandler extends AbstractHandler {

	private IContainer container;


	/**
	 * Constructor.
	 */
	public PetalsPasteHandler() {
		// Nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		if( this.container != null ) {
			Clipboard clipboard = PetalsCommonPlugin.getDefault().getClipboard( Display.getDefault());
			Object o = clipboard.getContents( FileTransfer.getInstance());
			if( o instanceof String[] ) {

				final File targetFile = this.container.getLocation().toFile();
				for( String path : (String[]) o) {
					final File f = new File( path );
					if( ! f.exists())
						continue;

					// Rename files to copy if there is a conflict
					File target = new File( targetFile, f.getName());
					if( target.exists()) {
						InputDialog dlg = new InputDialog( new Shell(), "Name Conflict",
									"Enter a new name for '" + target.getName() + "':",
									"Copy of " + target.getName(),
									new IInputValidator() {
							@Override
							public String isValid( String newText ) {

								String msg = null;
								File tg = new File( targetFile, newText );
								if( tg.exists())
									msg = "This file already exists.";

								return msg;
							}
						});

						// Cancel? Stop the copy
						if( dlg.open() != Window.OK )
							break;

						target = new File( targetFile, dlg.getValue());
					}

					// Copy the file
					if( f.isDirectory()) {
						try {
							IoUtils.copyFile( f, target, false );
						} catch( IOException e ) {
							PetalsCommonPlugin.log( e, IStatus.ERROR );
						}
					}
					else {
						try {
							IoUtils.copyStream( f, target );
						} catch( IOException e ) {
							PetalsCommonPlugin.log( e, IStatus.ERROR );
						}
					}
				}

				try {
					this.container.refreshLocal( IResource.DEPTH_INFINITE, new NullProgressMonitor());
				} catch( CoreException e ) {
					PetalsCommonPlugin.log( e, IStatus.WARNING );
				}
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

		this.container = null;

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

		// The selection must be a single container.
		// Be cool with resources (e.g. IJavaElements)
		if(((IStructuredSelection) s).size() == 1 ) {
			Object o = ((IStructuredSelection) s).getFirstElement();
			IContainer cont = (IContainer) PlatformUtils.getAdapter( o, IContainer.class );
			if( cont == null ) {
				IResource res = (IResource) PlatformUtils.getAdapter( o, IResource.class );
				if( res instanceof IContainer )
					cont = (IContainer) res;
				else if( res != null )
					cont = res.getParent();
			}

			if( cont != null )
				this.container = cont;
		}

		super.setBaseEnabled( this.container != null );
	}
}
