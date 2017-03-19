package com.arc.jScraper.helpers.page;

import com.arc.jScraper.models.page.HomePage;
import org.springframework.stereotype.Component;

@Component
public class HomePageHelper {
    public String getCategoryPageUrl(HomePage homePage, char startingLetter) {
        return homePage.getStartingLetterToCategoryPageUrlMap().get(Character.toString(startingLetter));
    }
}
