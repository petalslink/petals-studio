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
package com.ebmwebsourcing.petals.services.eip.designer.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;

/**
 * A figure to represents an initial note.
 * <p>
 * Widely copied from Eclipse GEF samples (BentCornerFigure and StickyNoteFigure).
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class NoteFigure extends Figure {

	private static int DEFAULT_CORNER_SIZE = 10;
	private final TextFlow textFlow;
	private int cornerSize;


	/**
	 * Constructor.
	 */
	public NoteFigure() {

		setBackgroundColor( ColorConstants.tooltipBackground );
		setForegroundColor( ColorConstants.black );
		setCornerSize( DEFAULT_CORNER_SIZE );
		setBorder( new MarginBorder( 27, 18, 27, 18 ));

		FlowPage flowPage = new FlowPage();
		flowPage.setHorizontalAligment( PositionConstants.CENTER );

		this.textFlow = new TextFlow();
		this.textFlow.setLayoutManager(
					new ParagraphTextLayout(
								this.textFlow,
								ParagraphTextLayout.WORD_WRAP_TRUNCATE ));

		flowPage.add( this.textFlow );
		setLayoutManager( new StackLayout());
		add( flowPage );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.draw2d.Figure
	 * #paintFigure(org.eclipse.draw2d.Graphics)
	 */
	@Override
	protected void paintFigure( Graphics graphics ) {

		Rectangle rect = getBounds().getCopy();
		graphics.translate( getLocation());

		// Fill the note
		PointList outline = new PointList();

		outline.addPoint( 0, 0 );
		outline.addPoint( rect.width - this.cornerSize, 0 );
		outline.addPoint( rect.width - 1, this.cornerSize );
		outline.addPoint( rect.width - 1, rect.height - 1 );
		outline.addPoint( 0, rect.height - 1 );

		graphics.fillPolygon( outline );


		// Draw the inner outline
		PointList innerLine = new PointList();

		innerLine.addPoint( rect.width - this.cornerSize - 1, 0 );
		innerLine.addPoint( rect.width - this.cornerSize - 1, this.cornerSize );
		innerLine.addPoint( rect.width - 1, this.cornerSize );
		innerLine.addPoint( rect.width - this.cornerSize - 1, 0 );
		innerLine.addPoint( 0, 0 );
		innerLine.addPoint( 0, rect.height - 1 );
		innerLine.addPoint( rect.width - 1, rect.height - 1 );
		innerLine.addPoint( rect.width - 1, this.cornerSize );
		graphics.drawPolygon( innerLine );

		graphics.translate( getLocation().getNegated());
	}


	/**
	 * @param newSize
	 */
	public void setCornerSize( int newSize ) {
		this.cornerSize = newSize;
	}


	/**
	 * @return
	 */
	public int getCornerSize() {
		return this.cornerSize;
	}


	/**
	 * @return
	 */
	public String getText() {
		return this.textFlow.getText();
	}

	/**
	 * @param newText
	 */
	public void setText( String newText ) {
		this.textFlow.setText( newText );
	}
}
