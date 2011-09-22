/*******************************************************************************
* Copyright (c) 2011 PetalsLink
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
* Mickael Istria, PetalsLink - initial API and implementation
*******************************************************************************/
package org.eclipse.bpel.ui.tests.extensions;

import junit.framework.Assert;

import org.eclipse.bpel.ui.tests.CommonTestUtil;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.junit.Test;

/**
 * @author Mickael Istria (PetalsLink)
 *
 */
public class TestEditPolicyExtensionPoint extends SWTBotGefTestCase {
	
	@Test
	public void testExtensionPoint() {
		DummyEditPolicy.handled = false;
		String fileName = CommonTestUtil.createNewBPELFile(bot);
		SWTBotGefEditor gefEditor = bot.gefEditor(fileName);
		gefEditor.rootEditPart().children().get(0).click();
		Assert.assertTrue(DummyEditPolicy.handled);
	}
}
