/******************************************************************************
 * Copyright (c) 2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.swt;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.part.FileEditorInput;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * A hyperlink listener that opens the Petals source editor.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class OpenSourceEditorHyperlinkListener extends PetalsHyperlinkListener {

	private final IFile file;
	private final boolean closeCurrentEditor;


	/**
	 * Constructor.
	 * @param file
	 * @param closeCurrentEditor
	 */
	public OpenSourceEditorHyperlinkListener( IFile file, boolean closeCurrentEditor ) {
		this.file = file;
		this.closeCurrentEditor = closeCurrentEditor;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.swt.PetalsHyperlinkListener
	 * #linkActivated(org.eclipse.ui.forms.events.HyperlinkEvent)
	 */
	@Override
	public void linkActivated( HyperlinkEvent e ) {

		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			if( this.closeCurrentEditor )
				page.closeEditor( page.getActiveEditor(), false );

			page.openEditor(
					new FileEditorInput( this.file ),
					"com.ebmwebsourcing.petals.common.sourceeditor",
					true,
					IWorkbenchPage.MATCH_NONE );

		} catch( Exception e1 ) {
			PetalsCommonPlugin.log( e1, IStatus.ERROR );
			MessageDialog.openError( new Shell(), "Error", "An error occurred while opening the file in the source editor." );
		}
	}
}
