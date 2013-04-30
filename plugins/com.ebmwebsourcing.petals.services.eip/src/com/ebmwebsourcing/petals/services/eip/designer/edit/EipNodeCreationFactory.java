/****************************************************************************
 * 
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
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
