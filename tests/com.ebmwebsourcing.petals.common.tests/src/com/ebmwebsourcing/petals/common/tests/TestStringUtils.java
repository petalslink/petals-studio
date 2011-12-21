package com.ebmwebsourcing.petals.common.tests;

import junit.framework.Assert;

import org.junit.Test;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

public class TestStringUtils {

	@Test
	public void testCamelCaseToHuman() {
		Assert.assertEquals(
			"A long text with 1 ACRONYM and digits 234",
			StringUtils.camelCaseToHuman("aLongTextWith1ACRONYMandDigits234"));
		Assert.assertEquals(
			"A long text with 1 ACRONYM and digits 234 lol",
			StringUtils.camelCaseToHuman("aLongTextWith1ACRONYMandDigits234lol"));
	}

}
