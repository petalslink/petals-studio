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

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;
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
		final SWTBotGefEditPart targetPart1 = bpelEditor.getSWTBotGefViewer().rootEditPart().children().get( 0 ).children().get( 1 );
		SWTBotGefEditPart targetPart2 = bpelEditor.getSWTBotGefViewer().rootEditPart().children().get( 0 ).children().get( 2 );
		final IFigure figure1 = ((GraphicalEditPart) targetPart1.part()).getFigure();
		final IFigure figure2 = ((GraphicalEditPart) targetPart2.part()).getFigure();

		final Point targetLocation = new Point( 0, 0 );
		Display.getDefault().syncExec( new Runnable() {
			@Override
			public void run() {

				int x = figure1.getBounds().x + figure1.getBounds().width / 2;
				int y = (figure2.getBounds().y - figure1.getBounds().y - figure1.getBounds().height) / 2;
				y += figure1.getBounds().y;

				Point p = targetPart1.part().getViewer().getControl().toDisplay( x, y );
				targetLocation.x = p.x;
				targetLocation.y = p.y;
			}
		});

		new DndUtil( this.bot.activeShell().display ).dragAndDrop( toDrag, targetLocation );
		this.bot.button("OK").click();
		this.bot.waitUntil( new ICondition() {

			@Override
			public boolean test() throws Exception {
				SWTBotEditor editor = TestDND.this.bot.activeEditor();
				return editor.isDirty();
			}

			@Override
			public void init( SWTBot bot ) {
				// nothing
			}

			@Override
			public String getFailureMessage() {
				return "The BPEL editor was not marked as dirty.";
			}
		}, 8000 );

		this.bot.saveAllEditors();
		this.bot.closeAllEditors();
	}
}
