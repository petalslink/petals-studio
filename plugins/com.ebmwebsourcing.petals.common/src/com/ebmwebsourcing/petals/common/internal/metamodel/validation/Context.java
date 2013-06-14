/****************************************************************************
 * 
 * Copyright (c) 2008-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.metamodel.validation;

import org.eclipse.emf.validation.model.IClientSelector;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Context implements IClientSelector {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.validation.model.IClientSelector
	 * #selects(java.lang.Object)
	 */
	public boolean selects(Object object) {
		return true;
	}
}
