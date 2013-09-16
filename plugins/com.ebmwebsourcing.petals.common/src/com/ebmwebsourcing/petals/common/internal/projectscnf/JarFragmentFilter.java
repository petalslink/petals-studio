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
