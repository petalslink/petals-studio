/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.details.providers;

import java.util.List;

import org.eclipse.bpel.model.Invoke;
import org.eclipse.bpel.model.Receive;
import org.eclipse.bpel.model.Reply;
import org.eclipse.wst.wsdl.PortType;

/**
 * Content provider for Operations.
 * 
 * Expects a PortType as input.
 */
public class OperationContentProvider extends AbstractContentProvider  {

	@Override
	public void collectElements(Object input, List list)  {
		
		if (input instanceof PortType) {
			PortType pt = (PortType) input;
			list.addAll ( pt.getOperations() );
			
			return ;
		}
		
		
		// Return all the operations for a receive (myRole mapping to port types)
		if (input instanceof Receive) {
			Receive receive = (Receive) input;
			collectElements( receive.getPortType(), list );			
			return;
		}
		
		
		if (input instanceof Reply) {
			Reply reply = (Reply) input;
			collectElements ( reply.getPortType(), list);
			return ;
		}
		
		if (input instanceof Invoke) {
			Invoke invoke = (Invoke) input;
			collectElements ( invoke.getPortType(), list );
			return ;
		}
	}
}
