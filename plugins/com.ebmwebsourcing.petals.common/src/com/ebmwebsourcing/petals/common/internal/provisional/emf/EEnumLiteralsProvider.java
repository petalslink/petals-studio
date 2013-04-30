/****************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.emf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Micka�l Istria - Petals Link
 */
public class EEnumLiteralsProvider implements IStructuredContentProvider {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider
	 * #dispose()
	 */
	@Override
	public void dispose() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider
	 * #inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider
	 * #getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {

		EEnum eEnum = (EEnum)inputElement;
		List<Object> res = new ArrayList<Object>();
		for (EEnumLiteral lit : eEnum.getELiterals())
			res.add(lit.getInstance());

		return res.toArray();
	}
}
