package com.arc.jScraper.main;

import com.arc.jScraper.endpoints.gateways.Scraper;
import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.models.endpoints.gateways.ScraperRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception{
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(com.arc.jScraper.config.ApplicationContext.class);
        Scraper scraper = applicationContext.getBean(Scraper.class);
        ScraperChannelModel model = scraper.scrape(new ScraperRequest("Aarika Wolf", true));
        System.out.println(model.getModel().getBaseUrl());
        System.out.println(model.getModel().getNumberOfPages());
        System.out.println(model.getModel().getNumberOfImages());
        for (int i=0; i<model.getModel().getNumberOfPages(); i++) {
            System.out.println("Page number: " + (i + 1));
            for (int j=0; j<model.getModel().getModelPages().get(i).getImageDataList().size(); j++) {
                System.out.println("Thumbnail: " + model.getModel().getModelPages().get(i).getImageDataList().get(j).getThumbnailUrl()
                + "  Image Page URL: " + model.getModel().getModelPages().get(i).getImageDataList().get(j).getImagePageURL()
                + "Image URL: " + model.getModel().getModelPages().get(i).getImageDataList().get(j).getImageUrl() );
            }
        }
	}
}
