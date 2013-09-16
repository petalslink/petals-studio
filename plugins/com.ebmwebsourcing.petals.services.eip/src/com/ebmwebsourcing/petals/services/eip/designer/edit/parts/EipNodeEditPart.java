/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.eip.designer.edit.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;

import com.ebmwebsourcing.petals.services.eip.designer.figures.EipNodeFigure;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;

/**
 * An edit part for an EIP node.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipNodeEditPart extends AbstractNodeEditPart {

	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart
	 * #createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		return new EipNodeFigure();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #refreshVisuals()
	 */
	@Override
	protected void refreshVisuals() {

		EipNodeFigure figure = (EipNodeFigure) getFigure();
		EipNode node = (EipNode) getModel();

		figure.setName( node.getServiceName() != null ? node.getServiceName() : "?" );
		figure.setPattern( node.getEipType());

		List<String> errors = node.getErrorMessages();
		if( errors.size() == 1 ) {
			figure.setErrorMessage( errors.get( 0 ));
		}
		else if( ! errors.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			sb.append( errors.size() + " errors were found." );
			for( String s : errors )
				sb.append( "\n+ " + s );

			figure.setErrorMessage( sb.toString());
		}
		else {
			figure.setErrorMessage( null );
		}

		Point location = node.getLocation();
		Rectangle r = new Rectangle( location.x, location.y, -1, -1 );
		((GraphicalEditPart) getParent()).setLayoutConstraint( this, figure, r );
	}


	/*
	 * (non-Jsdoc)
	 * @see java.beans.PropertyChangeListener
	 * #propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange( PropertyChangeEvent evt ) {

		if( EipNode.PROPERTY_EIP_TYPE.equals( evt.getPropertyName()))
			refreshVisuals();

		else if( EipNode.PROPERTY_OUTGOING_CONNECTION.equals( evt.getPropertyName())) {

			// Sync the model
			refreshSourceConnections();

			// Refresh the visuals of all the connections
			for( Object o : getSourceConnections()) {
				if( o instanceof EipConnectionEditPart )
					((EipConnectionEditPart) o).refreshVisuals();
			}
		}

		else
			super.propertyChange( evt );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.services.eip.designer.edit.parts.AbstractNodeEditPart
	 * #getModelTargetConnections()
	 */
	@SuppressWarnings( "rawtypes" )
	@Override
	public List getModelSourceConnections() {
		return ((EipNode) getModel()).getOutgoingConnections();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #setSelected(int)
	 */
	@Override
	public void setSelected( int value ) {
		super.setSelected( value );
		((EipNodeFigure) getFigure()).setSelected( value != EditPart.SELECTED_NONE );
	}
}
