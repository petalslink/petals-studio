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
package com.ebmwebsourcing.petals.services.eip.designer.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.ebmwebsourcing.petals.services.eip.designer.EipChainDiagramEditor;
import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.NodeSwitchCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.AbstractNodeEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SwitchNodesAction extends Action {

	private final EipChainDiagramEditor editor;
	private AbstractNodeEditPart part1, part2;


	/**
	 * Constructor.
	 * @param editor
	 */
	public SwitchNodesAction( EipChainDiagramEditor editor ) {

		this.editor = editor;

		setId( "com.ebmwebsourcing.petals.services.eip.contextmenu.switchnodes" );
		setText( "Switch Nodes" );
		setToolTipText( "Switch the two selected nodes" );
		setDescription( "Switch the two selected nodes." );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.action.Action
	 * #isEnabled()
	 */
	@Override
	public boolean isEnabled() {

		this.part1 = null;
		this.part2 = null;

		ISelection s = this.editor.getSite().getSelectionProvider().getSelection();
		if( s instanceof IStructuredSelection ) {

			List<AbstractNodeEditPart> parts = new ArrayList<AbstractNodeEditPart> ();
			for( Iterator<?> it = ((IStructuredSelection) s).iterator(); it.hasNext(); ) {
				Object o = it.next();
				if( o instanceof AbstractNodeEditPart )
					parts.add((AbstractNodeEditPart) o);
			}

			if( parts.size() == 2 ) {
				AbstractNode m1 = (AbstractNode) parts.get( 0 ).getModel();
				AbstractNode m2 = (AbstractNode) parts.get( 1 ).getModel();
				if( m1.getIncomingConnection() != null
							&& m2.getIncomingConnection() != null ) {

					AbstractNode n1 = m1.getIncomingConnection().getSource();
					AbstractNode n2 = m2.getIncomingConnection().getSource();
					if( n1 != null && n1 == n2 ) {
						this.part1 = parts.get( 0 );
						this.part2 = parts.get( 1 );
					}
				}
			}
		}

		return this.part1 != null && this.part2 != null;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.action.Action
	 * #run()
	 */
	@Override
	public void run() {
		NodeSwitchCommand cmd = new NodeSwitchCommand( this.part1, this.part2 );
		this.editor.getEditingDomain().getCommandStack().execute( cmd );
	}
}
