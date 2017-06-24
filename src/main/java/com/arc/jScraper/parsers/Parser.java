package com.arc.jScraper.parsers;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;

public abstract class Parser {
	@Getter
	@Setter
	protected Document document;

	public abstract <T> T parse();
}
