/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
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
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;
import org.junit.Test;

import com.ebmwebsourcing.petals.tests.common.SUCreator;
import com.ebmwebsourcing.petals.tests.common.SUDesc;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class TestValidateXML extends SWTBotEclipseTestCase {

	@Test
	public void testValidateXML() throws Exception {

		final SUDesc su = SUCreator.createFileTransferEndpoint(this.bot);
		SWTBotView view = this.bot.viewById("com.ebmwebsourcing.petals.common.projects");
		view.show();
		view.setFocus();

		final SWTBotTree tree = this.bot.tree(1);
		SWTBotTreeItem item = tree.getTreeItem("Service Units").expand()
				.getNode(su.getProjectName()).expand()
				.getNode("src").expand()
				.getNode("main").expand()
				.getNode("jbi").expand()
				.getNode(su.getWsdlName());

		item.click();
		SWTBotMenu menu = item.contextMenu("Validate");
		Assert.assertNotNull(menu);
		Assert.assertTrue(menu.isVisible());
		Assert.assertTrue(menu.isEnabled());
	}
}
