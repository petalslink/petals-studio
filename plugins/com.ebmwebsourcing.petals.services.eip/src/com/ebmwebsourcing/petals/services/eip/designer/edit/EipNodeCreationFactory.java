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
 
package com.ebmwebsourcing.petals.services.eip.designer.edit;

import org.eclipse.gef.requests.CreationFactory;

import com.ebmwebsourcing.petals.services.eip.designer.model.EIPtype;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipNodeCreationFactory implements CreationFactory {

	private final EIPtype eipType;


	/**
	 * Constructor.
	 * @param eipType
	 */
	public EipNodeCreationFactory( EIPtype eipType ) {
		this.eipType = eipType;
	}


	/* (non-Jsdoc)
	 * @see org.eclipse.gef.requests.CreationFactory
	 * #getNewObject()
	 */
	public Object getNewObject() {
		return new EipNode( -1, this.eipType );
	}


	/* (non-Jsdoc)
	 * @see org.eclipse.gef.requests.CreationFactory
	 * #getObjectType()
	 */
	public Object getObjectType() {
		return this.eipType;
	}
}
