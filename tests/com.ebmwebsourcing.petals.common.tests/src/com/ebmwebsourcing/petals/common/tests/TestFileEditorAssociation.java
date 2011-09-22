package com.ebmwebsourcing.petals.common.tests;

import org.eclipse.swtbot.eclipse.finder.SWTBotEclipseTestCase;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.wst.sse.sieditor.ui.DataTypesEditor;
import org.eclipse.wst.sse.sieditor.ui.ServiceInterfaceEditor;
import org.junit.Assert;
import org.junit.Test;

import com.ebmwebsourcing.petals.tests.common.SUCreator;
import com.ebmwebsourcing.petals.tests.common.SUDesc;

public class TestFileEditorAssociation extends SWTBotEclipseTestCase {

	@Test
	public void testWSDLEditor() throws Exception {
		SUDesc su = SUCreator.createFileTransferEndpoint(bot);
		SWTBotView view = bot.viewById("com.ebmwebsourcing.petals.common.projects");
		view.show();
		view.setFocus();
		SWTBotTree tree = bot.tree(1);
		SWTBotTreeItem item = tree.getTreeItem("Service Units").getNode(su.getProjectName()).getNode("src").getNode("main").getNode("jbi").getNode(su.getWsdlName());
		item.doubleClick();
		Assert.assertEquals(ServiceInterfaceEditor.EDITOR_ID, bot.editorByTitle(su.getWsdlName()).getReference().getId());
	}
	
	@Test
	public void testNewWSDLFile() throws Exception {
		bot.menu("New").menu("WSDL File").click();
		bot.tree().select("Petals-Croquis");
		String fileName = "test" + System.currentTimeMillis() + ".wsdl";
		bot.text(1).setText(fileName);
		bot.button("Next >").click();
		bot.button("Finish").click();
		Assert.assertEquals(ServiceInterfaceEditor.EDITOR_ID, bot.editorByTitle(fileName).getReference().getId());
	}

	@Test
	public void testXSDEditor() throws Exception {
		bot.menu("New").menu("File").click();
		bot.tree().select("Petals-Croquis");
		String fileName = "test" + System.currentTimeMillis() + ".xsd";
		bot.text(1).setText(fileName);
		bot.button("Finish").click();
		Assert.assertEquals(DataTypesEditor.EDITOR_ID, bot.editorByTitle(fileName).getReference().getId());
	}
	
}
