/*******************************************************************************
* Copyright (c) 2011 PetalsLink
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
* Mickael Istria, PetalsLink - initial API and implementation
*******************************************************************************/
package org.eclipse.bpel.ui.tests;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * @author Mickael Istria (PetalsLink)
 *
 */
public class CommonTestUtil {

	protected static final String TEST_PROJECT_NAME = "testProject";
	
	/**
	 * @param bot
	 * @return
	 */
	public static String createNewBPELFile(SWTGefBot bot) {
		if (bot.activeView().getTitle().equals("Welcome")) {
			bot.activeView().close();
		}
		SWTBotMenu fileMenu = bot.menu("File");
		createTestProjectIfDoesNotExist(bot);
		fileMenu.menu("New").menu("Other...").click();
		bot.tree().getTreeItem("BPEL 2.0").expand().getNode("New BPEL Process File").select();
		bot.button("Next >").click();
		String fileName = "test" + System.currentTimeMillis();
		bot.text().setText(fileName);
		bot.comboBox().setText("testNS");
		bot.button("Next >").click();
		bot.tree().select(TEST_PROJECT_NAME);
		bot.button("Finish").click();
		return fileName + ".bpel";
	}

	/**
	 * 
	 */
	private static void createTestProjectIfDoesNotExist(final SWTGefBot bot) {
		CheckerRunnable projectExists = new CheckerRunnable() {
			
			@Override
			public boolean evaluateCondition() {
				return ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).exists();
			}
		};
		Display.getDefault().syncExec(projectExists);
		if (!projectExists.getResult()) {
			bot.menu("File").menu("New").menu("Project...").click();
			bot.tree().getTreeItem("General").expand().getNode("Project").select();
			bot.button("Next >").click();
			bot.text().setText(TEST_PROJECT_NAME);
			bot.button("Finish").click();
		}
		System.err.println("Eh oh");
		bot.waitUntil(new ICondition() {
			
			@Override
			public boolean test() throws Exception {
				SWTBotView view = bot.viewById("org.eclipse.jdt.ui.PackageExplorer");
				System.err.println("view: " + view == null);
				SWTBotTree tree = view.bot().tree();
				System.err.println("kikoo");
				for (SWTBotTreeItem item : tree.getAllItems()) {
					System.err.println("-> " + item.getText());
				}
				System.err.println("lol");
				tree.getTreeItem(TEST_PROJECT_NAME);
				return true;
			}
			
			@Override
			public void init(SWTBot bot) {
			}
			
			@Override
			public String getFailureMessage() {
				return null;
			}
		});
	}

}
