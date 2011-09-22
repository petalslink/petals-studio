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
package com.ebmwebsourcing.petals.services.eip.designer.edit.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ShortestPathConnectionRouter;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.swt.SWT;

import com.ebmwebsourcing.petals.services.eip.designer.edit.policies.NodeEditLocationPolicy;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipChain;

/**
 * An edit part for an EIP node.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipChainEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart
	 * #createFigure()
	 */
	@Override
	protected IFigure createFigure() {

		// Create the figure
		FreeformLayer figure = new FreeformLayer();
		figure.setLayoutManager( new FreeformLayout());
		figure.setBorder( new MarginBorder( 12 ));
		figure.setBackgroundColor( ColorConstants.white );

		// Create the static router for the connection layer
		ConnectionLayer connLayer = (ConnectionLayer) getLayer( LayerConstants.CONNECTION_LAYER );
		connLayer.setAntialias( SWT.ON );
		connLayer.setConnectionRouter( new ShortestPathConnectionRouter( figure ));

		return figure;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart
	 * #activate()
	 */
	@Override
	public void activate() {
		super.activate();
		((EipChain) getModel()).addPropertyChangeListener( this );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart
	 * #deactivate()
	 */
	@Override
	public void deactivate() {
		((EipChain) getModel()).removePropertyChangeListener( this );
		super.deactivate();
	}


	/*
	 * (non-Jsdoc)
	 * @see java.beans.PropertyChangeListener
	 * #propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange( PropertyChangeEvent evt ) {
		if( EipChain.PROPERTY_CHILD.equals( evt.getPropertyName()))
			refreshChildren();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {

		// Prevent removal from this edit part
		installEditPolicy( EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());

		// Be able to move nodes on the diagram
		installEditPolicy( EditPolicy.LAYOUT_ROLE, new NodeEditLocationPolicy());
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #getModelChildren()
	 */
	@SuppressWarnings( "rawtypes" )
	@Override
	protected List getModelChildren() {

		List<Object> children = new ArrayList<Object> ();
		children.addAll(((EipChain) getModel()).getEipNodes());
		children.addAll(((EipChain) getModel()).getEndpoints());

		if( children.isEmpty())
			children.add( "This is the Graphical Area" );

		return children;
	}
}
