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

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ToolbarLayout;

import com.ebmwebsourcing.petals.services.eip.designer.EipDesignerImageStore;

/**
 * A figure for an end-point node.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EndpointFigure extends Figure {

	private final Label nameLabel, errorLabel;


	/**
	 * Constructor.
	 */
	public EndpointFigure() {

		// The layout
		ToolbarLayout layout = new ToolbarLayout( ToolbarLayout.HORIZONTAL );
		layout.setSpacing( 7 );
		setLayoutManager( layout );

		// The end-point name
		this.nameLabel = new Label( EipDesignerImageStore.INSTANCE.getEdptImage( false ));
		this.nameLabel.setBorder( new MarginBorder( 2 ));
		add( this.nameLabel );

		// The error label
		this.errorLabel = new Label();
		add( this.errorLabel );

		// Add a padding border
		setBorder( new MarginBorder( 6, 2, 6, 2 ));
	}


	/**
	 * Sets the name.
	 * @param name the name (not null)
	 */
	public void setName( String name ) {
		this.nameLabel.setText( name );
	}


	/**
	 * Displays an error message on the figure.
	 * @param errorMessage the error message (null to indicate that there is no error)
	 */
	public void setErrorMessage( String errorMessage ) {

		if( errorMessage == null ) {
			this.errorLabel.setIcon( null );
			this.errorLabel.setToolTip( null );

		} else {
			this.errorLabel.setIcon( EipDesignerImageStore.INSTANCE.getErrorIcon());
			this.errorLabel.setToolTip( new Label( errorMessage ));
		}
	}


	/**
	 * Updates the figure when the edit part is selected or unselected
	 * @param selected
	 */
	public void setSelected( boolean selected ) {
		this.nameLabel.setIcon( EipDesignerImageStore.INSTANCE.getEdptImage( selected ));
	}
}
