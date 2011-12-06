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
