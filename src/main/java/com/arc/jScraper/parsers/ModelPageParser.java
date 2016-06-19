package com.arc.jScraper.parsers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ModelPageParser extends Parser {	
	
	private int parseUrlForNumberOfPages(URL url) {
		int numberOfPages;
		String query = url.getQuery();
		numberOfPages = Integer.parseInt(query.split("=")[1]);
		return numberOfPages;
	}
	
	private List<String> getIntsInString(String str) {
		List<String> imageRange = new ArrayList<String>();
		str = str.replaceAll("[^0-9]+", " ");	
		imageRange = Arrays.asList(str.trim().split(" "));
		return imageRange;
	}
	
	public int getNumberOfPages() throws IOException {
		int numberOfPages = 1;
		Elements links = retrieveDocument().select("a.prevnext");
		for(Element link : links) {
			if(link.text().contains("Last page")) {
				String url = link.attr("abs:href");
				numberOfPages = parseUrlForNumberOfPages(new URL(url));
			}
		}
		return numberOfPages;
	}
	
	public int getStartingImageNumber() throws IOException {
		int startingImageNumber = -1;
		Elements links = retrieveDocument().getElementsByTag("div");
		for(Element link : links) {
			if(link.hasAttr("id") && link.attr("id").equals("pagingstats")) {
				String text = link.text();
				List<String> imageRange = getIntsInString(text);
				startingImageNumber = Integer.parseInt(imageRange.get(0));
			}
		}
		return startingImageNumber;
	}
	
	public int getLastImageNumber() throws IOException {
		int lastImageNumber = -1;
		Elements links = retrieveDocument().getElementsByTag("div");
		for(Element link : links) {
			if(link.hasAttr("id") && link.attr("id").equals("pagingstats")) {
				String text = link.text();
				List<String> imageRange = getIntsInString(text);
				lastImageNumber = Integer.parseInt(imageRange.get(1));
			}
		}
		return lastImageNumber;
	}
	
	public List<String> getImageThumbnailsLinkList() throws IOException {
		List<String> imageThumbnailsLinkList = new ArrayList<String>();
		
		Elements images = retrieveDocument().getElementsByTag("img");
		for(Element image : images) {
			if(image.hasAttr("itemprop") && image.attr("itemprop").equals("thumbnailUrl")) {
				imageThumbnailsLinkList.add(image.attr("abs:src"));
			}
		}
		return imageThumbnailsLinkList;
	}
	
	public List<String> getImagePageLinkList() throws IOException {
		List<String> imagePageLinkList = new ArrayList<String>();
		
		Elements links = retrieveDocument().getElementsByTag("a");
		for(Element link : links) {
			if(link.hasAttr("itemprop") && link.attr("itemprop").equals("contentURL")) {
				String linkToImagePage = link.attr("abs:href");
				imagePageLinkList.add(linkToImagePage);
				System.out.println(linkToImagePage);
			}
		}
		return imagePageLinkList;
	}
}
