package com.arc.jScraper.parsers;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class CategoryPageParserTest {
	@Test
	public void getModelBasePageTest() throws IOException {
		CategoryPageParser parser = new CategoryPageParser();
		
		String categoryALink = "http://www.celebwallpaper.org/cat-a-1.htm";
		parser.setUrl(categoryALink);
		String modelName = "Aaliyah";
		String expectedLink = "http://www.celebwallpaper.org/cat-aaliyah-2.htm";
		
		String actualLink = parser.getModelBasePage(modelName);
		
		assertEquals(expectedLink, actualLink);
	}
}
