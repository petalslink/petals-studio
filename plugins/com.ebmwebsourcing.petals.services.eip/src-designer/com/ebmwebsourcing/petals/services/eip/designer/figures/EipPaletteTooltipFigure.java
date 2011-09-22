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
package com.ebmwebsourcing.petals.services.eip.designer.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

import com.ebmwebsourcing.petals.services.eip.designer.model.EIPtype;

/**
 * A figure to display when a palette entry for an EIP is hovered.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipPaletteTooltipFigure extends Figure {

	private final Font font;


	/**
	 * Constructor.
	 * @param eipType
	 */
	public EipPaletteTooltipFigure( EIPtype eipType ) {

		setLayoutManager( new ToolbarLayout());
		setBackgroundColor( ColorConstants.tooltipBackground );
		setOpaque( true );
		setBorder( new CompoundBorder(
					new LineBorder( ColorConstants.black, 1 ),
					new MarginBorder( 12 )));

		FontData fontData = new FontData( "Arial", 13, SWT.BOLD );
		this.font = new Font( Display.getDefault(), fontData );

		Label l = new Label( eipType.getPrettyName());
		l.setFont( this.font );
		l.setLabelAlignment( PositionConstants.LEFT );
		l.setBorder( new MarginBorder( 0, 4, 12, 4 ));
		add( l );

		l = new Label( eipType.getExplaination());
		l.setBorder( new MarginBorder( 4, 4, 6, 4 ));
		add( l );
	}


	/*
	 * (non-Jsdoc)
	 * @see java.lang.Object
	 * #finalize()
	 */
	@Override
	protected void finalize() throws Throwable {

		if( this.font != null && ! this.font.isDisposed())
			this.font.dispose();

		super.finalize();
	}
}
