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
public class OperationConditionFilter implements IFilter {

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
				result = RouterUtils.isRoutingByOperation( eip );
		}

		return result;
	}
}
