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

package com.ebmwebsourcing.petals.jbi.editor.form.common.emf;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This is a generic {@link IContentProvider} that can be used to perfer a
 * get on an {@link EObject}. It uses an {@link EStructuralFeature} to introspect
 * the object.
 * @author istria
 *
 */
public class EMFFeatureContentProvider implements IStructuredContentProvider {

	private EStructuralFeature feature;
	private EObject eObject;
	
	public EMFFeatureContentProvider(EStructuralFeature feature) {
		this.feature = feature;
	}
	
	public void dispose() {
	}

	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		this.eObject = (EObject)arg2;
	}

	public Object[] getElements(Object arg0) {
		Object res = eObject.eGet(feature);
		if (res instanceof Collection) {
			return ((Collection<?>)res).toArray();
		} else if (res.getClass().isArray()) {
			return (Object[])res; 
		} else {
			Object[] toArray = new Object[1];
			toArray[0] = res;
			return toArray;
		}
	}

}
