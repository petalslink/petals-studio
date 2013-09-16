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
 
package com.ebmwebsourcing.petals.services.eip.designer.edit.commands;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;

/**
 * A command to delete a node.
 * <p>
 * Attached connections are not removed by this command.
 * To do so, use a {@link CompoundCommand} and associate it with {@link EipConnectionDeleteCommand}.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipNodeDeleteCommand extends Command {

	private EipNode eip;


	/**
	 * @param eip the EIP node to set
	 */
	public void setEipNode( EipNode eip ) {
		this.eip = eip;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #canExecute()
	 */
	@Override
	public boolean canExecute() {
		return true;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #execute()
	 */
	@Override
	public void execute() {
		this.eip.getEipChain().removeEipNode( this.eip );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #undo()
	 */
	@Override
	public void undo() {
		this.eip.getEipChain().restoreEipNode( this.eip );
	}
}
