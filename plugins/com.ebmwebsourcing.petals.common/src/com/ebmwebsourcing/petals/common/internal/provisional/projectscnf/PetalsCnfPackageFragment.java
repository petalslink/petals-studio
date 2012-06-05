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

package com.ebmwebsourcing.petals.common.internal.provisional.projectscnf;

import org.eclipse.jdt.core.IPackageFragment;

/**
 * A wrapper for a package fragment, so that display is easier in the Petals CNF for Java elements.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsCnfPackageFragment {

	private final IPackageFragment fragment;
	private final Object parent;


	/**
	 * Constructor.
	 * @param fragment (can be null)
	 * @param parent (not null)
	 */
	public PetalsCnfPackageFragment( IPackageFragment fragment, Object parent ) {
		this.fragment = fragment;
		this.parent = parent;
	}


	/**
	 * @return the fragment
	 */
	public IPackageFragment getFragment() {
		return this.fragment;
	}


	/**
	 * @return the parent
	 */
	public Object getParent() {
		return this.parent;
	}
}
