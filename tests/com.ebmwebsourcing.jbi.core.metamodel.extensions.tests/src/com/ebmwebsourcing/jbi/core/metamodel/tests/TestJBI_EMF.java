package com.ebmwebsourcing.jbi.core.metamodel.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

import junit.framework.Assert;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Test;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.jbi.editor.form.extensibility.InitializeModelExtensionCommand;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.CopyMode;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.TransferMode;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.DocumentRoot;
import com.sun.java.xml.ns.jbi.Provides;

public class TestJBI_EMF {

	@Test
	public void testCanReadAndSetCDK() throws Exception {
		ResourceSet set = new ResourceSetImpl();
		Resource resource = set.createResource(URI.createURI(getClass().getResource("jbi-cdk5.xml").toString()));
		resource.load(set.getLoadOptions());
		DocumentRoot root = (DocumentRoot) resource.getContents().get(0);
		Provides provide = root.getJbi().getServices().getProvides().get(0);
		
		InitializeModelExtensionCommand command = new InitializeModelExtensionCommand(Cdk5Package.eINSTANCE, provide);
		command.prepare();
		command.execute();
		Assert.assertEquals("AddOrderRemote.wsdl", provide.eGet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL));
		provide.eSet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL, "testURL");
		Assert.assertEquals("testURL", provide.eGet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL));

		assertJbiFileContainsLine(set, root, "<petalsCDK:wsdl>testURL</petalsCDK:wsdl>");
	}
	
	@Test
	public void testCanReadAndSetFileTransferProvides() throws Exception {
		ResourceSet set = new ResourceSetImpl();
		Resource resource = set.createResource(URI.createURI(getClass().getResource("jbi-filetransfer-provides.xml").toString()));
		resource.load(set.getLoadOptions());
		DocumentRoot root = (DocumentRoot) resource.getContents().get(0);
		Provides provide = root.getJbi().getServices().getProvides().get(0);
		
		InitializeModelExtensionCommand command = new InitializeModelExtensionCommand(FileTransferPackage.eINSTANCE, provide);
		command.prepare();
		command.execute();
		Assert.assertEquals(CopyMode.CONTENT_AND_ATTACHMENTS, provide.eGet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE));
		provide.eSet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE, CopyMode.CONTENT_ONLY);
		Assert.assertEquals(CopyMode.CONTENT_ONLY, provide.eGet(FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES__COPY_MODE));

		
		String expectedLine = "<filetransfer:copy-mode>content-only</filetransfer:copy-mode>";
		assertJbiFileContainsLine(set, root, expectedLine);
	}
	
	@Test
	public void testCanReadAndSetFileTransferConsumes() throws Exception {
		ResourceSet set = new ResourceSetImpl();
		Resource resource = set.createResource(URI.createURI(getClass().getResource("jbi-filetransfer-consumes.xml").toString()));
		resource.load(set.getLoadOptions());
		DocumentRoot root = (DocumentRoot) resource.getContents().get(0);
		Consumes consume = root.getJbi().getServices().getConsumes().get(0);
		
		InitializeModelExtensionCommand command = new InitializeModelExtensionCommand(FileTransferPackage.eINSTANCE, consume);
		command.prepare();
		command.execute();
		Assert.assertEquals(TransferMode.CONTENT, consume.eGet(FileTransferPackage.Literals.FILE_TRANSFER_CONSUMES__TRANSFER_MODE));
		consume.eSet(FileTransferPackage.Literals.FILE_TRANSFER_CONSUMES__TRANSFER_MODE, TransferMode.ATTACHMENTS);
		Assert.assertEquals(TransferMode.ATTACHMENTS, consume.eGet(FileTransferPackage.Literals.FILE_TRANSFER_CONSUMES__TRANSFER_MODE));

		
		String expectedLine = "<filetransfer:transfer-mode>attachments</filetransfer:transfer-mode>";
		assertJbiFileContainsLine(set, root, expectedLine);
	}

	
	
	
	private void assertJbiFileContainsLine(ResourceSet set, DocumentRoot root, String expectedLine) throws IOException, FileNotFoundException {
		File tmpFile = File.createTempFile("jbi", ".xml");
		tmpFile.deleteOnExit();
		Resource outResource = set.createResource(URI.createFileURI(tmpFile.getAbsolutePath()));
		outResource.getContents().add(root);
		outResource.save(Collections.EMPTY_MAP);
		
		BufferedReader reader = new BufferedReader(new FileReader(tmpFile));
		boolean found = false;
		String line = null;
		while (!found && (line = reader.readLine()) != null) {
			found = line.contains(expectedLine);
		}
		reader.close();
		Assert.assertTrue(found);
	}

}
