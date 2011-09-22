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

package com.ebmwebsourcing.petals.studio.utils;

import org.eclipse.swt.graphics.FontData;

/**
 * A set of utilities for SWT and JFace.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SwtJFaceUtils {

	/**
	 * @param originalData
	 * @param additionalStyle
	 * @return
	 */
	public static FontData[] getModifiedFontData( FontData[] originalData, int additionalStyle ) {

		FontData[] styleData = new FontData[ originalData.length ];
		for( int i=0; i<styleData.length; i++ ) {
			FontData base = originalData[ i ];
			styleData[ i ] = new FontData( base.getName(), base.getHeight(), base.getStyle() | additionalStyle );
		}

		return styleData;
	}


	/**
	 * @param originalData
	 * @param height
	 * @return
	 */
	public static FontData[] changeFontDataSize( FontData[] originalData, int height ) {

		FontData[] styleData = new FontData[ originalData.length ];
		for( int i=0; i<styleData.length; i++ ) {
			FontData base = originalData[ i ];
			styleData[ i ] = new FontData( base.getName(), height, base.getStyle());
		}

		return styleData;
	}
}