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

package com.ebmwebsourcing.petals.common.internal.projectscnf;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EmptyJavaPackageFilter extends ViewerFilter {

	/**
	 * Constructor.
	 */
	public EmptyJavaPackageFilter() {
		// nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter
	 * #select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select( Viewer viewer, Object parentElement, Object element ) {

		IPackageFragment fragment = null;
		if( element instanceof IPackageFragment ) {
			fragment = (IPackageFragment) element;
		}
		else if( element instanceof TreePath ) {
			Object o = ((TreePath) element).getFirstSegment();
			if( o instanceof IPackageFragment )
				fragment = (IPackageFragment) o;
		}

		boolean result = true;
		try {
			if( fragment != null )
				result = fragment.getNonJavaResources().length > 0 || fragment.getChildren().length > 0;

		} catch( JavaModelException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return result;
	}
}
