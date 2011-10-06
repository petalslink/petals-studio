/******************************************************************************
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.bpel.tests;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;

public class BPELTestsUtils {

	public static void openBPELEditor(SWTBot bot) {
		SWTBotMenu newMenu = bot.menu("File").menu("New");
		newMenu.menu("Service Unit").click();
		bot.comboBox(0).setSelection("Service Composition");
		bot.button("Next >").click();
		bot.button("Next >").click();
		bot.text().setText("su-BPEL-Test" + System.currentTimeMillis() + "-provides");
		bot.button("Finish").click();
	}

}
