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
package com.ebmwebsourcing.petals.services.eip.designer.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.EipConnectionDeleteCommand;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ConnectionDeleteEditPolicy extends ConnectionEditPolicy {

	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editpolicies.ConnectionEditPolicy
	 * #getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	@Override
	protected Command getDeleteCommand( GroupRequest req ) {

		EipConnectionDeleteCommand command = new EipConnectionDeleteCommand();
		command.setConnection((EipConnection) getHost().getModel());
		return command;
	}
}