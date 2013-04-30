/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.projectscnf;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

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
		return true;
	}
}
