package com.arc.jScraper.parsers;

import com.arc.jScraper.constants.Constants;
import com.arc.jScraper.models.page.ImagePage;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImagePageParser extends Parser{
	private static final String CONTENT_URL = "contentUrl";

	@Override
	public ImagePage parse() {
        ImagePage imagePage = new ImagePage();
		Elements images = document.getElementsByTag(Constants.IMG_TAG);
		for(Element image: images) {
			if(image.hasAttr(Constants.ITEMPROP) && CONTENT_URL.equals(image.attr(Constants.ITEMPROP))) {
				imagePage.setImageURL(image.absUrl(Constants.IMAGE_SOURCE));
			}
		}
		return imagePage;
	}
}
