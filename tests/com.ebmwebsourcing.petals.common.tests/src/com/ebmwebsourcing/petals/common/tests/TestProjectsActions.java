/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
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

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class TestProjectsActions extends SWTBotEclipseTestCase {

	@Test
	public void testConfigureBuildPath() {
		SUDesc su = SUCreator.createFileTransferEndpoint(this.bot);
		SWTBotView view = this.bot.viewById("com.ebmwebsourcing.petals.common.projects");
		view.show();
		view.setFocus();

		SWTBotTree tree = this.bot.tree(1);
		final SWTBotTreeItem item = tree.getTreeItem("Service Units").getNode(su.getProjectName());
		item.select();

		ContextMenuHelper.clickContextMenu(tree, "Petals", "Add Java Nature");
		ContextMenuHelper.clickContextMenu(tree, "Configure Build Path...");
		this.bot.shell("Properties for " + su.getProjectName());
		Assert.assertEquals("Java Build Path", this.bot.tree().selection().get(0).get(0));
		this.bot.button("Cancel").click();
	}
}
