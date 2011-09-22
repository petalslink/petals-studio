package com.ebmwebsourcing.petals.common.tests;


import junit.framework.Assert;

import org.eclipse.swtbot.eclipse.finder.SWTBotEclipseTestCase;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Test;

import com.ebmwebsourcing.petals.tests.common.ContextMenuHelper;
import com.ebmwebsourcing.petals.tests.common.SUCreator;
import com.ebmwebsourcing.petals.tests.common.SUDesc;

public class TestProjectsActions extends SWTBotEclipseTestCase {

	@Test
	public void testConfigureBuildPath() {
		SUDesc su = SUCreator.createFileTransferEndpoint(bot);
		SWTBotView view = bot.viewById("com.ebmwebsourcing.petals.common.projects");
		view.show();
		view.setFocus();
		SWTBotTree tree = bot.tree(1);
		final SWTBotTreeItem item = tree.getTreeItem("Service Units").getNode(su.getProjectName());
		item.select();
		ContextMenuHelper.clickContextMenu(tree, "Petals", "Add Java Nature");
		ContextMenuHelper.clickContextMenu(tree, "Configure Build Path...");
		bot.shell("Properties for " + su.getProjectName());
		Assert.assertEquals("Java Build Path", bot.tree().selection().get(0).get(0));
		bot.button("Cancel").click();
		bot.activeEditor().close();
	}

}
