package com.ebmwebsourcing.petals.services.bpel.tests;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;
import org.junit.Test;

import com.ebmwebsourcing.petals.tests.common.DndUtil;
import com.ebmwebsourcing.petals.tests.common.SUCreator;
import com.ebmwebsourcing.petals.tests.common.SUDesc;

public class TestDND extends SWTBotGefTestCase {

	@Test
	public void testDND() throws Exception {
		SUDesc desc = SUCreator.createFileTransferEndpoint(bot);
		BPELTestsUtils.openBPELEditor(bot);
		
		SWTBotView servicesView = bot.viewByTitle("Petals Services");
		servicesView.show();
		servicesView.setFocus();
		SWTBotTreeItem root = bot.tree().getTreeItem("Workspace");
		root.expand();
		SWTBotTreeItem toDrag = root.getNode(desc.getEndpoint()).select();
		
		SWTBotGefEditor eipEditor = bot.gefEditor(bot.activeEditor().getTitle());
		new DndUtil(bot.activeShell().display).dragAndDrop(toDrag, eipEditor.getSWTBotGefViewer().rootEditPart());
		
		bot.button("OK").click();
		Assert.assertTrue(bot.activeEditor().isDirty());
		
		bot.saveAllEditors();
		bot.closeAllEditors();
	}

}
