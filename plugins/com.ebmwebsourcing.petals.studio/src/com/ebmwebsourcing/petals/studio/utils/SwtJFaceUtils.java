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
