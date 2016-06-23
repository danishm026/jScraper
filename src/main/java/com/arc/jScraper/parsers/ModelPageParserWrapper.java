package com.arc.jScraper.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.arc.jScraperDao.dto.ModelPage;

public class ModelPageParserWrapper {
	private static final Logger logger = LoggerFactory.getLogger(ModelPageParserWrapper.class);
	@Autowired
	private ModelPageParser parser;
	private String baseUrl;
	private int numberOfPagesForModel;

	public int getNumberOfPagesForModel() {
		return numberOfPagesForModel;
	}

	public void setNumberOfPagesForModel(int numberOfPagesForModel) {
		this.numberOfPagesForModel = numberOfPagesForModel;
	}

	public ModelPageParser getParser() {
		return parser;
	}

	public void setParser(ModelPageParser parser) {
		this.parser = parser;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	private List<String> getModelPageUrls(int startPage, int endPage) {
		List<String> modelPageUrls = new ArrayList<String>();
		int numberOfPages = 0;
		try {
			numberOfPages = getNumberOfPages();
		} catch (Exception exception) {
			logger.error("ModelPageParserWrapper: Failed to find number of pages for model");
			return null;
		}

		startPage = Math.max(1, startPage);
		endPage = Math.min(endPage, numberOfPages);

		for (int i = startPage; i <= endPage; i++) {
			String url = baseUrl + "?page=" + i;
			modelPageUrls.add(url);
		}
		return modelPageUrls;
	}

	public int getNumberOfPages() throws IOException {
		if (numberOfPagesForModel != 0)
			return numberOfPagesForModel;
		
		parser.setUrl(baseUrl);
		numberOfPagesForModel = parser.getNumberOfPages();
		
		return numberOfPagesForModel;
	}

	public List<ModelPage> getModelPageList(int startPage, int endPage) {
		List<ModelPage> modelPageList = new ArrayList<ModelPage>();
		List<String> modelPageUrls = getModelPageUrls(startPage, endPage);

		if (modelPageUrls == null) {
			return null;
		}

		int currentPageNumber = Math.max(1, startPage);

		for (String modelPageUrl : modelPageUrls) {
			ModelPage page = new ModelPage();
			parser.setUrl(modelPageUrl);
			try {
				page.setPageNUmber(currentPageNumber);
				
				int startingImageNumber = parser.getStartingImageNumber();
				page.setStartingImageNumber(startingImageNumber);
				
				int lastImageNumber = parser.getLastImageNumber();
				page.setLastImageNumber(lastImageNumber);
				
				List<String> thumbnailsList = parser.getImageThumbnailsLinkList();
				page.setThumbnailsList(thumbnailsList);
				
				List<String> imagePageLinkList = parser.getImagePageLinkList();
				page.setImagePageLinkList(imagePageLinkList);
				
				modelPageList.add(page);
				
				currentPageNumber++;
			} catch (Exception exception) {
				logger.error("Failed to parse model page number: {}", currentPageNumber);
				currentPageNumber++;
			}
		}

		return modelPageList;
	}
}
