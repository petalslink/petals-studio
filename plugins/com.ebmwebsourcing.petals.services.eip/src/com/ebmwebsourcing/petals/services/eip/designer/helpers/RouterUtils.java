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
