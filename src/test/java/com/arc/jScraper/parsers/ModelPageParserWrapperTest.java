package com.arc.jScraper.parsers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.arc.jScraperDao.dto.ModelPage;

public class ModelPageParserWrapperTest {
	
	@Test
	public void getAndSetBaseUrlTest() {
		ModelPageParserWrapper parser = new ModelPageParserWrapper();
		
		String testBaseUrl = "https://www.google.co.in/";
		parser.setBaseUrl(testBaseUrl);
		String actualBaseUrl = parser.getBaseUrl();
		
		assertEquals(testBaseUrl, actualBaseUrl);
	}
	
	@Test
	public void getAndSetNumberOfPagesForModelTest() {
		ModelPageParserWrapper parser = new ModelPageParserWrapper();
		
		int testNumberOfPagesForModel = 3;
		parser.setNumberOfPagesForModel(testNumberOfPagesForModel);
		int actualNumberOfPagesForModel = 3;
		
		assertEquals(testNumberOfPagesForModel, actualNumberOfPagesForModel);
	}
	
	@Test
	public void getAndSetParserTest() {
		ModelPageParserWrapper parser = new ModelPageParserWrapper();
		ModelPageParser modelPageParser = mock(ModelPageParser.class);
		
		parser.setParser(modelPageParser);
		ModelPageParser actualParser = parser.getParser();
		
		assertEquals(modelPageParser, actualParser);
	}
	
	@Test
	public void getNumberOfPagesTest() throws IOException{
		ModelPageParserWrapper parser = new ModelPageParserWrapper();
		ModelPageParser modelPageParser = mock(ModelPageParser.class);
		
		parser.setParser(modelPageParser);
		
		int testNumberOfPages = 3;
		when(modelPageParser.getNumberOfPages()).thenReturn(testNumberOfPages);
		int firstTimePages = parser.getNumberOfPages();
		
		assertEquals(testNumberOfPages, firstTimePages);
		verify(modelPageParser).getNumberOfPages();
		
		parser.getNumberOfPages();
		verify(modelPageParser, times(1)).getNumberOfPages();
	}
	
	@Test
	public void getModelPageUrlsTest() throws IOException {
		ModelPageParserWrapper parser = new ModelPageParserWrapper();
		String baseUrl = "http://www.celebwallpaper.org/cat-aarika-wolf-3494.htm";
		parser.setBaseUrl(baseUrl);
		
		ModelPageParser modelPageParser = mock(ModelPageParser.class);
		parser.setParser(modelPageParser);
		
		int testNumberOfPages = 2;
		when(modelPageParser.getNumberOfPages()).thenReturn(testNumberOfPages);
		
		List<String> modelPageUrls = parser.getModelPageUrls(1, testNumberOfPages);
		
		assertEquals(modelPageUrls.get(0), baseUrl + "?page=" + 1);
		assertEquals(modelPageUrls.get(1), baseUrl + "?page=" + 2);
	}
	
	@Test 
	public void getModelPageListTest() throws IOException {
		ModelPageParserWrapper parser = new ModelPageParserWrapper();
		String baseUrl = "http://www.celebwallpaper.org/cat-aarika-wolf-3494.htm";
		parser.setBaseUrl(baseUrl);
		
		ModelPageParser modelPageParser = mock(ModelPageParser.class);
		parser.setParser(modelPageParser);
		
		int testNumberOfPages = 1;
		when(modelPageParser.getNumberOfPages()).thenReturn(testNumberOfPages);
				
		when(modelPageParser.getStartingImageNumber()).thenReturn(1);
		when(modelPageParser.getLastImageNumber()).thenReturn(72);
		when(modelPageParser.getImageThumbnailsLinkList()).thenReturn(Arrays.asList("testThumbnailLink"));
		when(modelPageParser.getImagePageLinkList()).thenReturn(Arrays.asList("testImageLink"));
	
		List<ModelPage> modelPageList = parser.getModelPageList(1, testNumberOfPages);
		ModelPage modelPage = modelPageList.get(0);
		
		assertEquals(1, modelPageList.size());
		assertEquals(1, modelPage.getStartingImageNumber());
		assertEquals(72, modelPage.getLastImageNumber());
		assertEquals(Arrays.asList("testThumbnailLink"), modelPage.getThumbnailsList());
		assertEquals(Arrays.asList("testImageLink"), modelPage.getImagePageLinkList());
		
		verify(modelPageParser).getStartingImageNumber();
		verify(modelPageParser).getLastImageNumber();
		verify(modelPageParser).getImageThumbnailsLinkList();
		verify(modelPageParser).getImagePageLinkList();
	}
}
