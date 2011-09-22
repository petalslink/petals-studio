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
package org.eclipse.bpel.ui.tests;

import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Mickael Istria (PetalsLink)
 *
 */
public class TestOpenEditor extends SWTBotGefTestCase {

	@Test
	public void testOpenEditor() throws Exception {
		String fileName = CommonTestUtil.createNewBPELFile(bot);
		Assert.assertEquals("org.eclipse.bpel.ui.bpeleditor", bot.editorByTitle(fileName).getReference().getId());
	}
}
