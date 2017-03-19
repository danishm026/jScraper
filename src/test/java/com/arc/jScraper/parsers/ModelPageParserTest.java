package com.arc.jScraper.parsers;

import com.arc.jScraper.constants.Constants;
import com.arc.jScraper.retriever.Retriever;
import org.hamcrest.Matchers;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:modelPageParserTestConfig.xml")
public class ModelPageParserTest {
	@Autowired
    private Retriever documentRetriever;
	@Autowired
	private ModelPageParser parser;
	
	/*@Before
	public void setUp () {
		String url = "http://www.celebwallpaper.org/cat-aarika-wolf-3494.htm";
        documentRetriever.setUrl(url);
        parser.setDocument((Document) documentRetriever.getDocument());
	}*/
	
/*	@Test
	public void getNumberOfPagesTest() throws MalformedURLException {
		int expectedNumberOfPages = 2;
		int actualNumberOfPages = parser.getNumberOfPages();
		
		assertEquals(expectedNumberOfPages, actualNumberOfPages);
	}
	
	@Test
	public void getStartingImageNumberTest() {
		int expectedStartingImageNumber = 1;
		int actualStartingImageNumber = parser.getStartingImageNumber();
		
		assertEquals(expectedStartingImageNumber, actualStartingImageNumber);
	}*/
	
	/*@Test
	public void getLastImageNumberTest() {
		int expectedLastImageNumber = 72;
		int actualLastImageNumber = parser.getLastImageNumber();
		
		assertEquals(expectedLastImageNumber, actualLastImageNumber);
	}
	
	@Test
	public void getImageThumbnailsLinkListTest() {
		List<String> listOfThumbnailLinks = parser.getImageThumbnailsLinkList();
		for(String link : listOfThumbnailLinks) {
			String fileExtension = link.split(Constants.FILE_EXTENSION)[1];
			assertThat(fileExtension, Matchers.isOneOf("jpg", "jpeg"));
		}
	}
	
	@Test
	public void getImagePageLinkListTest() {
		List<String> listOfImagePages = parser.getImagePageLinkList();
		for(String link: listOfImagePages) {
			String fileExtension = link.split(Constants.FILE_EXTENSION)[1];
			assertThat(fileExtension, Matchers.isOneOf("htm", "html"));
		}
	}*/
}
