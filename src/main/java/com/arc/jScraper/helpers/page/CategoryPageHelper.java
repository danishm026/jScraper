package com.arc.jScraper.helpers.page;

import com.arc.jScraper.models.page.CategoryPage;
import com.arc.jScraper.util.StringUtil;

/**
 * Created by danish on 15/10/16.
 */
public class CategoryPageHelper {
    public String getModelBasePageUrlByName(final CategoryPage categoryPage, String name) {
        name = StringUtil.sanitize(name);
        return categoryPage.getNameToModelBasePageUrlMap().get(name);
    }
}
