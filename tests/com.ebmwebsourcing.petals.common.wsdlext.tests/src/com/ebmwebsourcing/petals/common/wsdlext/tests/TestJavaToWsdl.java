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

/**
 * @author Mickael Istria - EBM WebSourcing
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class TestJavaToWsdl extends SWTBotGefTestCase {

	private File businessFile;
	private File jeeFile;


	/**
	 * Initializes the files for the Java to WSDL operation.
	 * @param ejbJarName
	 * @throws Exception
	 */
	public void initFiles( String ejbJarName ) throws Exception {
		URL url = TestJavaToWsdl.class.getResource( "/" + ejbJarName );
		url = FileLocator.toFileURL(url);
		this.businessFile = new File(url.getFile());
		url = TestJavaToWsdl.class.getResource("/easybeans-all-1.0.2.jar");
		url = FileLocator.toFileURL(url);
		this.jeeFile = new File(url.getFile());
	}


	/**
	 * Tests whether the Java to WSDL operation locks the EJB's JAR file.
	 * @throws Exception
	 */
	@Test
	public void testFileLocked() throws Exception {
		initFiles( "addorder.jar" );

		Assert.assertFalse(FileTestUtil.fileOpen(this.jeeFile));
		Assert.assertFalse(FileTestUtil.fileOpen(this.businessFile));
		String folder = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
		final File out = WsdlExtUtils.generateWsdlFile(
				"AddOrderRemote.wsdl",
				folder,
				"org.ow2.petals.examples.ejb.addorder.AddOrderRemote",
				new String[] { this.businessFile.getAbsolutePath(), this.jeeFile.getAbsolutePath()},
				folder,
				"AddOrderRemotePort",
				"AddOrderRemote",
				new NullProgressMonitor());

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


	/**
	 * Tests whether the Java to WSDL operation locks the EJB's JAR file.
	 * @throws Exception
	 */
//	@Test
//	@Ignore
//	public void testEjbWithDifferentSchemas() throws Exception {
//		initFiles( "EjbWithDifferentSchemas.jar" );
//
//		Assert.assertFalse(FileTestUtil.fileOpen(this.jeeFile));
//		Assert.assertFalse(FileTestUtil.fileOpen(this.businessFile));
//		String folder = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
//		final File out = WsdlExtUtils.generateWsdlFile(
//				"AddOrderRemote.wsdl",
//				folder,
//				"org.ow2.petals.examples.ejb.addorder.AddOrderRemote",
//				new String[] { this.businessFile.getAbsolutePath(), this.jeeFile.getAbsolutePath()},
//				folder,
//				"AddOrderRemotePort",
//				"AddOrderRemote",
//				new NullProgressMonitor());
//
//		this.bot.waitUntil(new ICondition() {
//
//			@Override
//			public boolean test() throws Exception {
//				return out.exists();
//			}
//
//			@Override
//			public void init(SWTBot bot) {
//			}
//
//			@Override
//			public String getFailureMessage() {
//				return null;
//			}
//		});
//
//		Assert.assertNotSame(0, out.length());
//		System.gc();
//
//		// Parse the resulting WSDL.
//		// Search for all the XSD import made in WSDL files.
//		// Recursive XSD imports are not supported.
//		Collection<Definition> definitions =
//				WsdlParser.loadAllWsdlDefinitions( URI.createFileURI( out.getAbsolutePath()), WsdlParser.createBasicResourceSetForWsdl());
//
//		Set<String> schemaNamespaces = new HashSet<String> ();
//		for( Definition def : definitions ) {
//			for( Object o : def.getETypes().getSchemas()) {
//				if( !( o instanceof XSDSchema ))
//					continue;
//
//				// The current schema
//				String ns = ((XSDSchema) o).getTargetNamespace();
//				if( ns != null )
//					schemaNamespaces.add( ns );
//
//				// Process the import declarations
//				for( XSDSchemaContent content : ((XSDSchema) o).getContents()) {
//					if( !( content instanceof XSDImport ))
//						continue;
//
//					ns = ((XSDImport) content).getNamespace();
//					if( ns != null )
//						schemaNamespaces.add( ns );
//				}
//			}
//		}
//
//		Assert.assertEquals( 3, schemaNamespaces.size());
//	}
}
