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
package com.ebmwebsourcing.petals.services.eip.designer.edit.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.ebmwebsourcing.petals.services.eip.designer.model.EipChain;
import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;

/**
 * A command to create an end-point.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EndpointCreateCommand extends Command {

	private EipChain eipChain;
	private Endpoint newEdpt;
	private Point location;


	/**
	 * @param eipChain the eipChain to set
	 */
	public void setEipChain( EipChain eipChain ) {
		this.eipChain = eipChain;
	}


	/**
	 * @param newEdpt the newEdpt to set
	 */
	public void setNewEndpoint( Endpoint newEdpt ) {
		this.newEdpt = newEdpt;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation( Point location ) {
		this.location = location;
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
	 * #execute()
	 */
	@Override
	public void execute() {
		this.eipChain.adoptEndpoint( this.newEdpt );
		this.newEdpt.setLocation( this.location );
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
	 * #undo()
	 */
	@Override
	public void undo() {

		this.eipChain.removeEndpoint( this.newEdpt );
		if( this.newEdpt.getId() != -1 )
			this.eipChain.setNextNodeId( this.newEdpt.getId());
	}
}
