package jScraper;

import java.io.IOException;

import static org.junit.Assert.*;

import org.jsoup.nodes.Document;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arc.jScraper.parsers.HomePageParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:homePageParserTestConfig.xml")
public class HomePageParserTest {
	@Autowired
	private HomePageParser parser;
	
	@Before
	public void setUp() {
		String baseUrl = "http://www.celebwallpaper.org/";
		assertNotNull(parser);
		assertEquals(baseUrl, parser.getUrl());
	}
	
	@Test
	public void getAndSetUrlTest() {
		HomePageParser parser = new HomePageParser();
		String testUrl = "http://www.google.co.in/";
		parser.setUrl(testUrl);
		assertEquals(testUrl, parser.getUrl());
	}
	
	@Test
	public void getAndSetDocument() {
		HomePageParser parser = new HomePageParser();
		assertNull(parser.getDocument());
		Document document = Mockito.mock(Document.class);
		parser.setDocument(document);
		assertEquals(document, parser.getDocument());		
	}
	
	@Test
	public void getCategoryLinkTest() throws IOException{
		String expectedLink = "http://www.celebwallpaper.org/cat-a-1.htm";
		String actulaLink = parser.getCategoryLink('A');
		assertEquals(expectedLink, actulaLink);
	}
}
