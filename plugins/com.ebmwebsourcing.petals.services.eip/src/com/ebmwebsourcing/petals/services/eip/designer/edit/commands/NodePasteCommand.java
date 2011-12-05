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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipProperty;
import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;

/**
 * A command to paste nodes on the diagram.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class NodePasteCommand extends Command {

	private final Map<AbstractNode,AbstractNode> originalToCopy = new HashMap<AbstractNode,AbstractNode> ();


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #canExecute()
	 */
	@Override
	public boolean canExecute() {

		boolean result = true;
		Collection<?> nodesToCopy = (Collection<?>) Clipboard.getDefault().getContents();
		if (nodesToCopy == null || nodesToCopy.isEmpty()) {
			result = false;

		} else {
			for( Object o : nodesToCopy ) {
				if( o instanceof AbstractNode )
					this.originalToCopy.put((AbstractNode) o, null );
			}
		}

		return result;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #execute()
	 */
	@Override
	public void execute() {

		for( AbstractNode originalNode : this.originalToCopy.keySet()) {
			AbstractNode copy;
			if( originalNode instanceof Endpoint )
				copy = duplicateEndpoint((Endpoint) originalNode);
			else
				copy = duplicateEipNode((EipNode) originalNode);

			this.originalToCopy.put( originalNode, copy );
		}
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #redo()
	 */
	@Override
	public void redo() {

		for( AbstractNode node : this.originalToCopy.values()) {
			if( node instanceof Endpoint )
				node.getEipChain().restoreEndpoint((Endpoint) node);
			else
				node.getEipChain().restoreEipNode((EipNode) node);
		}
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #canUndo()
	 */
	@Override
	public boolean canUndo() {
		return ! this.originalToCopy.isEmpty();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command
	 * #undo()
	 */
	@Override
	public void undo() {

		for( AbstractNode node : this.originalToCopy.values()) {
			if( node instanceof Endpoint )
				node.getEipChain().removeEndpoint((Endpoint) node);
			else
				node.getEipChain().removeEipNode((EipNode) node);
		}
	}


	/**
	 * Duplicates an EIP node but with a new ID and a slightly different location.
	 * @param eip the EIP to duplicate
	 * @return the clone
	 */
	private EipNode duplicateEipNode( EipNode eip ) {

		EipNode clone = new EipNode( -1, eip.getEipType() );
		clone.setServiceName( eip.getServiceName());
		clone.setServiceNamespace( eip.getServiceNamespace());
		clone.setInterfaceName( eip.getInterfaceName());
		clone.setInterfaceNamespace( eip.getInterfaceNamespace());
		clone.setEndpointName( eip.getEndpointName());
		clone.setWsdlUri( eip.getWsdlUri());

		Point loc = eip.getLocation();
		Point newLoc = new Point( loc.x + 30, loc.y + 60 );
		clone.setLocation( newLoc );

		for( EipProperty property : eip.getSupportedProperties()) {
			String value = eip.getProperties().get( property );
			clone.setEipProperty( property, value );
		}

		eip.getEipChain().adoptEipNode( clone );
		return clone;
	}


	/**
	 * Duplicates an end-point but with a new ID and a slightly different location.
	 * @param edpt the end-point to duplicate
	 * @return the clone
	 */
	private Endpoint duplicateEndpoint( Endpoint edpt ) {

		Endpoint clone = new Endpoint( -1 );
		clone.setServiceName( edpt.getServiceName());
		clone.setServiceNamespace( edpt.getServiceNamespace());
		clone.setInterfaceName( edpt.getInterfaceName());
		clone.setInterfaceNamespace( edpt.getInterfaceNamespace());
		clone.setEndpointName( edpt.getEndpointName());

		Point loc = edpt.getLocation();
		Point newLoc = new Point( loc.x + 30, loc.y + 60 );
		clone.setLocation( newLoc );

		edpt.getEipChain().adoptEndpoint( clone );
		return clone;
	}
}
