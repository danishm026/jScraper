package com.arc.jScraper.parsers;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImagePageParser extends Parser{
	public String getImageUrl() throws IOException {
		String imageUrl = "";
		Elements images = retrieveDocument().getElementsByTag("img");
		for(Element image: images) {
			if(image.hasAttr("itemprop") && image.attr("itemprop").equals("contentUrl")) {
				imageUrl = image.attr("abs:src");
				break;
			}
		}
		return imageUrl;
	}
}
