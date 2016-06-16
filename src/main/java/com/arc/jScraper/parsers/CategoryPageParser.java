package com.arc.jScraper.parsers;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CategoryPageParser extends Parser {
	public String getModelBasePage(String name) throws IOException {
		String modelBasePageLink = "";
		String titleValue = name +  " Pictures and Wallpapers";
		
		Elements links = retrieveDocument().getElementsByTag("a");		
		for(Element link : links) {
			if(link.hasAttr("title") && link.attr("title").equals(titleValue)) {
				modelBasePageLink = link.attr("abs:href");
				break;
			}
		}
		return modelBasePageLink;
	}
}
