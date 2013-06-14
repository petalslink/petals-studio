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

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;

/**
 * A command to copy nodes.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class NodeCopyCommand extends Command {

	private final Collection<AbstractNode> nodes = new HashSet<AbstractNode> ();


	/**
	 * Adds nodes to the list of nodes to copy.
	 * @param node the node to add
	 */
	public void addElement( AbstractNode node ) {
		this.nodes.add( node );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #canExecute()
	 */
	@Override
	public boolean canExecute() {
		return ! this.nodes.isEmpty();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #execute()
	 */
	@Override
	public void execute() {
		Clipboard.getDefault().setContents( this.nodes );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #canUndo()
	 */
	@Override
	public boolean canUndo() {
		return false;
	}
}