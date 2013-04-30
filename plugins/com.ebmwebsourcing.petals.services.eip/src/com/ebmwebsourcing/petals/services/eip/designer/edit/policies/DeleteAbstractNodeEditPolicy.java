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

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.EipConnectionDeleteCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.EipNodeDeleteCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.EndpointDeleteCommand;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class DeleteAbstractNodeEditPolicy extends ComponentEditPolicy {

	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editpolicies.ComponentEditPolicy
	 * #createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	@Override
	protected Command createDeleteCommand( GroupRequest deleteRequest ) {

		// End-points
		if( getHost().getModel() instanceof Endpoint ) {

			CompoundCommand command = new CompoundCommand( "Remove an end-point and its connection" );

			EndpointDeleteCommand cmd1 = new EndpointDeleteCommand();
			cmd1.setEndpoint((Endpoint) getHost().getModel());
			command.add( cmd1 );

			EipConnection conn = ((Endpoint) getHost().getModel()).getIncomingConnection();
			if( conn != null ) {
				EipConnectionDeleteCommand cmd2 = new EipConnectionDeleteCommand();
				cmd2.setConnection( conn );
				command.add( cmd2 );
			}

			return command;
		}


		// EIP nodes
		if( getHost().getModel() instanceof EipNode ) {

			CompoundCommand command = new CompoundCommand( "Remove an EIP node and its connections" );

			EipNodeDeleteCommand cmd1 = new EipNodeDeleteCommand();
			cmd1.setEipNode((EipNode) getHost().getModel());
			command.add( cmd1 );

			for( EipConnection conn : ((EipNode) getHost().getModel()).getOutgoingConnections()) {
				EipConnectionDeleteCommand cmd2 = new EipConnectionDeleteCommand();
				cmd2.setConnection( conn );
				command.add( cmd2 );
			}

			EipConnection conn = ((EipNode) getHost().getModel()).getIncomingConnection();
			if( conn != null ) {
				EipConnectionDeleteCommand cmd3 = new EipConnectionDeleteCommand();
				cmd3.setConnection( conn );
				command.add( cmd3 );
			}

			return command;
		}

		return null;
	}
}
