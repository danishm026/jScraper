package com.arc.jScraper.parsers;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:categoryPageParserTestConfig.xml")
public class CategoryPageParserTest {
	@Autowired
	private CategoryPageParser parser;
	
	@Test
	public void getModelBasePageTest() throws IOException {
		String categoryALink = "http://www.celebwallpaper.org/cat-a-1.htm";
		parser.setUrl(categoryALink);
		String modelName = "Aaliyah";
		String expectedLink = "http://www.celebwallpaper.org/cat-aaliyah-2.htm";
		
		String actualLink = parser.getModelBasePage(modelName);
		
		assertEquals(expectedLink, actualLink);
	}
}
