/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.ebmwebsourcing.petals.services.eip.designer.model.EipChain;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;

/**
 * The edit part factory for the EIP designer.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipEditPartFactory implements EditPartFactory {

	/* (non-Jsdoc)
	 * @see org.eclipse.gef.EditPartFactory
	 * #createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart( EditPart context, Object model ) {

		EditPart editPart = null;
		if( model instanceof EipChain )
			editPart = new EipChainEditPart();

		else if( model instanceof EipNode )
			editPart = new EipNodeEditPart();

		else if( model instanceof Endpoint )
			editPart = new EndpointEditPart();

		else if( model instanceof EipConnection )
			editPart = new EipConnectionEditPart();

		else if( model instanceof String )
			editPart = new MessageEditPart();

		if( editPart != null )
			editPart.setModel( model );

		return editPart;
	}
}
