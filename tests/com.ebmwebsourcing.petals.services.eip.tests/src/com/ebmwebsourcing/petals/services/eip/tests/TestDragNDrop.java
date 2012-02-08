/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

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
 * @author Mickael Istria - EBM WebSourcing
 */
public class TestDragNDrop extends SWTBotGefTestCase {

	@Test
	public void testDND_bug138() throws Exception {
		SUDesc suDesc = SUCreator.createFileTransferEndpoint(this.bot);
		SWTBotView servicesView = this.bot.viewByTitle("Petals Services");
		servicesView.show();
		servicesView.setFocus();
		SWTBotTreeItem root = this.bot.tree().getTreeItem("Workspace");
		root.expand();
		SWTBotTreeItem toDrag = root.getNode(suDesc.getEndpoint()).select();

		SWTBotMenu newMenu = this.bot.menu("File").menu("New");
		newMenu.menu("Croquis").click();
		this.bot.text(0).setText("TestEIPFile");
		this.bot.text(1).setText("TestEIPChain");
		this.bot.button("Finish").click();

		SWTBotGefEditor eipEditor = this.bot.gefEditor(this.bot.activeEditor().getTitle());
		new DndUtil(this.bot.activeShell().display).dragAndDrop(toDrag, eipEditor.getSWTBotGefViewer().rootEditPart());

		Assert.assertNotSame("No edit part created", 0, eipEditor.getEditPart(suDesc.getEndpoint().replace("Endpoint", "Service")));

		this.bot.saveAllEditors();
		this.bot.closeAllEditors();
	}

}
