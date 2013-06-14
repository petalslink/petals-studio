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
package com.ebmwebsourcing.petals.services.eip.designer.helpers;

import com.ebmwebsourcing.petals.services.eip.designer.model.EIPtype;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipProperty;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RouterUtils {

	/**
	 * @param eip an EIP node whose type must be {@link EIPtype#ROUTER}
	 * @return true if this router routes by operation
	 */
	public static boolean isRoutingByOperation( EipNode eip ) {
		String routingCriteria = eip.getProperties().get( EipProperty.ROUTING_CRITERIA );
		return EipProperty.ROUTING_CRITERIA_BY_OPERATION.equals( routingCriteria );
	}


	/**
	 * @param eip an EIP node whose type must be {@link EIPtype#ROUTER}
	 * @return true if this router routes by content (XPath conditions)
	 */
	public static boolean isRoutingByContent( EipNode eip ) {
		String routingCriteria = eip.getProperties().get( EipProperty.ROUTING_CRITERIA );
		return routingCriteria == null || EipProperty.ROUTING_CRITERIA_BY_CONTENT.equals( routingCriteria );
	}
}
