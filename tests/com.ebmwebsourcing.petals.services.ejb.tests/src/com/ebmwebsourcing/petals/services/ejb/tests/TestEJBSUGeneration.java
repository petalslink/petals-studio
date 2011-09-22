package com.ebmwebsourcing.petals.services.ejb.tests;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;
import org.junit.Test;

import com.ebmwebsourcing.petals.services.ejb.v12.EJBCustomSpecificationPage12;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.swt.FileList;
import com.ebmwebsourcing.petals.tests.common.FileTestUtil;

public class TestEJBSUGeneration extends SWTBotGefTestCase {

	
	protected File businessFile;
	protected File jeeFile;

	public void initFiles() throws Exception {
		URL url = TestEJBSUGeneration.class.getResource("addorder.jar");
		url = FileLocator.toFileURL(url);
		businessFile = new File(url.getFile());
		url = TestEJBSUGeneration.class.getResource("easybeans-all-1.0.2.jar");
		url = FileLocator.toFileURL(url);
		jeeFile = new File(url.getFile());
	}
	
	@Test
	public void testFileLocked() throws Exception {
		initFiles();
		bot.menu("New").menu("Service Unit").click();
		bot.comboBox(1).setSelection("EJB  //  petals-bc-ejb");
		bot.button("Next >").click();
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				try {
					WizardDialog wizardDialog = (WizardDialog)bot.activeShell().widget.getData();
					EJBCustomSpecificationPage12 page = (EJBCustomSpecificationPage12)wizardDialog.getCurrentPage();
					{
						FileList list = page.getEJBFiles();
						List<File> files = new ArrayList<File>();
						files.add(businessFile);
						list.addFiles(files);
					}
					{
						FileList list = page.getJEEFiles();
						List<File> files = new ArrayList<File>();
						files.add(jeeFile);
						list.addFiles(files);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			}
		});
		bot.text().setText("org.ow2.petals.examples.ejb.addorder.AddOrderRemote");
		bot.button("Next >").click();
		final String suName = "ejbSu " + System.currentTimeMillis();
		bot.text().setText(suName);
		bot.button("Next >").click();
		bot.text(0).setText("jndiName");
		bot.text(1).setText("jndiFactory");
		bot.text(3).setText("jndiUrl");
		bot.comboBox().setSelection(0);
		bot.button("Next >").click();
		Assert.assertFalse(FileTestUtil.fileOpen(jeeFile));
		Assert.assertFalse(FileTestUtil.fileOpen(businessFile));
		bot.button("Finish").click();

		bot.waitUntil(new ICondition() {
			
			@Override
			public boolean test() throws Exception {
				return bot.activeEditor().getTitle().equals(suName);
			}
			
			@Override
			public void init(SWTBot bot) {
			}
			
			@Override
			public String getFailureMessage() {
				return null;
			}
		});
		
		Assert.assertFalse(FileTestUtil.fileOpen(jeeFile));
		Assert.assertFalse(FileTestUtil.fileOpen(businessFile));
	}



}
