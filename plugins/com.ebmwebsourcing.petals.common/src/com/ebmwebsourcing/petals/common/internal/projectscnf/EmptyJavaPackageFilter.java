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
