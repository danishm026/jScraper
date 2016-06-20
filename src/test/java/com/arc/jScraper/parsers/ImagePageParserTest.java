package com.arc.jScraper.parsers;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:imagePageParserTestConfig.xml")
public class ImagePageParserTest {
	@Autowired 
	private ImagePageParser parser;
	
	@Before
	public void setUp() {
		String url = "http://www.celebwallpaper.org/img-american-model-aarika-wolf-pictures-42-278264.htm";
		parser.setUrl(url);
	}
	
	@Test
	public void getImageUrlTest() throws IOException {
		String expectedImageUrl = "http://www.celebwallpaper.org/data/media/3494/american_model_aarika_wolf_pictures_42.jpg";
		String actualImageUrl = parser.getImageUrl();
		
		assertEquals(expectedImageUrl, actualImageUrl);
	}
}
