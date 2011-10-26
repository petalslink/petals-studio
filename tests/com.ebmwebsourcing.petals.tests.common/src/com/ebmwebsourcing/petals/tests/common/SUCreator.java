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

package com.ebmwebsourcing.petals.tests.common;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

public class SUCreator {
	public static SUDesc createFileTransferEndpoint(final SWTWorkbenchBot bot) {
		final SUDesc su = new SUDesc();
		final SWTBotEditor currentEditor = getCurrentEditor(bot);
		su.setServiceName("TestFileTransferService" + System.currentTimeMillis());
		bot.menu("File").menu("New").menu("Service Unit").click();
		bot.shell("New Service Unit").activate();
		bot.button("Next >"); // wait for button to appear.
		SWTBotCombo componentCombo = bot.comboBox(1);
		componentCombo.setSelection("File Transfer  //  petals-bc-filetransfer");
		bot.button("Next >").click();
		bot.ccomboBox(3).setText(su.getServiceName());
		su.setEndpoint(su.getServiceName() + "Endpoint");
		bot.text().setText(su.getEndpoint());
		bot.button("Next >").click();
		su.setProjectName(bot.text().getText());
		bot.button("Next >").click();
		bot.comboBox().setSelection(1);
		su.setWsdlName("GetFiles.wsdl");
		bot.text(0).setText("testFolder");
		bot.button("Finish").click();
		bot.waitUntil(new ICondition() {

			@Override
			public boolean test() throws Exception {
				return bot.activeEditor() != currentEditor;
			}

			@Override
			public void init(SWTBot bot) {
			}

			@Override
			public String getFailureMessage() {
				return null;
			}
		});
		SWTBotView view = bot.viewById("com.ebmwebsourcing.petals.common.projects");
		view.show();
		view.setFocus();
		final SWTBotTree tree = bot.tree(1);
		bot.waitUntil(new ICondition() {

			@Override
			public boolean test() throws Exception {
				tree.getTreeItem("Service Units").expand().getNode(su.getProjectName());
				return true;
			}

			@Override
			public void init(SWTBot bot) {
			}

			@Override
			public String getFailureMessage() {
				return "SU not accessible in Petals Navigator view";
			}

		});
		return su;
	}

	private static SWTBotEditor getCurrentEditor(SWTWorkbenchBot bot) {
		try {
			return bot.activeEditor();
		} catch (WidgetNotFoundException ex) {
			return null;
		}
	}

}
