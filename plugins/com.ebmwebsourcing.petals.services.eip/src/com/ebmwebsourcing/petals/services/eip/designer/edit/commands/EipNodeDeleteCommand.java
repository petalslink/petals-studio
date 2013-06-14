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
