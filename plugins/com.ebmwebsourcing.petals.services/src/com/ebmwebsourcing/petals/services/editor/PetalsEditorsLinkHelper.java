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
