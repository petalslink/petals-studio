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
package com.ebmwebsourcing.petals.services.eip.designer.edit.policies;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.EipNodeCreateCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.EndpointCreateCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.NodeChangeLayoutCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.EipChainEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipChain;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class NodeEditLocationPolicy extends XYLayoutEditPolicy {

	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy
	 * #createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	@Override
	protected Command createChangeConstraintCommand( EditPart child, Object constraint ) {

		NodeChangeLayoutCommand command = null;
		if( child.getModel() instanceof AbstractNode
					&& constraint instanceof Rectangle ) {

			command = new NodeChangeLayoutCommand();
			command.setNode((AbstractNode) child.getModel());

			Point location = new Point(((Rectangle) constraint).x, ((Rectangle) constraint).y );
			command.setLocation( location );
		}

		return command;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy
	 * #getCommand(org.eclipse.gef.Request)
	 */
	@Override
	public Command getCommand( Request request ) {

		// RESIZE is disabled
		if( request.getType() == REQ_RESIZE_CHILDREN )
			return null;

		return super.getCommand( request );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy
	 * #getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	@Override
	protected Command getCreateCommand( CreateRequest request ) {

		if( request.getType() == REQ_CREATE
					&& getHost() instanceof EipChainEditPart ) {

			Object o = request.getNewObject();

			// EIP node
			if( o instanceof EipNode ) {
				EipNodeCreateCommand cmd = new EipNodeCreateCommand();
				cmd.setEipChain((EipChain) getHost().getModel());
				cmd.setNewEipNode((EipNode) o);

				Object constraint = getConstraintFor( request );
				if( constraint instanceof Point )
					cmd.setLocation((Point) constraint);
				else if( constraint instanceof Rectangle )
					cmd.setLocation( new Point(((Rectangle) constraint).x, ((Rectangle) constraint).y ));

				return cmd;
			}

			// End-point
			else if( o instanceof Endpoint ) {
				EndpointCreateCommand cmd = new EndpointCreateCommand();
				cmd.setEipChain((EipChain) getHost().getModel());
				cmd.setNewEndpoint((Endpoint) o);

				Object constraint = getConstraintFor( request );
				if( constraint instanceof Point )
					cmd.setLocation((Point) constraint);
				else if( constraint instanceof Rectangle )
					cmd.setLocation( new Point(((Rectangle) constraint).x, ((Rectangle) constraint).y ));

				return cmd;
			}
		}

		return null;
	}
}
