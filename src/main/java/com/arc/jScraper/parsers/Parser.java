package com.arc.jScraper.parsers;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parser {
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
	
	public Document retrieveDocument() throws IOException{
		if(document == null) {
			document = Jsoup.connect(url).timeout(10*1000).get();
		}
		return document;
	}
}
