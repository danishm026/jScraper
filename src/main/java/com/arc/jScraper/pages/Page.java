package com.arc.jScraper.pages;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Page {
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
	
	public void retrieveDocument() throws IOException{
		final int MAX_TRIES = 5;
		int tries = 0;
		while(this.document == null && tries < MAX_TRIES) {
				this.document = Jsoup.connect(url).get();
		}
	}
}
