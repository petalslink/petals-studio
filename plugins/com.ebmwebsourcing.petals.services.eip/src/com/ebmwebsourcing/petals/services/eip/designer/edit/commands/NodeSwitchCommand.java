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

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.CompoundCommand;

import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.AbstractNodeEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;

/**
 * This command switches the position of two nodes.
 * <p>
 * By position, we mean both the diagram position (coordinates) and the
 * position in the invocation order. The latter is defined by the index of
 * a connection in the outgoing connections of an EIP node.
 * </p>
 * <p>
 * Such a command only makes sense for ROUTER, DYNAMIC-ROUTER and ROUTING-SLIP.
 * However, there is no restriction at this level. The command can be executed for
 * any pattern.
 * </p>
 * <p>
 * When two nodes are switched, their incoming connection remain the same.
 * The position of these connections are themselves switched in array of outgoing
 * connections the invoking node owns.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class NodeSwitchCommand extends CompoundCommand {

	private final AbstractNodeEditPart part1, part2;
	private final AbstractNode node1, node2;


	/**
	 * Constructor.
	 * @param part1
	 * @param part2
	 */
	public NodeSwitchCommand( AbstractNodeEditPart part1, AbstractNodeEditPart part2 ) {

		if( part1 == null || part2 == null )
			throw new IllegalArgumentException();

		this.part1 = part1;
		this.part2 = part2;
		this.node1 = (AbstractNode) part1.getModel();
		this.node2 = (AbstractNode) part2.getModel();

		// Switch the connections
		EipNodeSwitchOutgoingConnectionsCommand cmd = new EipNodeSwitchOutgoingConnectionsCommand();
		cmd.setConn1( this.node1.getIncomingConnection());
		cmd.setConn2( this.node2.getIncomingConnection());
		cmd.setEipNode( this.node1.getIncomingConnection().getSource());
		add( cmd );

		// Find the lowest node
		// Be CAREFUL! The y-axis is downward oriented.
		AbstractNode bottomNode, topNode;
		AbstractNodeEditPart bottomEp, topEp;
		if( this.node1.getLocation().y < this.node2.getLocation().y ) {
			bottomNode = this.node2;
			bottomEp = part2;
			topNode = this.node1;
			topEp = part1;

		} else {
			bottomNode = this.node1;
			bottomEp = part1;
			topNode = this.node2;
			topEp = part2;
		}

		Point bottomLoc = bottomNode.getLocation();
		Point topLoc = topNode.getLocation();

		// Compute the offset to adjust the Y-coordinates
		int yOffset = bottomEp.getFigure().getSize().height - topEp.getFigure().getSize().height;

		// Move up the lowest node
		NodeChangeLayoutCommand move = new NodeChangeLayoutCommand();
		move.setNode( bottomNode );
		move.setLocation( topLoc );
		add( move );

		// Move down the other node
		move = new NodeChangeLayoutCommand();
		move.setNode( topNode );
		move.setLocation( new Point( bottomLoc.x, bottomLoc.y + yOffset ));
		add( move );

		// Fix the Y-coordinate for the following nodes in the column
		boolean startUpdate = false;
		int found = 0;
		for( EipConnection c : bottomNode.getIncomingConnection().getSource().getOutgoingConnections()) {

			// Only handle intermediate nodes
			if( found > 1 )
				continue;

			// Do not update nodes that are before
			// Besides, the graphical position does not say anything
			// about the invocation order => test for both nodes
			if( c.getTarget().equals( topNode )
						|| c.getTarget().equals( bottomNode )) {
				startUpdate = true;
				found ++;
				continue;
			}

			// Adjust the Y coordinate (+/-)
			if( startUpdate ) {
				move = new NodeChangeLayoutCommand();
				move.setNode( c.getTarget());

				Point loc = c.getTarget().getLocation();
				move.setLocation( new Point( loc.x, loc.y + yOffset ));
				add( move );
			}
		}
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #canExecute()
	 */
	@Override
	public boolean canExecute() {

		return this.node1.getIncomingConnection() != null
		&& this.node2.getIncomingConnection() != null
		&& this.node1.getIncomingConnection().getSource() != null
		&& this.node1.getIncomingConnection().getSource() == this.node2.getIncomingConnection().getSource();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.CompoundCommand
	 * #execute()
	 */
	@Override
	public void execute() {
		animate( this.part1, this.node1, this.part2, this.node2 );
		super.execute();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.CompoundCommand
	 * #undo()
	 */
	@Override
	public void undo() {
		animate( this.part2, this.node2, this.part1, this.node1 );
		super.undo();
	}


	/**
	 * Animates the position changes on the diagram.
	 * @param fromPart
	 * @param fromNode
	 * @param toPart
	 * @param toNode
	 */
	private static void animate(
				AbstractNodeEditPart fromPart, AbstractNode fromNode,
				AbstractNodeEditPart toPart, AbstractNode toNode ) {

		// Compute intermediate coordinates
		Point loc1 = fromNode.getLocation();
		Point loc2 = toNode.getLocation();

		int centerY = fromNode.getLocation().y - toNode.getLocation().y;
		centerY = Math.abs( centerY ) / 2;

		int centerX = fromNode.getLocation().x - toNode.getLocation().x;
		centerX = Math.abs( centerX ) / 2;


		// Play an animation...

		// Hide the connections
		setTargetConnectionsVisible( fromPart, false );
		setTargetConnectionsVisible( toPart, false );


		// We have a circle, whose center is at the middle of the segment delimited
		// by the two node locations.
		// One node will go through one side of the circle, while the
		// second one will go through the other half.
		int step = 5;
		int loopX = 0, loopY = 0;

		// First part of the path
		while( loopX < centerX || loopY < centerY ) {

			int newX = loc1.x > loc2.x ? loc1.x - loopX : loc1.x + loopX;
			int newY = loc1.y > loc2.y ? loc1.y - loopY : loc1.y + loopY;
			Rectangle r = new Rectangle( newX, newY, -1, -1 );
			((GraphicalEditPart) fromPart.getParent()).setLayoutConstraint( fromPart, fromPart.getFigure(), r );

			newX = loc1.x > loc2.x ? loc2.x + loopX : loc2.x - loopX;
			newY = loc1.y > loc2.y ? loc2.y + loopY : loc2.y - loopY;
			r = new Rectangle( newX, newY, -1, -1 );
			((GraphicalEditPart) toPart.getParent()).setLayoutConstraint( toPart, toPart.getFigure(), r );

			((GraphicalEditPart) toPart.getParent()).getFigure().getUpdateManager().performUpdate();
			loopX += step;
			loopY += step;

			try {
				Thread.sleep( 10 );
			} catch( InterruptedException e ) {
				// nothing
			}
		}

		// Second part of the path
		while( loopX > 0 || loopY > 0 ) {

			int newX = loc1.x > loc2.x ? loc1.x - loopX : loc1.x + loopX;
			int newY = loc1.y > loc2.y ? loc2.y + loopY : loc2.y - loopY;
			Rectangle r = new Rectangle( newX, newY, -1, -1 );
			((GraphicalEditPart) fromPart.getParent()).setLayoutConstraint( fromPart, fromPart.getFigure(), r );

			newX = loc1.x > loc2.x ? loc2.x + loopX : loc2.x - loopX;
			newY = loc1.y > loc2.y ? loc1.y - loopY : loc1.y + loopY;
			r = new Rectangle( newX, newY, -1, -1 );
			((GraphicalEditPart) toPart.getParent()).setLayoutConstraint( toPart, toPart.getFigure(), r );

			((GraphicalEditPart) toPart.getParent()).getFigure().getUpdateManager().performUpdate();
			loopX -= step;
			loopY -= step;

			try {
				Thread.sleep( 10 );
			} catch( InterruptedException e ) {
				// nothing
			}
		}


		// Show the connections
		setTargetConnectionsVisible( fromPart, true );
		setTargetConnectionsVisible( toPart, true );


		// TODO: add padding and move the intermediate nodes...
	}


	/**
	 * Defines the visibility of the target connections.
	 * @param ep
	 * @param visible
	 */
	private static void setTargetConnectionsVisible( GraphicalEditPart ep, boolean visible ) {

		for( Object o : ep.getTargetConnections()) {
			if( o instanceof Connection )
				((Connection) o).setVisible( visible );
		}
	}
}
