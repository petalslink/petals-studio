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

import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;

/**
 * A command to delete an end-point.
 * <p>
 * Attached connections are not removed by this command.
 * To do so, use a {@link CompoundCommand} and associate it with {@link EipConnectionDeleteCommand}.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EndpointDeleteCommand extends Command {

	private Endpoint edpt;


	/**
	 * @param edpt the end-point to set
	 */
	public void setEndpoint( Endpoint edpt ) {
		this.edpt = edpt;
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
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		this.edpt.getEipChain().removeEndpoint( this.edpt );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #undo()
	 */
	@Override
	public void undo() {
		this.edpt.getEipChain().restoreEndpoint( this.edpt );
	}
}
