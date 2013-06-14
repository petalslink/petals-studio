/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.edit.parts;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;

import com.ebmwebsourcing.petals.services.eip.designer.figures.EndpointFigure;
import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;

/**
 * An edit part for an EIP node.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EndpointEditPart extends AbstractNodeEditPart {

	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart
	 * #createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		return new EndpointFigure();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #refreshVisuals()
	 */
	@Override
	protected void refreshVisuals() {

		EndpointFigure figure = (EndpointFigure) getFigure();
		Endpoint node = (Endpoint) getModel();
		figure.setName( node.getServiceName() != null ? node.getServiceName() : "?" );

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
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #setSelected(int)
	 */
	@Override
	public void setSelected( int value ) {
		super.setSelected( value );
		((EndpointFigure) getFigure()).setSelected( value != EditPart.SELECTED_NONE );
	}
}
