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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.ebmwebsourcing.petals.services.eip.designer.model.EipChain;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;

/**
 * A command to create an EIP.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipNodeCreateCommand extends Command {

	private EipChain eipChain;
	private EipNode newEipNode;
	private Point location;


	/**
	 * @param eipChain the eipChain to set
	 */
	public void setEipChain( EipChain eipChain ) {
		this.eipChain = eipChain;
	}


	/**
	 * @param newEipNode the newEipNode to set
	 */
	public void setNewEipNode( EipNode newEipNode ) {
		this.newEipNode = newEipNode;
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
		this.eipChain.adoptEipNode( this.newEipNode );
		this.newEipNode.setLocation( this.location );
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

		this.eipChain.removeEipNode( this.newEipNode );
		if( this.newEipNode.getId() != -1 )
			this.eipChain.setNextNodeId( this.newEipNode.getId());
	}
}
