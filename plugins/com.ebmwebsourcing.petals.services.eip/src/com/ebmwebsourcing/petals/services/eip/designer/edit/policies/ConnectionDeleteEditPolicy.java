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
