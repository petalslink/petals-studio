/**
 *  Copyright (c) 2011, PetalsLink
 *  
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *  
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 */
package com.ebmwebsourcing.petals.services.eip.tests;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;
import org.junit.Test;

import com.ebmwebsourcing.petals.tests.common.DndUtil;
import com.ebmwebsourcing.petals.tests.common.SUCreator;
import com.ebmwebsourcing.petals.tests.common.SUDesc;

/**
 * 
 * @author Mickael Istria (PetalsLink)
 *
 */
public class TestDragNDrop extends SWTBotGefTestCase {

	@Test
	public void testDND_bug138() throws Exception {
		SUDesc suDesc = SUCreator.createFileTransferEndpoint(bot);
		
		SWTBotView servicesView = bot.viewByTitle("Petals Services");
		servicesView.show();
		servicesView.setFocus();
		SWTBotTreeItem root = bot.tree().getTreeItem("Workspace");
		root.expand();
		SWTBotTreeItem toDrag = root.getNode(suDesc.getEndpoint()).select();
		
		
		SWTBotMenu newMenu = bot.menu("File").menu("New");
		newMenu.menu("Croquis").click();
		bot.text(0).setText("TestEIPFile");
		bot.text(1).setText("TestEIPChain");
		bot.button("Finish").click();
		
		SWTBotGefEditor eipEditor = bot.gefEditor(bot.activeEditor().getTitle());
		new DndUtil(bot.activeShell().display).dragAndDrop(toDrag, eipEditor.getSWTBotGefViewer().rootEditPart());
		
		Assert.assertNotSame("No edit part created", 0, eipEditor.getEditPart(suDesc.getEndpoint().replace("Endpoint", "Service")));
		
		bot.saveAllEditors();
		bot.closeAllEditors();
	}

}
