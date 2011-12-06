/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
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

import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;

/**
 * A command to move a node in the diagram.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class NodeChangeLayoutCommand extends Command {

	private Point location, oldLocation;
	private AbstractNode node;


	/**
	 * @param location the location to set
	 */
	public void setLocation( Point location ) {
		this.location = location;
	}


	/**
	 * @param node the node to set
	 */
	public void setNode( AbstractNode node ) {
		this.node = node;
		this.oldLocation = node.getLocation();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		this.node.setLocation( this.location );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	@Override
	public boolean canExecute() {
		return true;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		this.node.setLocation( this.oldLocation );
	}
}
