package com.arc.jScraper.parsers;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HomePageParser extends Parser{	
	public String getCategoryLink(char startingLetter) throws IOException {
		String categoryLink = "";
		String titleValue = "Pictures of Celebritries starting with " + Character.toUpperCase(startingLetter);
		Elements links = retrieveDocument().getElementsByTag("a");
		for(Element link: links) {
			if(link.hasAttr("title") && link.attr("title").equals(titleValue)) {
				categoryLink = link.attr("abs:href");
				break;
			}
		}
		return categoryLink;
	}
}
