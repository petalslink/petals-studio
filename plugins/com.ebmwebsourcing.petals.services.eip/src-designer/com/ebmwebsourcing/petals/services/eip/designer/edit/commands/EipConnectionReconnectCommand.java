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
package com.ebmwebsourcing.petals.services.eip.designer.edit.commands;

import org.eclipse.gef.commands.Command;

import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipConnectionReconnectCommand extends Command {

	private final EipConnection conn;
	private final EipNode oldSourceNode;
	private final AbstractNode oldTargetNode;
	private EipNode newSourceNode;
	private AbstractNode newTargetNode;


	/**
	 * Constructor.
	 * @param conn
	 */
	public EipConnectionReconnectCommand( EipConnection conn ) {

		if( conn == null )
			throw new IllegalArgumentException();

		this.conn = conn;
		this.oldSourceNode = conn.getSource();
		this.oldTargetNode = conn.getTarget();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #canExecute()
	 */
	@Override
	public boolean canExecute() {

		if( this.newSourceNode != null )
			return ! this.newSourceNode.equals( this.oldTargetNode );

		else if( this.newTargetNode != null )
			return ! this.newTargetNode.equals( this.oldSourceNode )
			&& this.newTargetNode.getIncomingConnection() == null;

		return false;
	}


	/**
	 * @param sourceNode
	 */
	public void setNewSourceNode( EipNode sourceNode ) {

		if( sourceNode == null )
			throw new IllegalArgumentException();

		this.newSourceNode = sourceNode;
		this.newTargetNode = null;
	}


	/**
	 * @param targetNode
	 */
	public void setNewTargetNode( AbstractNode targetNode ) {

		if( targetNode == null )
			throw new IllegalArgumentException();

		this.newSourceNode = null;
		this.newTargetNode = targetNode;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #execute()
	 */
	@Override
	public void execute() {

		if( this.newSourceNode != null )
			this.conn.reconnect( this.newSourceNode, this.oldTargetNode );

		else if( this.newTargetNode != null )
			this.conn.reconnect( this.oldSourceNode, this.newTargetNode );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #undo()
	 */
	@Override
	public void undo() {
		this.conn.reconnect( this.oldSourceNode,this.oldTargetNode );
	}
}
