/****************************************************************************
 * 
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.edit;

import org.eclipse.gef.requests.CreationFactory;

import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ConnectionCreationFactory implements CreationFactory {

	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.requests.CreationFactory
	 * #getNewObject()
	 */
	public Object getNewObject() {
		return null;
	}

	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.requests.CreationFactory
	 * #getObjectType()
	 */
	public Object getObjectType() {
		return EipConnection.class;
	}
}
