/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.bpel.tests;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class BPELTestsUtils {

	public static void openBPELEditor(SWTBot bot) {
		SWTBotMenu newMenu = bot.menu("File").menu("New");
		newMenu.menu("Service Provider (SU)").click();

		bot.tree(0).expandNode( "Service Composition" ).getNode( 0 ).select();
		bot.comboBox(0).setSelection( 0 );
		bot.button("Next >").click();

		bot.text( 0 ).setText( "myBpelProcess" );
		bot.comboBox( 1 ).setText( "http://myProcess/" );
		bot.button("Next >").click();
		bot.button("Next >").click();
		bot.button("Finish").click();
	}
}
