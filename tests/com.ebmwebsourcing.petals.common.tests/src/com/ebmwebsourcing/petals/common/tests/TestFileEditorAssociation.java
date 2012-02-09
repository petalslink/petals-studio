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

package com.ebmwebsourcing.petals.common.tests;

import org.eclipse.swtbot.eclipse.finder.SWTBotEclipseTestCase;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class TestFileEditorAssociation extends SWTBotEclipseTestCase {

	// @Test: disabled
	// See PETALSSTUD-222
//	public void testWSDLEditor() throws Exception {
//		SUDesc su = SUCreator.createFileTransferEndpoint(this.bot);
//		SWTBotView view = this.bot.viewById("com.ebmwebsourcing.petals.common.projects");
//		view.show();
//		view.setFocus();
//		SWTBotTree tree = this.bot.tree(1);
//		SWTBotTreeItem item = tree.getTreeItem("Service Units").getNode(su.getProjectName()).getNode("src").getNode("main").getNode("jbi").getNode(su.getWsdlName());
//		item.doubleClick();
//		Assert.assertEquals(ServiceInterfaceEditor.EDITOR_ID, this.bot.editorByTitle(su.getWsdlName()).getReference().getId());
//	}


	// @Test: disabled
	// See PETALSSTUD-222
//	public void testNewWSDLFile() throws Exception {
//		this.bot.menu("New").menu("WSDL File").click();
//		this.bot.shell("New WSDL File").activate();
//		this.bot.shell("New WSDL File").setFocus();
//		this.bot.tree().select("Petals-Croquis");
//		String fileName = "test" + System.currentTimeMillis() + ".wsdl";
//		this.bot.text(1).setText(fileName);
//		this.bot.button("Next >").click();
//		this.bot.button("Finish").click();
//		Assert.assertEquals(ServiceInterfaceEditor.EDITOR_ID, this.bot.editorByTitle(fileName).getReference().getId());
//	}


	// @Test: disabled
	// See PETALSSTUD-222
//	public void testXSDEditor() throws Exception {
//		this.bot.menu("New").menu("File").click();
//		this.bot.shell("New File").activate();
//		this.bot.shell("New File").setFocus();
//		this.bot.tree().select("Petals-Croquis");
//		String fileName = "test" + System.currentTimeMillis() + ".xsd";
//		this.bot.text(1).setText(fileName);
//		this.bot.button("Finish").click();
//		Assert.assertEquals(DataTypesEditor.EDITOR_ID, this.bot.editorByTitle(fileName).getReference().getId());
//	}
}
