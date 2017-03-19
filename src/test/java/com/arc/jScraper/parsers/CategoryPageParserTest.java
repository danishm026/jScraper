package com.arc.jScraper.parsers;

import com.arc.jScraper.retriever.Retriever;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:categoryPageParserTestConfig.xml")
public class CategoryPageParserTest {
	@Autowired
    private Retriever documentRetriever;
	@Autowired
	private CategoryPageParser parser;

    /*@Before
    public void setDocument() throws Exception {
        String categoryALink = "http://www.celebwallpaper.org/cat-a-1.htm";
        documentRetriever.setUrl(categoryALink);
        Document document = (Document) documentRetriever.getDocument();
        parser.setDocument(document);
    }
*/
	/*@Test
	public void getModelBasePageTest() throws IOException {
		String modelName = "Aaliyah";
		String expectedLink = "http://www.celebwallpaper.org/cat-aaliyah-2.htm";
		
		String actualLink = parser.getModelBasePage(modelName);
		
		assertEquals(expectedLink, actualLink);
	}*/
}
