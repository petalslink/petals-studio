/****************************************************************************
 * 
 * Copyright (c) 2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
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
