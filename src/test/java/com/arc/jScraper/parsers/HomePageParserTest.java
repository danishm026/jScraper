package com.arc.jScraper.parsers;

import java.io.IOException;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arc.jScraper.parsers.HomePageParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:homePageParserTestConfig.xml")
public class HomePageParserTest {
	@Autowired
	private HomePageParser parser;
	
	@Test
	public void getCategoryLinkTest() throws IOException{
		String expectedLink = "http://www.celebwallpaper.org/cat-a-1.htm";
		String actulaLink = parser.getCategoryLink('A');
		assertEquals(expectedLink, actulaLink);
	}
}
