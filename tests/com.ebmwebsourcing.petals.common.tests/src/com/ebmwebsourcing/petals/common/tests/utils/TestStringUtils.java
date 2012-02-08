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

import junit.framework.Assert;

import org.junit.Test;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class TestStringUtils {

	@Test
	public void testCamelCaseToHuman() {

		String originalCase = "aLongTextWith1ACRONYMandDigits234";
		String camelCase = "A long text with 1 ACRONYM and digits 234";
		Assert.assertEquals( camelCase, StringUtils.camelCaseToHuman( originalCase ));

		String capitalizedCamelCase = "A Long Text With 1 Acronym And Digits 234";
		Assert.assertEquals( capitalizedCamelCase, StringUtils.capitalize( camelCase ));

		Assert.assertEquals(
			"A long text with 1 ACRONYM and digits 234 lol",
			StringUtils.camelCaseToHuman("aLongTextWith1ACRONYMandDigits234lol"));
	}

}
