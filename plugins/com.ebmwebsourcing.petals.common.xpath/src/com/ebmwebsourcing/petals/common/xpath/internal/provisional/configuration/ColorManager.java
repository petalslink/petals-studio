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
