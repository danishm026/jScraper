package com.arc.jScraper.parsers;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HomePageParser {
	private String url;
	private Document document;
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	private Document retriveDocument() throws IOException{
		if(document == null) {
			document = Jsoup.connect(url).timeout(10*1000).get();
		}
		return document;
	}
	
	public String getCategoryLink(char startingLetter) throws IOException {
		String categoryLink = "";
		String titleValue = "Pictures of Celebritries starting with " + Character.toUpperCase(startingLetter);
		Elements links = retriveDocument().getElementsByTag("a");
		for(Element link: links) {
			if(link.hasAttr("title") && link.attr("title").equals(titleValue)) {
				categoryLink = link.attr("abs:href");
				break;
			}
		}
		return categoryLink;
	}
}
