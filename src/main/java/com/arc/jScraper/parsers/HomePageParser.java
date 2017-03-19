package com.arc.jScraper.parsers;

import com.arc.jScraper.constants.Constants;
import com.arc.jScraper.models.page.HomePage;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class HomePageParser extends Parser{
	private static final String TITLE_PREFIX = "Pictures of Celebritries starting with ";
    private static final String TITLE_ATTRIBUTE = "title";

	@Override
    public HomePage parse() {
        HomePage homePage = new HomePage(Constants.HOMEPAGE_URL);
        Map<String, String> categoryPageUrlMap = new HashMap<>();
        for (char character : Constants.ALPHABETS) {
            String title = TITLE_PREFIX + Character.toUpperCase(character);
            Elements links = document.getElementsByTag(Constants.ANCHOR_TAG);
            for (Element link : links) {
                if (link.hasAttr(TITLE_ATTRIBUTE) && link.attr(TITLE_ATTRIBUTE).equals(title)) {
                    String categoryLink = link.attr(Constants.ABSOLUTE_LINK);
                    categoryPageUrlMap.put(Character.toString(character), categoryLink);
                    break;
                }
            }
        }
        homePage.setStartingLetterToCategoryPageUrlMap(categoryPageUrlMap);
        return homePage;
    }
}
