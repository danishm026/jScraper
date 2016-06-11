package com.arc.jScraper.pages;

import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

public class PageTest {
	@Test
	public void getAndSetUrlTest() {
		String testUrl = "Test URL"; 
		Page page = new Page();
		page.setUrl(testUrl);
		String getterUrl = page.getUrl();
		assertEquals(testUrl, getterUrl);
	}
	
	@Test 
	public void getAndSetDocumentTest() {
		Page page = new Page();
		Document testDocument = mock(Document.class);
		page.setDocument(testDocument);
		Document getterDocument = page.getDocument();
		assertEquals(testDocument, getterDocument);
	}
	
	@Test
	public void retrieveDocumentSuccessTest() throws IOException{
		String testUrl = "http://www.celebwallpaper.org/";
		Page page = new Page();
		page.setUrl(testUrl);
		assertNull(page.getDocument());
		page.retrieveDocument();
		assertNotNull(page.getDocument());
	}
}
