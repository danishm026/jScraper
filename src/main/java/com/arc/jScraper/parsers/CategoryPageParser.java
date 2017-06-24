package com.arc.jScraper.parsers;

import com.arc.jScraper.constants.Constants;
import com.arc.jScraper.models.page.CategoryPage;
import com.arc.jScraper.util.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryPageParser extends Parser {

    private static final String TITLE_SUFFIX = " Pictures and Wallpapers";
    private static final String TITLE_ATTRIBUTE = "title";

	@Override
	public CategoryPage parse() {
		CategoryPage categoryPage = new CategoryPage(document.baseUri());
        List<String> modelNames = new ArrayList<>();
        Map<String, String> modelBasePageUrlMap = new HashMap<>();
        Elements links = document.getElementsByTag(Constants.ANCHOR_TAG);
        for (Element link : links) {
            if(link.hasAttr(TITLE_ATTRIBUTE) && link.attr(TITLE_ATTRIBUTE).contains(TITLE_SUFFIX)) {
                String modelName = StringUtil.sanitize(link.text());
                modelNames.add(modelName);
                modelBasePageUrlMap.put(modelName, link.attr(Constants.ABSOLUTE_LINK));
            }
        }
        categoryPage.setModelNames(modelNames);
        categoryPage.setNameToModelBasePageUrlMap(modelBasePageUrlMap);
        return categoryPage;
	}
}
