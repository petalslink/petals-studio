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

package com.ebmwebsourcing.petals.common.xpath.internal.provisional.configuration;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * A class used to cache colors.
 * <p>
 * Forked from the Eclipse BPEL Designer.
 * </p>
 */
public class ColorManager {

	protected Map<RGB,Color> rgbToColor = new HashMap<RGB,Color>( 10 );


	/**
	 * Disposes the SWT resources.
	 */
	public void dispose() {

		for(  Color color : this.rgbToColor.values()) {
			color.dispose();
			color = null;
		}

		this.rgbToColor.clear();
	}


	/**
	 * Gets the color corresponding to the given RGB.
	 * @param rgb a RGB instance
	 * @return the associated color
	 */
	public Color getColor( RGB rgb ) {

		Color color = this.rgbToColor.get( rgb );
		if( color == null ) {
			color = new Color( Display.getCurrent(), rgb );
			this.rgbToColor.put( rgb, color );
		}

		return color;
	}
}
