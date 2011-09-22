package com.ebmwebsourcing.petals.common.tests;

import org.eclipse.swtbot.eclipse.finder.SWTBotEclipseTestCase;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;
import org.junit.Test;

import com.ebmwebsourcing.petals.tests.common.SUCreator;
import com.ebmwebsourcing.petals.tests.common.SUDesc;

public class TestValidateXML extends SWTBotEclipseTestCase {

	@Test
	public void testValidateXML() throws Exception {
		final SUDesc su = SUCreator.createFileTransferEndpoint(bot);
		SWTBotView view = bot.viewById("com.ebmwebsourcing.petals.common.projects");
		view.show();
		view.setFocus();
		final SWTBotTree tree = bot.tree(1);
		SWTBotTreeItem item = tree.getTreeItem("Service Units").expand().getNode(su.getProjectName()).expand().getNode("src").expand().getNode("main").expand().getNode("jbi").expand().getNode(su.getWsdlName());
		item.click();
		SWTBotMenu menu = item.contextMenu("Validate");
		Assert.assertNotNull(menu);
		Assert.assertTrue(menu.isVisible());
		Assert.assertTrue(menu.isEnabled());
	}

}
