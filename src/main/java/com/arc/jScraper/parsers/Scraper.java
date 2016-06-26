package com.arc.jScraper.parsers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.arc.jScraper.util.StringUtil;
import com.arc.jScraperDao.dto.Model;
import com.arc.jScraperDao.dto.ModelPage;

public class Scraper {
	private static final Logger logger = LoggerFactory.getLogger(Scraper.class);
	private String name;
	@Autowired
	private HomePageParser homePageParser;
	@Autowired
	private CategoryPageParser categoryPageParser;
	@Autowired
	private ModelPageParserWrapper modelPageParserWrapper;
	@Autowired
	private ImagePageParser imagePageParser;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtil.sanitize(name);
	}

	public HomePageParser getHomePageParser() {
		return homePageParser;
	}

	public void setHomePageParser(HomePageParser homePageParser) {
		this.homePageParser = homePageParser;
	}

	public CategoryPageParser getCategoryPageParser() {
		return categoryPageParser;
	}

	public void setCategoryPageParser(CategoryPageParser categoryPageParser) {
		this.categoryPageParser = categoryPageParser;
	}

	public ImagePageParser getImagePageParser() {
		return imagePageParser;
	}

	public void setImagePageParser(ImagePageParser imagePageParser) {
		this.imagePageParser = imagePageParser;
	}

	public ModelPageParserWrapper getModelPageParserWrapper() {
		return modelPageParserWrapper;
	}

	public void setModelPageParserWrapper(ModelPageParserWrapper modelPageParserWrapper) {
		this.modelPageParserWrapper = modelPageParserWrapper;
	}
	
	private String getCategoryLink(String name) {
		String categoryPageLink;
		char firtLetterOfModelName = StringUtil.getFirstLetterAsCaps(name);

		try {
			categoryPageLink = homePageParser.getCategoryLink(firtLetterOfModelName);
		} catch (Exception exception) {
			logger.error("Failed to parse Home Page: {}.", homePageParser.getUrl());
			logger.error("Quitting");
			return null;
		}
		return categoryPageLink;
	}
	
	private String getModelPageLink(String categoryPageLink) {
		String modelPageLink;
		categoryPageParser.setUrl(categoryPageLink);
		try {
			modelPageLink = categoryPageParser.getModelBasePage(name);
		} catch (Exception exception) {
			logger.error("Failed to parse Category Page: {}.", categoryPageParser.getUrl());
			logger.error("Quitting");
			return null;
		}
		return modelPageLink;
	}
	
	private List<String> getImageLinks(List<String> imagePageLinks) {
		List<String> imageLinks = new ArrayList<String>();
		String imageLink;
		for(String imagePageLink : imagePageLinks) {
			imagePageParser.setUrl(imagePageLink);
			try {
				imageLink = imagePageParser.getImageUrl();
				imageLinks.add(imageLink);
				System.out.println(imageLink);
			} catch(Exception exception) {
				logger.error("Failed to retrieve image on page: {}", imagePageLink);
			}
		}
		return imageLinks;
	}
	
	private List<String> getImagePageLinks(List<ModelPage> modelPages) {
		List<String> imagePageLinks = new ArrayList<String>();
		for(ModelPage modelPage : modelPages) {
			imagePageLinks.addAll(modelPage.getImagePageLinkList());
		}
		return imagePageLinks;
	}

	public Model getModelObject() {
		Model model = new Model();
		model.setName(name);

		String categoryPageLink = getCategoryLink(name);
		if(categoryPageLink == null) {
			return null;
		}

		String modelPageLink = getModelPageLink(categoryPageLink);
		if(modelPageLink == null) {
			return null;
		}
		
		if(modelPageLink.equals("")) {
			logger.error("No Model named {} found.", name);
			logger.error("Quitting");
			return null;
		}
		
		model.setBaseUrl(modelPageLink);
		
		modelPageParserWrapper.setBaseUrl(modelPageLink);
		int numberOfPages = 0;
		try {
			numberOfPages = modelPageParserWrapper.getNumberOfPages();
		} catch(Exception excetion) {
			logger.error("Failed to find  number of pages for model: {}.", modelPageParserWrapper.getBaseUrl());
			logger.error("Quitting");
			return null;
		}
		
		model.setNumberOfPages(numberOfPages);
		
		List<ModelPage> modelPages = modelPageParserWrapper.getModelPageList(1, numberOfPages);
		
		model.setModelPages(modelPages);
		
		int lengthOfModelPages = modelPages.size();
		ModelPage lastModelPage = modelPages.get(lengthOfModelPages - 1);
		int numberOfImages = lastModelPage.getLastImageNumber();
		
		model.setNumberOfImages(numberOfImages);
		
		List<String> imagePageLinks = getImagePageLinks(modelPages);
		List<String> imageLinks = getImageLinks(imagePageLinks);
		
		model.setImageLinks(imageLinks);
		
		return model;
	}
}
