package com.arc.jScraper.parsers;

import com.arc.jScraper.constants.Constants;
import com.arc.jScraper.util.StringUtil;
import com.arc.jScraper.util.URLHelper;
import com.arc.jScraperDao.dto.application.ImageData;
import com.arc.jScraperDao.dto.application.ModelPage;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ModelPageParser extends Parser {
    private static final String PREVIOUS_NEXT_ANCHOR = "a.prevnext";
    private static final String LAST_PAGE_TEXT = "Last page";
    private static final String PAGING_STATS = "pagingstats";
    private static final String THUMBNAIL_URL = "thumbnailUrl";

	public int getNumberOfPages() {
		int numberOfPages = 1;
		Elements links = document.select(PREVIOUS_NEXT_ANCHOR);
		for(Element link : links) {
			if(link.text().contains(LAST_PAGE_TEXT)) {
				String url = link.attr(Constants.ABSOLUTE_LINK);
				numberOfPages = Integer.parseInt(URLHelper.getQueryParameter(URLHelper.getUrlFromString(url), Constants.PAGE_QUERY_PARAMETER));
				break;
			}
		}
		return numberOfPages;
	}

	public int getStartingImageNumber() {
		int startingImageNumber = -1;
		Elements divs = document.getElementsByTag(Constants.DIV_TAG);
		for(Element div : divs) {
			if(div.hasAttr(Constants.ID) && div.attr(Constants.ID).equals(PAGING_STATS)) {
				startingImageNumber = StringUtil.getIntegersInString(div.text()).get(0);
				break;
			}
		}
		return startingImageNumber;
	}

	public int getLastImageNumber() {
		int lastImageNumber = -1;
		Elements divs = document.getElementsByTag(Constants.DIV_TAG);
		for(Element div : divs) {
			if(div.hasAttr(Constants.ID) && div.attr(Constants.ID).equals(PAGING_STATS)) {
				lastImageNumber = StringUtil.getIntegersInString(div.text()).get(1);
				break;
			}
		}
		return lastImageNumber;
	}

	private List<ImageData> getImageDataList() {
		List<ImageData> imageDataList = new ArrayList<>();
		
		Elements images = document.getElementsByTag(Constants.IMG_TAG);
		for(Element image : images) {
			if(image.hasAttr(Constants.ITEMPROP) && image.attr(Constants.ITEMPROP).equals(THUMBNAIL_URL)) {
				ImageData imageData = new ImageData();
                imageData.setThumbnailUrl(image.absUrl(Constants.IMAGE_SOURCE));
                imageData.setImagePageURL(image.parent().attr(Constants.ABSOLUTE_LINK));
                imageDataList.add(imageData);
			}
		}
		return imageDataList;
	}

	@Override
	public ModelPage parse() {
        ModelPage modelPage = new ModelPage();
        modelPage.setModelPageURL(document.baseUri());
        modelPage.setStartingImageNumber(getStartingImageNumber());
        modelPage.setLastImageNumber(getLastImageNumber());
        modelPage.setImageDataList(getImageDataList());
        return modelPage;
	}
}
