/******************************************************************************
 * Copyright (c) 2016, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.tests.common;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefFigureCanvas;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefViewer;

/**
 * 
 * @author vnoel
 *
 */
public class SWTBotGefUtil {

	public static SWTBotGefFigureCanvas getCanvas(SWTBotGefViewer viewer) {
		SWTBotGefFigureCanvas canvas = null;
		for (Field f : viewer.getClass().getDeclaredFields()) {
			if ("canvas".equals(f.getName())) {
				f.setAccessible(true);
				try {
					canvas = (SWTBotGefFigureCanvas) f.get(viewer);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		assertNotNull(canvas);
		return canvas;
	}
}
