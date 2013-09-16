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
 
package com.ebmwebsourcing.petals.services.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.ILinkHelper;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsEditorsLinkHelper implements ILinkHelper {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.ILinkHelper
	 * #activateEditor(org.eclipse.ui.IWorkbenchPage, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void activateEditor( IWorkbenchPage aPage, IStructuredSelection aSelection ) {
		// nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.ILinkHelper
	 * #findSelection(org.eclipse.ui.IEditorInput)
	 */
	@Override
	public IStructuredSelection findSelection( IEditorInput anInput ) {

		IStructuredSelection selection = null;
		if( anInput instanceof IFileEditorInput ) {
			IFile f = ((IFileEditorInput) anInput).getFile();
			if( "jbi.xml".equals( f.getName()))
				selection = new StructuredSelection( f );
		}

		return selection;
	}
}
