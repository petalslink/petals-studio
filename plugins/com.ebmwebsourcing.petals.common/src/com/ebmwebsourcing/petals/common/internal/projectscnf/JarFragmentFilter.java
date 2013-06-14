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

package com.ebmwebsourcing.petals.common.internal.projectscnf;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JarFragmentFilter extends ViewerFilter {

	/**
	 * Constructor.
	 */
	public JarFragmentFilter() {
		// nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter
	 * #select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select( Viewer viewer, Object parentElement, Object element ) {

		IPackageFragmentRoot fragmentRoot = null;
		if( element instanceof IPackageFragmentRoot ) {
			fragmentRoot = (IPackageFragmentRoot) element;
		}
		else if( element instanceof TreePath ) {
			Object o = ((TreePath) element).getFirstSegment();
			if( o instanceof IPackageFragmentRoot )
				fragmentRoot = (IPackageFragmentRoot) o;
		}

		boolean result = true;
		try {
			if( fragmentRoot != null ) {
				result = fragmentRoot.getKind() != IPackageFragmentRoot.K_BINARY;
			}

		} catch( JavaModelException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return result;
	}
}
