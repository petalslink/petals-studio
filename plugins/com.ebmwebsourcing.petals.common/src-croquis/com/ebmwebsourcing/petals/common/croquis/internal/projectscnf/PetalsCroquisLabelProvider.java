/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.croquis.internal.projectscnf;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import com.ebmwebsourcing.petals.common.croquis.internal.CroquisContributionManager;
import com.ebmwebsourcing.petals.common.croquis.internal.provisional.ICroquisExtension;
import com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectLabelProvider;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsCroquisLabelProvider extends PetalsProjectLabelProvider {

	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.projectscnf.PetalsProjectLabelProvider
	 * #getText(java.lang.Object)
	 */
	@Override
	public String getText( Object element ) {

		String result;
		ICroquisExtension ext = getCroquisExtension( element );
		if( ext != null )
			result = ext.getLabel();
		else
			result = super.getText( element );

		return result;
	}


	/**
	 * Gets the extension class for a croquis category.
	 * @param element an element
	 * @return an extension if the elements matches a croquis category, null otherwise
	 */
	public static ICroquisExtension getCroquisExtension( Object element ) {

		IFolder folder = null;
		if( element instanceof IFolder
					&& ((IFolder) element).getParent() instanceof IProject &&
					ICroquisExtension.CROQUIS_PROJECT_NAME.equals(((IFolder) element).getParent().getName()))
			folder = (IFolder) element;

		ICroquisExtension result = null;
		if( folder != null ) {
			for( ICroquisExtension croquis : CroquisContributionManager.INSTANCE.getCroquisData()) {
				if( folder.getName().equals( croquis.getSubDirectoryName())) {
					result = croquis;
					break;
				}
			}
		}

		return result;
	}
}
