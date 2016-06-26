package com.arc.jScraper.parsers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.arc.jScraperDao.dto.ModelPage;

@RunWith(MockitoJUnitRunner.class)
public class ScraperTest {
	@Mock
	private Iterator<ModelPage> iterator;
	
	@Test
	public void getAndSetNameTest() {
		Scraper scraper = new Scraper();
		
		String testName = "Candice Swanepoel";
		scraper.setName(testName);
		String actaulName = scraper.getName();
		
		assertEquals(testName, actaulName);
	}

	@Test
	public void getAndSetHomePageParserTest() {
		Scraper scraper = new Scraper();
		
		HomePageParser homePageParser = mock(HomePageParser.class);
		scraper.setHomePageParser(homePageParser);
		
		assertEquals(homePageParser, scraper.getHomePageParser());
	}
	
	@Test
	public void getAndSetCategoryPageParserTest() {
		Scraper scraper = new Scraper();
		
		CategoryPageParser categoryPageParser = mock(CategoryPageParser.class);
		scraper.setCategoryPageParser(categoryPageParser);
		
		assertEquals(categoryPageParser, scraper.getCategoryPageParser());
	}
	
	@Test
	public void getAndSetModelPageParserWrapperTest() {
		Scraper scraper = new Scraper();
		
		ModelPageParserWrapper modelPageParserWrapper = mock(ModelPageParserWrapper.class);
		scraper.setModelPageParserWrapper(modelPageParserWrapper);
		
		assertEquals(modelPageParserWrapper, scraper.getModelPageParserWrapper());
	}
	
	@Test
	public void getAndSetImagePageParserTest() {
		Scraper scraper = new Scraper();
		
		ImagePageParser imagePageParser = mock(ImagePageParser.class);
		scraper.setImagePageParser(imagePageParser);
		
		assertEquals(imagePageParser, scraper.getImagePageParser());
	}
	
	@Test
	public void getModelObjectTest() throws IOException{
		Scraper scraper = new Scraper();
		scraper.setName("Candice");
		
		HomePageParser homePageParser = mock(HomePageParser.class);
		CategoryPageParser categoryPageParser = mock(CategoryPageParser.class);
		ModelPageParserWrapper modelPageParserWrapper = mock(ModelPageParserWrapper.class);
		ImagePageParser imagePageParser = mock(ImagePageParser.class);
		
		scraper.setHomePageParser(homePageParser);
		scraper.setCategoryPageParser(categoryPageParser);
		scraper.setModelPageParserWrapper(modelPageParserWrapper);
		scraper.setImagePageParser(imagePageParser);
		
		String categoryPageLink = "http://www.celebwallpaper.org/cat-a-1.htm";
		when(homePageParser.getCategoryLink('C')).thenReturn(categoryPageLink);
		
		String modelBasePageLink = "http://www.celebwallpaper.org/cat-aarika-wolf-3494.htm";
		when(categoryPageParser.getModelBasePage(anyString())).thenReturn(modelBasePageLink);
		
		int testNumberOfPages = 1;
		when(modelPageParserWrapper.getNumberOfPages()).thenReturn(testNumberOfPages);
		
		int numberOfImages = 63;
		ModelPage modelPage = mock(ModelPage.class);
		when(modelPage.getLastImageNumber()).thenReturn(numberOfImages);
		when(modelPage.getImagePageLinkList()).thenReturn(Arrays.asList("firstPageImageList"));
		
		List<ModelPage> modelPages = new ArrayList<ModelPage>();
		modelPages.add(modelPage);
		
		when(modelPageParserWrapper.getModelPageList(1, testNumberOfPages)).thenReturn(modelPages);
		
		when(imagePageParser.getImageUrl()).thenReturn("firstImage");
		
		assertEquals(Arrays.asList("firstImage"), scraper.getModelObject().getImageLinks());
		
	}
}
