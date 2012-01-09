package com.ebmwebsourcing.petals.common.tests.utils;

import junit.framework.Assert;

import org.junit.Test;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

public class TestStringUtils {

	@Test
	public void testCamelCaseToHuman() {
		Assert.assertEquals(
			"A Long Text With 1 ACRONYM And Digits 234",
			StringUtils.camelCaseToHuman("aLongTextWith1ACRONYMandDigits234"));
		Assert.assertEquals(
			"A Long Text With 1 ACRONYM And Digits 234 Lol",
			StringUtils.camelCaseToHuman("aLongTextWith1ACRONYMandDigits234lol"));
	}

}
