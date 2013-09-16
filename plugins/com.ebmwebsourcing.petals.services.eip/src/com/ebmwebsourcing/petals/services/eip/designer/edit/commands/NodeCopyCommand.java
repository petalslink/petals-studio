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