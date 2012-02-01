/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.common.tests.utils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.JbiBasicBean;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.SoapVersion;

/**
 * Tests the WSDL utilities (parsing and update).
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class TestWsdlUtils extends TestCase {

	/**
	 * Tests a simple WSDL (one service, one port type, one file).
	 * <p>
	 * The WSDL has one SOAP binding, version 1.1.
	 * </p>
	 *
	 * @throws Exception
	 */
	@Test
	public void testWsdlParsing1() throws Exception {

		URL url = getClass().getResource( "/wsdl/tuxDroid.wsdl" );
		List<JbiBasicBean> beans = WsdlUtils.INSTANCE.parse( url.toString());
		Assert.assertTrue( beans.size() == 1 );

		JbiBasicBean bean = beans.get( 0 );
		Assert.assertEquals( bean.getInterfaceName(), "TuxDroidPortType" );
		Assert.assertEquals( bean.getServiceName(), "TuxDroid" );
		Assert.assertEquals( bean.getEndpointName(), "TuxDroidPort" );
		Assert.assertTrue( bean.getSoapVersion() == SoapVersion.v11 );
		Assert.assertTrue( bean.getOperations().size() == 11 );
	}


	/**
	 * Tests a simple WSDL (one service, one port type, one file).
	 * <p>
	 * The WSDL has one SOAP binding, version 1.2.
	 * </p>
	 *
	 * @throws Exception
	 */
	@Test
	public void testWsdlParsing2() throws Exception {

		URL url = getClass().getResource( "/wsdl/hello_soap12.wsdl" );
		List<JbiBasicBean> beans = WsdlUtils.INSTANCE.parse( url.toString());
		Assert.assertTrue( beans.size() == 1 );

		JbiBasicBean bean = beans.get( 0 );
		Assert.assertEquals( bean.getInterfaceName(), "ExamplePort" );
		Assert.assertEquals( bean.getServiceName(), "ExampleService" );
		Assert.assertEquals( bean.getEndpointName(), "ExamplePort" );
		Assert.assertTrue( bean.getSoapVersion() == SoapVersion.v12 );
		Assert.assertTrue( bean.getOperations().size() == 1 );
	}


	/**
	 * Tests a more complex WSDL (one service, one port type, two ports, one file).
	 * <p>
	 * The WSDL has one SOAP binding version 1.1 and one with version 1.2.
	 * </p>
	 *
	 * @throws Exception
	 */
	@Test
	public void testWsdlParsing3() throws Exception {

		URL url = getClass().getResource( "/wsdl/tuxDroid_WithTwoPorts.wsdl" );
		List<JbiBasicBean> beans = WsdlUtils.INSTANCE.parse( url.toString());
		Assert.assertEquals(2, beans.size());

		for( JbiBasicBean bean : beans ) {
			Assert.assertEquals("TuxDroidPortType", bean.getInterfaceName());
			Assert.assertEquals("TuxDroid", bean.getServiceName());
			Assert.assertEquals(11, bean.getOperations().size());

			if( bean.getSoapVersion() == SoapVersion.v11 ) {
				Assert.assertEquals( bean.getSoapAddress(), "http://localhost:9090/TuxDroidPort" );
				Assert.assertEquals("TuxDroidPort", bean.getEndpointName());
			} else {
				Assert.assertEquals( bean.getSoapAddress(), "http://www.example.org/" );
				Assert.assertEquals("TuxDroidPort_Soap12", bean.getEndpointName());
			}
		}
	}


	/**
	 * Tests the end-point update in a WSDL.
	 * @throws Exception
	 */
	@Test
	public void testWsdlUpdate1() throws Exception {

		// Copy the WSDL
		URL url = getClass().getResource( "/wsdl/tuxDroid.wsdl" );
		InputStream in =  url.openStream();
		File tempFile = File.createTempFile( "petals_test_", ".wsdl" );
		IoUtils.copyStream( in, tempFile );
		in.close();

		// Update the WSDL
		WsdlUtils.INSTANCE.updateEndpointNameInWsdl( tempFile, new QName( "http://tuxdroid.ebmwebsourcing.com/", "TuxDroid" ), "paf" );

		// Check the update
		List<JbiBasicBean> beans = WsdlUtils.INSTANCE.parse( tempFile.toURI());
		Assert.assertTrue( beans.size() == 1 );
		Assert.assertEquals( beans.get( 0 ).getEndpointName(), "paf" );

		// Delete the temporary WSDL
		IoUtils.deleteFilesRecursively( tempFile );
	}


	/**
	 * Tests the service and end-point update in a WSDL.
	 * @throws Exception
	 */
	@Test
	public void testWsdlUpdate2() throws Exception {

		// Copy the WSDL
		URL url = getClass().getResource( "/wsdl/tuxDroid.wsdl" );
		InputStream in =  url.openStream();
		File tempFile = File.createTempFile( "petals_test_", ".wsdl" );
		IoUtils.copyStream( in, tempFile );
		in.close();

		// Update the WSDL
		WsdlUtils.INSTANCE.updateEndpointAndServiceNamesInWsdl(
				tempFile,
				new QName( "http://tuxdroid.ebmwebsourcing.com/", "TuxDroid" ),
				new QName( "http://tuxdroid.ebmwebsourcing.com/", "TuxDroid-renamed" ),
				"paf" );

		// Check the update
		List<JbiBasicBean> beans = WsdlUtils.INSTANCE.parse( tempFile.toURI());
		Assert.assertTrue( beans.size() == 1 );
		Assert.assertEquals( beans.get( 0 ).getServiceName(), "TuxDroid-renamed" );
		Assert.assertEquals( beans.get( 0 ).getEndpointName(), "paf" );

		// Delete the temporary WSDL
		IoUtils.deleteFilesRecursively( tempFile );
	}
}
