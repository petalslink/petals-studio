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
 
package com.ebmwebsourcing.petals.services.eip.designer.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.action.Action;

import com.ebmwebsourcing.petals.services.eip.designer.EipChainDiagramEditor;
import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.NodeChangeLayoutCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.AbstractNodeEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.helpers.SubGraphStatistics;
import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ArrangeAllAction extends Action {

	private final EipChainDiagramEditor editor;


	/**
	 * Constructor.
	 * @param editor
	 */
	public ArrangeAllAction( EipChainDiagramEditor editor ) {

		this.editor = editor;

		setId( "com.ebmwebsourcing.petals.services.eip.contextmenu.arrangeall" );
		setText( "Arrange All" );
		setToolTipText( "Arrange the diagram layout" );
		setDescription( "Move all the nodes at their best position." );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.action.Action
	 * #run()
	 */
	@Override
	public void run() {

		// Build the map that associates nodes and figures
		Map<AbstractNode,IFigure> nodeToFigure = new HashMap<AbstractNode,IFigure> ();
		Map<?,?> map = ((GraphicalViewer) this.editor.getAdapter( GraphicalViewer.class )).getEditPartRegistry();
		for( Map.Entry<?,?> entry : map.entrySet()) {
			if( entry.getKey() instanceof AbstractNode
						&& entry.getValue() instanceof AbstractNodeEditPart ) {

				IFigure fig = ((AbstractNodeEditPart) entry.getValue()).getFigure();
				nodeToFigure.put((AbstractNode) entry.getKey(), fig );
			}
		}

		// Compute node preferred positions
		// First, we need to build sub-graphs
		List<AbstractNode> subGraphRoots = new ArrayList<AbstractNode> ();
		for( EipNode eip : this.editor.getEipChain().getEipNodes()) {
			if( eip.getIncomingConnection() == null )
				subGraphRoots.add( eip );
		}

		for( Endpoint edpt : this.editor.getEipChain().getEndpoints()) {
			if( edpt.getIncomingConnection() == null )
				subGraphRoots.add( edpt );
		}

		// Then, process each sub-graph
		List<SubGraphStatistics> stats = new ArrayList<SubGraphStatistics> ();
		for( AbstractNode rootNode : subGraphRoots )
			stats.add( new SubGraphStatistics( rootNode, nodeToFigure ));

		// Create a compound command to execute in a single transaction all the
		// location change commands
		CompoundCommand cmd = new CompoundCommand();

		// Create a move command for every node
		int xOffset = 0, yOffset = 100;
		for( SubGraphStatistics stat : stats ) {

			// Move the nodes of this sub-graph
			int maxY = 0;
			for( Map.Entry<AbstractNode,Point> entry : stat.getNodeToRelativeLocation().entrySet()) {

				// Keep it for debug
				// System.out.print( entry.getKey() instanceof Endpoint ? "Endpoint : " : "EIP : " );
				// System.out.println( entry.getKey().getInterfaceName());
				// System.out.println( "( " + entry.getValue().x + ", " + entry.getValue().y + " )\n" );

				Point newLoc = new Point(
							entry.getValue().x + xOffset,
							entry.getValue().y + yOffset );

				if( entry.getValue().y > maxY )
					maxY = entry.getValue().y;

				NodeChangeLayoutCommand clm = new NodeChangeLayoutCommand();
				clm.setNode( entry.getKey());
				clm.setLocation( newLoc );
				cmd.add( clm );
			}


			// Put each sub-graph under another one
			yOffset += maxY + 70; // 70 = padding-Y between each sub-graph
		}

		// Execute it
		this.editor.getEditingDomain().getCommandStack().execute( cmd );
	}
}
