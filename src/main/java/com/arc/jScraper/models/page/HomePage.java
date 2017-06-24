package com.arc.jScraper.models.page;

import lombok.Data;

import java.util.Map;

/**
 * Created by danish on 15/10/16.
 */
@Data
public class HomePage extends Page {
    private Map<String, String> startingLetterToCategoryPageUrlMap;

    public HomePage(String url) {
        super(url);
    }
}
