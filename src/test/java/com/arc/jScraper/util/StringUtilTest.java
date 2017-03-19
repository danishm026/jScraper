package com.arc.jScraper.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilTest {
	@Test
	public void sanitizeTest() {
		String[] testStrings = { "hello world  there", " hello world   there  ", "hello world there ",
				" hello world    ThErE", "hello World There", "Hello world  thErE  ", "Hello World ThErE",
				"  hello   world  ThErE  ", "   hELLo    WoRlD   ThErE" };
		String expectedString = "Hello World There";

		for (int i = 0; i < testStrings.length; i++) {
			String sanitizedString = StringUtil.sanitize(testStrings[i]);
			assertEquals(expectedString, sanitizedString);
		}
	}
	
	@Test
	public void getFirstLetterAsCapsTest() {
		String testString = " asda";
		assertEquals('A', StringUtil.getFirstLetterAsCaps(testString));
		
		testString = "As";
		assertEquals('A', StringUtil.getFirstLetterAsCaps(testString));
	}
}
