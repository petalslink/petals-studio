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
 
package com.ebmwebsourcing.petals.services.eip.designer.figures;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Insets;

/**
 * A figure to contain separated items.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class CompartmentFigure extends Figure {

	/**
	 * Constructor.
	 */
	public CompartmentFigure() {

		// The layout
		ToolbarLayout layout = new ToolbarLayout();
		layout.setMinorAlignment( ToolbarLayout.ALIGN_CENTER );
		layout.setStretchMinorAxis( false );
		layout.setSpacing( 2 );
		setLayoutManager( layout );

		// The border
		setBorder( new AbstractBorder() {
			public Insets getInsets( IFigure figure ) {
				return new Insets( 1, 0, 0, 0 );
			}

			public void paint( IFigure figure, Graphics graphics, Insets insets ) {
				graphics.drawLine( getPaintRectangle( figure, insets ).getTopLeft(), tempRect.getTopRight());
			}
		});
	}
}
