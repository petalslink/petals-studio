/******************************************************************************
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.common.wsdlext.tests;

import java.io.File;
import java.net.URL;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;
import org.junit.Test;

import com.ebmwebsourcing.petals.common.extensions.internal.provisional.WsdlExtUtils;
import com.ebmwebsourcing.petals.tests.common.FileTestUtil;

public class TestJavaToWsdl extends SWTBotGefTestCase {

	private File businessFile;
	private File jeeFile;

	public void initFiles() throws Exception {
		URL url = TestJavaToWsdl.class.getResource("/addorder.jar");
		url = FileLocator.toFileURL(url);
		this.businessFile = new File(url.getFile());
		url = TestJavaToWsdl.class.getResource("/easybeans-all-1.0.2.jar");
		url = FileLocator.toFileURL(url);
		this.jeeFile = new File(url.getFile());
	}

	@Test
	public void testFileLocked() throws Exception {
		initFiles();
		Assert.assertFalse(FileTestUtil.fileOpen(this.jeeFile));
		Assert.assertFalse(FileTestUtil.fileOpen(this.businessFile));
		String folder = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
		final File out = WsdlExtUtils.generateWsdlFile("AddOrderRemote.wsdl", folder, "org.ow2.petals.examples.ejb.addorder.AddOrderRemote", new String[] {this.businessFile.getAbsolutePath(), this.jeeFile.getAbsolutePath()}, folder, "AddOrderRemotePort", "AddOrderRemote", new NullProgressMonitor());
		this.bot.waitUntil(new ICondition() {

			@Override
			public boolean test() throws Exception {
				return out.exists();
			}

			@Override
			public void init(SWTBot bot) {
			}

			@Override
			public String getFailureMessage() {
				return null;
			}
		});
		Assert.assertNotSame(0, out.length());
		System.gc();
		Assert.assertFalse(FileTestUtil.fileOpen(this.jeeFile));
		Assert.assertFalse(FileTestUtil.fileOpen(this.businessFile));
	}

}
