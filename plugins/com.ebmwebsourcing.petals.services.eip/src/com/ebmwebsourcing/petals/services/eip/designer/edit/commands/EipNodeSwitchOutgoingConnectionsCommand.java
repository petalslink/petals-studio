/****************************************************************************
 * 
 * Copyright (c) 2011-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.edit.commands;

import org.eclipse.gef.commands.Command;

import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;

/**
 * A command to switch two outgoing connections in an EIP node.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipNodeSwitchOutgoingConnectionsCommand extends Command {

	private EipConnection conn1, conn2;
	private EipNode eipNode;


	/**
	 * Constructor (package only).
	 */
	EipNodeSwitchOutgoingConnectionsCommand() {
		// nothing
	}


	/**
	 * @param conn1 the conn1 to set
	 */
	public void setConn1( EipConnection conn1 ) {
		this.conn1 = conn1;
	}


	/**
	 * @param conn2 the conn2 to set
	 */
	public void setConn2( EipConnection conn2 ) {
		this.conn2 = conn2;
	}


	/**
	 * @param eipNode the eipNode to set
	 */
	public void setEipNode( EipNode eipNode ) {
		this.eipNode = eipNode;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #canExecute()
	 */
	@Override
	public boolean canExecute() {
		return this.eipNode.getOutgoingConnections().contains( this.conn1 )
		&& this.eipNode.getOutgoingConnections().contains( this.conn2 );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #execute()
	 */
	@Override
	public void execute() {
		this.eipNode.swapOutgoingConnections( this.conn1, this.conn2 );
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
		execute();
	}
}
