package com.arc.jScraper.parsers;

import com.arc.jScraper.retriever.Retriever;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class HomePageParserTest {
    private Retriever documentRetriever;
	private HomePageParser parser;

	/*@Before
    public void setDocument() throws Exception {
        parser = new HomePageParser();
		documentRetriever = new DocumentRetriever(Constants.HOMEPAGE_URL);
        Document document = (Document) documentRetriever.getDocument();
        parser.setDocument(document);
    }
	@Test
	public void getCategoryLinkTest() throws IOException{
		String expectedLink = "http://www.celebwallpaper.org/cat-a-1.htm";
		HomePage homePage = parser.parse();
		assertEquals(expectedLink, homePage.getStartingLetterToCategoryPageUrlMap().get('A'));
	}*/
}
