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

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.ebmwebsourcing.petals.common.croquis.internal.CroquisContributionManager;

/**
 * A minimal content provider to display categories.
 * <p>
 * This content provider allows us to define trigger points
 * to enable other content provider. Trigger points enhance performances
 *  in the project viewer.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MinimalPetalsProjectContentProvider implements ITreeContentProvider {

	/**
	 * Constructor.
	 */
	public MinimalPetalsProjectContentProvider() {

		// Hack for croquis
		// No need to make a complex mechanism just for croquis...
		CroquisContributionManager.INSTANCE.checkInitialization();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider
	 * #getChildren(java.lang.Object)
	 */
	public Object[] getChildren( Object element ) {
		return new Object[ 0 ];
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider
	 * #getParent(java.lang.Object)
	 */
	public Object getParent( Object element ) {
		return null;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.viewers.IContentProvider
	 * #dispose()
	 */
	public void dispose() {
		// nothing
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.viewers.IContentProvider
	 * #inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged( Viewer viewer, Object oldInput, Object newInput ) {
		// nothing
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider
	 * #getElements(java.lang.Object)
	 */
	public Object[] getElements( Object inputElement ) {
		return PetalsProjectManager.INSTANCE.getProjectCategories().toArray();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider
	 * #hasChildren(java.lang.Object)
	 */
	public boolean hasChildren( Object element ) {
		return false;
	}
}
