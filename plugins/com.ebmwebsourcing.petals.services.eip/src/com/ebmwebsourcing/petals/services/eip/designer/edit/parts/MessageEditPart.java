/****************************************************************************
 * 
 * Copyright (c) 2010-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.ebmwebsourcing.petals.services.eip.designer.figures.NoteFigure;

/**
 * An edit part for messages to display (typically when there is no child).
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MessageEditPart extends AbstractGraphicalEditPart {

	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart
	 * #createFigure()
	 */
	@Override
	protected IFigure createFigure() {

		NoteFigure fig =  new NoteFigure();
		fig.setText( "This is the Graphical Area.\n\nStart adding content to delete this note." );

		return fig;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #refreshVisuals()
	 */
	@Override
	protected void refreshVisuals() {
		Rectangle r = new Rectangle( 100, 100, -1, -1 );
		((GraphicalEditPart) getParent()).setLayoutConstraint( this, getFigure(), r );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.services.eip.designer.edit.parts.AbstractNodeEditPart
	 * #isSelectable()
	 */
	@Override
	public boolean isSelectable() {
		return false;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		// nothing
	}
}
