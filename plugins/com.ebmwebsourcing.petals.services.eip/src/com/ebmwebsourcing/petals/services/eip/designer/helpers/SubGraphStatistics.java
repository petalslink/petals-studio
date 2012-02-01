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
package com.ebmwebsourcing.petals.services.eip.designer.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;

/**
 * A class which computes various statistics about a sub-graph of an EIP chain.
 * <p>
 * The root of such a sub-graph is an EIP node with no incoming connection.
 * The leaves of a sub-graph should be end-points.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SubGraphStatistics {

	/**
	 * Considering columns of nodes (i.e. levels in the tree), this
	 * value indicates the horizontal padding between the two node areas.
	 */
	private final static int GRID_PADDING_X = 240;

	/**
	 * Considering nodes on a same column (i.e. same level in the tree), this
	 * value indicates the vertical padding between the two node areas.
	 */
	private final static int GRID_PADDING_Y = 40;

	/**
	 * The default height of a node area (deduced approximately from the figure height).
	 * <p>
	 * To use if and only if the figure's size could not be retrieved.
	 * </p>
	 */
	private final static int GRID_CELL_DEFAULT_HEIGHT = 70;

	/**
	 * The default width of a node area (deduced approximately from the figure height).
	 * <p>
	 * To use if and only if the figure's size could not be retrieved.
	 * </p>
	 */
	private final static int GRID_CELL_DEFAULT_WIDTH = 90;

	/**
	 * A map that associates nodes with figures.
	 */
	private final Map<AbstractNode,IFigure> nodeToFigure;

	/**
	 * A map to associate a node with a tree level.
	 */
	private final Map<AbstractNode,Integer> nodeToLevel;

	/**
	 * A map that associates a node (keys) with a computed Y coordinate.
	 */
	private final Map<AbstractNode,Integer> nodeToCoY;

	/**
	 * A map that associates a tree level (keys) with a width (same width for all the nodes of this level).
	 * <p>
	 * Iterating through this map is done in the natural level order.
	 * The first key will always be 1.
	 * </p>
	 */
	private final TreeMap<Integer,Integer> levelToWidth;

	/**
	 * A map that associates a tree level (keys) with a ordered list of nodes.
	 * <p>
	 * Iterating through this map is done in the natural level order.
	 * The first key will always be 1.
	 * </p>
	 */
	private final TreeMap<Integer,ArrayList<AbstractNode>> levelToNodes;

	/**
	 * A map to associate a node with a relative location (if the sub-graph was the entire EIP chain).
	 */
	private final Map<AbstractNode,Point> nodeToRelativeLocation;

	/**
	 * The size of the sub-graph.
	 */
	private Dimension subGraphDimension;


	/**
	 * Constructor.
	 * @param rootNode
	 * @param nodeToFigure
	 */
	public SubGraphStatistics( AbstractNode rootNode, Map<AbstractNode,IFigure> nodeToFigure ) {
		this.nodeToFigure = nodeToFigure;

		this.nodeToLevel = new HashMap<AbstractNode,Integer> ();
		this.nodeToCoY = new HashMap<AbstractNode,Integer> ();
		this.levelToWidth = new TreeMap<Integer,Integer> ();
		this.levelToNodes = new TreeMap<Integer,ArrayList<AbstractNode>> ();
		this.nodeToRelativeLocation = new HashMap<AbstractNode,Point> ();

		computeNodeStatistics( rootNode, 1, 0 );
		adjustYCoordinates();
		computeNodeRelativeLocations();
	}


	/**
	 * @return the number of nodes in this sub-graph
	 */
	public int getNodeCount() {
		return this.nodeToLevel.size();
	}


	/**
	 * @return the nodeToRelativeLocation
	 */
	public Map<AbstractNode,Point> getNodeToRelativeLocation() {
		return this.nodeToRelativeLocation;
	}


	/**
	 * @return the subGraphDimension
	 */
	public Dimension getSubGraphDimension() {
		return this.subGraphDimension;
	}


	/**
	 * Adjusts the Y coordinate to prevent node overlap.
	 * <p>
	 * Here is a situation where this method is required.
	 * 
	 * <code>EIP_____ EIP 1 ______ edpt
	 *    \____ EIP 2 ______ edpt</code>
	 * 
	 * With the single algorithm, EIP 1 and 2 will overlap vertically.
	 * </p>
	 */
	private void adjustYCoordinates() {

		// 1st pass: correct the padding and suppress overlap
		List<EipNode> zeroNodes = new ArrayList<EipNode> ();
		Map<AbstractNode,Integer> nodeToNewOffset = new HashMap<AbstractNode,Integer> ();
		for( ArrayList<AbstractNode> nodes : this.levelToNodes.values()) {
			int maxY = 0;
			for( AbstractNode node : nodes ) {

				// Find the original Y
				int y = this.nodeToCoY.get( node );

				// The initial offset is inherited from the parent
				int offset = 0;
				if( node.getIncomingConnection() != null )
					offset = nodeToNewOffset.get( node.getIncomingConnection().getSource());

				// Prevent overlap
				if( y <= maxY )
					offset = maxY - y;

				// Make sure there is the minimal vertical padding
				if( maxY != 0 && y - maxY < GRID_PADDING_Y )
					offset += GRID_PADDING_Y - y + maxY;

				// Store the new Y
				y += offset;
				nodeToNewOffset.put( node, offset );
				this.nodeToCoY.put( node, y );

				// Update the maximal Y for this level
				maxY = y + getFigureSize( node ).height;

				// Something for the next pass?
				if( offset == 0 && node instanceof EipNode )
					zeroNodes.add((EipNode) node);
			}
		}

		// 2nd pass: there may be nodes that were not translated (e.g. the root node).
		// This pass moves them at the middle-Y...
		for( EipNode eip : zeroNodes ) {
			int y = computeCenteredYCoordinate( eip, getFigureSize( eip ));
			this.nodeToCoY.put( eip, y );
		}
	}


	/**
	 * Computes the location of all the nodes of this sub-graph.
	 * <p>
	 * Locations are considered ideal. It means that they are set as if the sub-graph
	 * was the entire EIP chain. Real locations will obtained from the relative locations
	 * by a translation. The translation depends on the offsets to display a sub-graph
	 * in the entire area of the EIP chain.
	 * </p>
	 */
	private void computeNodeRelativeLocations() {

		// Compute the X coordinate for every level
		Map<Integer,Integer> levelToCoX = new HashMap<Integer,Integer> ();
		int x = 0;
		for( Map.Entry<Integer,Integer> entry : this.levelToWidth.entrySet()) {
			if( x != 0 )
				x += GRID_PADDING_X;
			else
				x = 30;

			levelToCoX.put( entry.getKey(), x );
			x += entry.getValue();
		}

		// Compute the location for every node
		int maxX = 0, maxY = 0;
		for( Map.Entry<AbstractNode,Integer> entry : this.nodeToLevel.entrySet()) {
			int level = this.nodeToLevel.get( entry.getKey());
			x = levelToCoX.get( level );
			int y = this.nodeToCoY.get( entry.getKey());
			this.nodeToRelativeLocation.put( entry.getKey(), new Point( x, y ));

			if( x > maxX )
				maxX = x;

			if( y > maxY )
				maxY = y;
		}

		// Store the size of the sub-graph
		this.subGraphDimension = new Dimension( maxX, maxY );
	}


	/**
	 * Computes the level and the coordinates for a node of the sub-graph.
	 * <p>
	 * The algorithm relies on the following predicates:
	 * </p>
	 * <ol>
	 * 		<li>The X coordinate of a node depends on the level and the width of the previous levels.</li>
	 * 		<li>The width of a level depends on the width of the biggest figure of this level.</li>
	 * 		<li>The Y coordinate of a node depends on whether this node is a leaf or an intermediate node in the tree.</li>
	 * 		<li>The Y coordinate of a leaf is the highest computed Y + a specific padding + the height of the preceding figure.</li>
	 * 		<li>The Y coordinate of an intermediate node is the middle of the max and min Y of the node's children.</li>
	 * </ol>
	 * <p>
	 * Mathematically, it gives:
	 * </p>
	 * <ol>
	 * 		<li>X( node ) = X( node-1 ) + padding + Width( node-1 )</li>
	 * 		<li>Width( level ) = Math.max( Width( node )) where node is any node in the level / column.</li>
	 * 		<li>...</li>
	 * 		<li>Y( node ) = Math.max( Y( node2 )) where node2 is any node that has been processed before the current node.</li>
	 * 		<li>Y( node ) = [ Y( node_child_max ) + Y( node_child_min ) - Height( node ) ] / 2</li>
	 * </ol>
	 * 
	 * @param node
	 * @param level the tree level of the node (1 for the root level)
	 * @param maxY the biggest computed Y coordinate (for any column or level)
	 * @return the new biggest computed Y coordinate (can be the same than the received one)
	 */
	private int computeNodeStatistics( AbstractNode node, int level, int maxY ) {

		// Register this node
		this.nodeToLevel.put( node, level );
		ArrayList<AbstractNode> nodes = this.levelToNodes.get( level );
		if( nodes == null )
			nodes = new ArrayList<AbstractNode> ();

		nodes.add( node );
		this.levelToNodes.put( level, nodes );


		// Get the size of this figure
		Dimension dim = getFigureSize( node );


		// Update the width of the tree level.
		// The X coordinate of a node depends on the width for this level.
		// This is why it can only be computed once the entire tree has been explored.
		Integer levelWidth = this.levelToWidth.get( level );
		if( levelWidth == null )
			levelWidth = -1;

		if( dim.width > levelWidth ) {
			levelWidth = dim.width;
			this.levelToWidth.put( level, levelWidth );
		}


		// Compute the Y coordinate of the node
		// This last one depends on the Y coordinates of the children
		// Note that there are only two sub-classes of AbstractNode
		int y;
		if( node instanceof Endpoint ) {
			maxY += GRID_PADDING_Y;
			y = maxY;
			maxY += dim.height;

		} else {

			// Process all the children
			for( EipConnection conn : ((EipNode) node).getOutgoingConnections())
				maxY = computeNodeStatistics( conn.getTarget(), level + 1, maxY );

			// Determine the Y coordinate of the current EIP
			// The parent's location depends on the children
			y = computeCenteredYCoordinate((EipNode) node, dim );

			// No child found => treat it as an end-point
			if( y == -1 ) {
				maxY += GRID_PADDING_Y;
				y = maxY;
				maxY += dim.height;
			}
		}

		// Store the Y coordinate for a later use
		this.nodeToCoY.put( node, y );

		return maxY;
	}


	/**
	 * @param node a node
	 * @return the size of the figure associated to this node
	 */
	private Dimension getFigureSize( AbstractNode node ) {

		IFigure fig = this.nodeToFigure.get( node );
		int width, height;
		if( fig != null ) {
			width = fig.getSize().width;
			height = fig.getSize().height;

		} else {
			width = GRID_CELL_DEFAULT_WIDTH;;
			height = GRID_CELL_DEFAULT_HEIGHT;
			PetalsEipPlugin.log( "No figure was found for the node " + node.getId()
						+ " (EIP chain: " + node.getEipChain().getTitle() + " ).", IStatus.ERROR );
		}

		return new Dimension( width, height );
	}


	/**
	 * Computes the Y coordinate so that the EIP is centered with respect to its children.
	 * @param eip the EIP
	 * @param dim the dimension of the figure associated to the EIP
	 * @return the new Y coordinate, -1 if there is no child
	 */
	private int computeCenteredYCoordinate( EipNode eip, Dimension dim ) {

		// Find the minimal and maximal values
		int childMinY = -1, childMaxY = -1;
		AbstractNode lastNode = null;
		for( EipConnection conn : eip.getOutgoingConnections()) {

			lastNode = conn.getTarget();
			int childY = this.nodeToCoY.get( lastNode );
			childMaxY = childY;
			if( childMinY == -1 )
				childMinY = childY;
		}

		// Put it at the middle...
		int y = childMaxY + childMinY;

		// ... but take the figure's height in account.
		// When there is only 1 child, the EIP and the child should be aligned
		if( eip.getOutgoingConnections().size() == 1 ) {
			Dimension childDim = getFigureSize( lastNode );
			y -= dim.height - childDim.height;

		} else if( eip.getOutgoingConnections().size() > 1 ) {
			Dimension childDim = getFigureSize( lastNode );
			y += childDim.height - dim.height;
		}

		y = y / 2;
		return y;
	}
}
