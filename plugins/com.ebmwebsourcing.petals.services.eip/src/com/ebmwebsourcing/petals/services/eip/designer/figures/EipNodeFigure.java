/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
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
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ToolbarLayout;

import com.ebmwebsourcing.petals.services.eip.designer.EipDesignerImageStore;
import com.ebmwebsourcing.petals.services.eip.designer.model.EIPtype;

/**
 * A figure for an EIP node.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipNodeFigure extends Figure {

	private final Label nameLabel, patternImgLabel, patternNameLabel;
	private EIPtype eipType;


	/**
	 * Constructor.
	 */
	public EipNodeFigure() {

		// The layout
		ToolbarLayout layout = new ToolbarLayout();
		setLayoutManager( layout );

		setBorder( new LineBorder( ColorConstants.black, 1 ));
		setOpaque( false );

		// The EIP details
		this.nameLabel = new Label();
		this.nameLabel.setBorder( new MarginBorder( 4, 4, 4, 4 ));
		add( this.nameLabel );

		// The image
		CompartmentFigure patternImageFigure = new CompartmentFigure();
		this.patternImgLabel = new Label();
		this.patternImgLabel.setBorder( new MarginBorder( 0, 4, 0, 4 ));
		patternImageFigure.add( this.patternImgLabel );

		this.patternNameLabel = new Label();
		this.patternNameLabel.setBorder( new MarginBorder( 0, 4, 0, 4 ));
		patternImageFigure.add( this.patternNameLabel );
		add( patternImageFigure );
	}


	/**
	 * Sets the EIP name.
	 * @param name the name (not null)
	 */
	public void setName( String name ) {
		this.nameLabel.setText( name );
	}


	/**
	 * Sets the EIP type.
	 * @param eipType the EIP type (may be null)
	 */
	public void setPattern( EIPtype eipType ) {
		this.eipType = eipType;
		this.patternImgLabel.setIcon( EipDesignerImageStore.INSTANCE.getEipImage( eipType, false ));
		this.patternNameLabel.setText( eipType.getPrettyName());
	}


	/**
	 * Displays an error message on the figure.
	 * @param errorMessage the error message (null to indicate that there is no error)
	 */
	public void setErrorMessage( String errorMessage ) {

		if( errorMessage == null ) {
			this.nameLabel.setIcon( null );
			this.nameLabel.setToolTip( null );

		} else {
			this.nameLabel.setIcon( EipDesignerImageStore.INSTANCE.getErrorIcon());
			this.nameLabel.setToolTip( new Label( errorMessage ));
		}
	}


	/**
	 * Updates the figure when the edit part is selected or unselected
	 * @param selected
	 */
	public void setSelected( boolean selected ) {
		this.patternImgLabel.setIcon( EipDesignerImageStore.INSTANCE.getEipImage( this.eipType, selected ));
	}
}
