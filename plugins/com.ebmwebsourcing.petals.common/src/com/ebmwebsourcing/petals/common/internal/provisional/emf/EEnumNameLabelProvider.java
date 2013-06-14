/****************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.emf;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * @author Micka�l Istria - Petals Link
 */
public class EEnumNameLabelProvider extends LabelProvider implements IBaseLabelProvider {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider
	 * #getText(java.lang.Object)
	 */
	@Override
	public String getText(Object e) {

		if (e instanceof Enumerator)
			return ((Enumerator)e).getLiteral();
		else
			return super.getText(e);
	}
}
