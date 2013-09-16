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
 
package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class TestJaxWsUtils {

	private static final String EMPTY_BODY = "\\s*\\{[^}]*\\}";
	private static final String RETURN_BODY = "\\s*\\{\\s*return\\s+[^;]*;\\s*\\}";


	@Test
	public void testReplaceInterfaceMethodsByImpl() {
		Matcher m = matchOutput( "public void test();", EMPTY_BODY );
		Assert.assertTrue( m.matches());

		m = matchOutput( "public String test();", RETURN_BODY );
		Assert.assertTrue( m.matches());

		m = matchOutput( "public String test(\n\n);", RETURN_BODY );
		Assert.assertTrue( m.matches());

		m = matchOutput( "public void test()\n\n;", EMPTY_BODY );
		Assert.assertTrue( m.matches());

		m = matchOutput( "public void test( String t );", EMPTY_BODY );
		Assert.assertTrue( m.matches());

		m = matchOutput( "public void test( String t ) throws LolException ;", EMPTY_BODY );
		Assert.assertTrue( m.matches());

		m = matchOutput( "public void test() throws LolException ;", EMPTY_BODY );
		Assert.assertTrue( m.matches());

		m = matchOutput( "public void test(\n\t\t" +
		        ");", EMPTY_BODY );
		Assert.assertTrue( m.matches());

		m = matchOutput( "public void test(\n\t\t" +
		        "String t);", EMPTY_BODY );
		Assert.assertTrue( m.matches());

		m = matchOutput( "public void test(\n\t\t" +
		        "String t)\n\t;", EMPTY_BODY );
		Assert.assertTrue( m.matches());

		m = matchOutput( "public void test(\n\t\t" +
		        "Holder<List<RowType>> row)\n\t\t" +
		        ";", EMPTY_BODY );
		Assert.assertTrue( m.matches());

		m = matchOutput( "public void filter(\n\t\t" +
		        "@WebParam(name = \"row\", targetNamespace = \"\", mode = WebParam.Mode.INOUT)\n\t\t" +
		        "Holder<List<RowType>> row)\n\t\t" +
		        ";", EMPTY_BODY );
		Assert.assertTrue( m.matches());

		m = matchOutput( "public void filter(\n\t\t" +
				"@WebParam(name = \"row\", targetNamespace = \"\", mode = WebParam.Mode.INOUT)\n\t\t" +
				"Holder<List<RowType>> row)\n\t\t" +
				"throws FilterFault\n\t\t" +
				";", EMPTY_BODY );
		Assert.assertTrue( m.matches());

		m = matchOutput( "public boolean filter(\n\t\t" +
				"@WebParam(name = \"row\", targetNamespace = \"\", mode = WebParam.Mode.INOUT)\n\t\t" +
				"Holder<List<RowType>> row)\n\t\t" +
				"throws FilterFault\n\t\t" +
				";", RETURN_BODY );
		Assert.assertTrue( m.matches());
	}


	/**
	 * Prepares a matcher to check if the output is correct.
	 * @param input
	 * @param bodyPattern
	 * @return a matcher
	 */
	private Matcher matchOutput( String input, String bodyPattern ) {
		String output = JaxWsUtils.replaceInterfaceMethodsByImpl( new StringBuffer( input )).trim();
		String initPattern = Pattern.quote( input.substring( 0, input.length() - 1 ).trim());
		return smartPattern( initPattern + bodyPattern ).matcher( output );
	}


	/**
	 * @param pattern
	 * @return
	 */
	private Pattern smartPattern( String pattern ) {
		return Pattern.compile( pattern, Pattern.MULTILINE | Pattern.DOTALL );
	}
}
