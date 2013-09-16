/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.explorer.dnd;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.navigator.CommonDragAdapterAssistant;

import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;

/**
 * Drag end-points from the service explorer to other Eclipse views and editors.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ServiceExplorerDragAdapterAssistant
extends CommonDragAdapterAssistant {


	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.CommonDragAdapterAssistant
	 * #getSupportedTransferTypes()
	 */
	@Override
	public Transfer[] getSupportedTransferTypes() {
		return new Transfer[] { TextTransfer.getInstance()};
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.CommonDragAdapterAssistant
	 * #setDragData(
	 * 		org.eclipse.swt.dnd.DragSourceEvent,
	 * 		org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public boolean setDragData( DragSourceEvent anEvent, IStructuredSelection aSelection ) {

		boolean activated = false;
		if( aSelection.getFirstElement() instanceof EndpointBean ) {

			EndpointBean edpt = (EndpointBean) aSelection.getFirstElement();
			StringBuffer sb = new StringBuffer();
			sb.append( "petals-" );
			sb.append( "|srvNs=" );
			sb.append( edpt.getServiceName().getNamespaceURI());
			sb.append( "|srvName=" );
			sb.append( edpt.getServiceName().getLocalPart());
			sb.append( "|itfNs=" );
			sb.append( edpt.getInterfaceName().getNamespaceURI());
			sb.append( "|itfName=" );
			sb.append( edpt.getInterfaceName().getLocalPart());
			sb.append( "|edptName=" );
			sb.append( edpt.getEndpointName());
			sb.append( "|wsdlUri=" );
			if( edpt.getWsdlUri() != null )
				sb.append( edpt.getWsdlUri());

			anEvent.data = sb.toString();
			activated = true;
		}

		return activated;
	}
}
