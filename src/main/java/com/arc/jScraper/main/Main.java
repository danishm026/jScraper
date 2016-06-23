package com.arc.jScraper.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.arc.jScraper.parsers.Scraper;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	private static BufferedReader reader;
	private static ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		reader = new BufferedReader(new InputStreamReader(System.in));
		String name;
		try {
			name = reader.readLine();
		} catch (IOException ioException) {
			logger.error("Failed to read name.");
			logger.error("Quitting.");
			return ;
		}
				
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		Scraper scraper = applicationContext.getBean("scraper", Scraper.class);
		
		scraper.setName(name);
		scraper.getModelObject();
	}
}
