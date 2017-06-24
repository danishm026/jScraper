package com.arc.jScraper.models.page;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by danish on 15/10/16.
 */
@Data
public class CategoryPage extends Page {
    private List<String> modelNames;
    private Map<String, String> nameToModelBasePageUrlMap;

    public CategoryPage(String url) {
        super(url);
    }
}
