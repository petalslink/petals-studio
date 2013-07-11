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

package com.ebmwebsourcing.petals.services.bpel.tests;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;
import org.junit.Test;

import com.ebmwebsourcing.petals.tests.common.DndUtil;
import com.ebmwebsourcing.petals.tests.common.SUCreator;
import com.ebmwebsourcing.petals.tests.common.SUDesc;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class TestDND extends SWTBotGefTestCase {

	@Test
	public void testDND() throws Exception {
		SUDesc desc = SUCreator.createFileTransferEndpoint(this.bot);
		BPELTestsUtils.openBPELEditor(this.bot);

		this.bot.waitUntil( new ICondition() {

			@Override
			public boolean test() throws Exception {
				SWTBotEditor editor = TestDND.this.bot.activeEditor();
				return editor != null &&
						"myBpelProcess.bpel".equals( editor.getTitle());
			}

			@Override
			public void init( SWTBot bot ) {
				// nothing
			}

			@Override
			public String getFailureMessage() {
				return "Could not wait the BPEL editor.";
			}
		}, 5000 );

		SWTBotView servicesView = this.bot.viewByTitle("Petals Services");
		servicesView.show();
		servicesView.setFocus();
		SWTBotTreeItem root = this.bot.tree().getTreeItem("Workspace");
		root.expand();
		SWTBotTreeItem toDrag = root.getNode(desc.getEndpoint()).select();

		SWTBotGefEditor bpelEditor = this.bot.gefEditor(this.bot.activeEditor().getTitle());
		final SWTBotGefEditPart targetPart = bpelEditor.getSWTBotGefViewer().rootEditPart().children().get( 0 ).children().get( 2 );

		final Point targetLocation = new Point( 0, 0 );
		Display.getDefault().syncExec( new Runnable() {
			@Override
			public void run() {
				Point point = targetPart.part().getViewer().getControl().toDisplay( 120, 130 );
				targetLocation.x = point.x;
				targetLocation.y = point.y;
			}
		});

		try {
			new DndUtil( this.bot.activeShell().display ).dragAndDrop( toDrag, targetLocation );
			this.bot.button("OK").click();
			Assert.assertTrue(this.bot.activeEditor().isDirty());

		} catch( Exception e ) {
			new DndUtil( this.bot.activeShell().display ).dragAndDrop( toDrag, bpelEditor.getSWTBotGefViewer().rootEditPart());
			this.bot.button("OK").click();
			Assert.assertTrue(this.bot.activeEditor().isDirty());
		}

		this.bot.saveAllEditors();
		this.bot.closeAllEditors();
	}
}
