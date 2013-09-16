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
 
package com.ebmwebsourcing.petals.services.eip.designer.tabbedproperties.filters;

import org.eclipse.jface.viewers.IFilter;

import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.EipConnectionEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.helpers.RouterUtils;
import com.ebmwebsourcing.petals.services.eip.designer.model.EIPtype;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;

/**
 * The properties input must be associated with an EIP connection.
 * <p>
 * This connection must has an EIP node for source. And this source
 * must require a XPath condition for this connection.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XPathConditionFilter implements IFilter {

	/* (non-Jsdoc)
	 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
	 */
	public boolean select( Object toTest ) {

		boolean result = false;
		if( toTest instanceof EipConnectionEditPart ) {
			EipConnection conn = (EipConnection) ((EipConnectionEditPart) toTest).getModel();
			EipNode eip = conn.getSource();
			if( eip != null
						&& eip.getEipType() == EIPtype.ROUTER
						&& conn.shouldHaveCondition())
				result = RouterUtils.isRoutingByContent( eip );
		}

		return result;
	}
}
