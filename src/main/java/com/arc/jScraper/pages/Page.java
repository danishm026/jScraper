package com.arc.jScraper.pages;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	public void retriveDocument() {
		final int MAX_TRIES = 5;
		int tries = 0;
		while(this.document == null && tries < MAX_TRIES) {
			try{
				this.document = Jsoup.connect(url).get();
			}catch(IOException ioe) {
				tries++;
				Logger logger = LoggerFactory.getLogger(getClass());
				logger.error("Error retriveing page: {}", url);
			}
		}
	}
}
