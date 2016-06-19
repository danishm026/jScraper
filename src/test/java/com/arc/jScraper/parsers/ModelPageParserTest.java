package com.arc.jScraper.parsers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:modelPageParserTestConfig.xml")
public class ModelPageParserTest {
	@Autowired
	private ModelPageParser parser;
	
	@Before 
	public void setUp () {
		String url = "http://www.celebwallpaper.org/cat-aarika-wolf-3494.htm";
		parser.setUrl(url);
	}
	
	@Test
	public void getNumberOfPagesTest() throws IOException {
		int expectedNumberOfPages = 2;
		int actualNumberOfPages = parser.getNumberOfPages();
		
		assertEquals(expectedNumberOfPages, actualNumberOfPages);
	}
	
	@Test
	public void getStartingImageNumberTest() throws IOException {
		int expectedStartingImageNumber = 1;
		int actualStartingImageNumber = parser.getStartingImageNumber();
		
		assertEquals(expectedStartingImageNumber, actualStartingImageNumber);
	}
	
	@Test
	public void getLastImageNumberTest() throws IOException {
		int expectedLastImageNumber = 72;
		int actualLastImageNumber = parser.getLastImageNumber();
		
		assertEquals(expectedLastImageNumber, actualLastImageNumber);
	}
	
	@Test
	public void getImageThumbnailsLinkListTest() throws IOException {
		List<String> listOfThumbnailLinks = parser.getImageThumbnailsLinkList();
		for(String link : listOfThumbnailLinks) {
			String fileExtension = link.split("\\.(?=[^\\.]+$)")[1];
			assertThat(fileExtension, Matchers.isOneOf("jpg", "jpeg"));
		}
	}
	
	@Test
	public void getImagePageLinkListTest() throws IOException {
		List<String> listOfImagePages = parser.getImagePageLinkList();
		for(String link: listOfImagePages) {
			String fileExtension = link.split("\\.(?=[^\\.]+$)")[1];
			assertThat(fileExtension, Matchers.isOneOf("htm", "html"));
		}
	}
}
