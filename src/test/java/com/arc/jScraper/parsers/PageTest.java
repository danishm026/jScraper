package com.arc.jScraper.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jsoup.nodes.Document;
import org.junit.Test;
import org.mockito.Mockito;

public class PageTest {
	@Test
	public void getAndSetUrlTest() {
		Parser parser = new Parser();
		String testUrl = "http://www.google.co.in/";
		parser.setUrl(testUrl);
		assertEquals(testUrl, parser.getUrl());
	}
	
	@Test
	public void getAndSetDocument() {
		Parser parser = new Parser();
		assertNull(parser.getDocument());
		Document document = Mockito.mock(Document.class);
		parser.setDocument(document);
		assertEquals(document, parser.getDocument());		
	}
}
