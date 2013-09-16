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
 
package com.ebmwebsourcing.petals.common.internal.provisional.emf;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This is a generic {@link IContentProvider} that can be used to perform a get on an {@link EObject}.
 * <p>
 * It uses an {@link EStructuralFeature} to introspect the object.
 * </p>
 *
 * @author Micka�l Istria - EBM WebSourcing
 */
public class EMFFeatureContentProvider implements IStructuredContentProvider {

	private final EStructuralFeature feature;
	private EObject eObject;


	/**
	 * Constructor.
	 * @param feature
	 */
	public EMFFeatureContentProvider( EStructuralFeature feature ) {
		this.feature = feature;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider
	 * #dispose()
	 */
	@Override
	public void dispose() {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider
	 * #inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		this.eObject = (EObject) arg2;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider
	 * #getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements( Object arg0 ) {

		Object[] result;
		Object res = this.eObject.eGet( this.feature );
		if( res instanceof Collection )
			result = ((Collection<?>)res).toArray();
		else if( res.getClass().isArray())
			result = (Object[]) res;
		else
			result = new Object[] { res };

		return result;
	}
}
