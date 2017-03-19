package com.arc.jScraper.config;

import com.arc.jScraper.parsers.CategoryPageParser;
import com.arc.jScraper.parsers.HomePageParser;
import com.arc.jScraper.parsers.ImagePageParser;
import com.arc.jScraper.parsers.ModelPageParser;
import com.arc.jScraper.helpers.page.CategoryPageHelper;
import com.arc.jScraper.retriever.DocumentRetriever;
import com.arc.jScraper.retriever.Retriever;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by danish on 16/10/16.
 */
@Configuration
@ComponentScan("com.arc.jScraper")
@Import(IntegrationContext.class)
public class ApplicationContext {
    @Bean
    public Retriever documentRetriever() {
        return new DocumentRetriever();
    }

    @Bean
    public HomePageParser homePageParser() {
        return new HomePageParser();
    }

    @Bean
    public CategoryPageParser categoryPageParser() {
        return new CategoryPageParser();
    }

    @Bean
    public ModelPageParser modelPageParser() {
        return new ModelPageParser();
    }

    @Bean
    public ImagePageParser imagePageParser() {
        return new ImagePageParser();
    }

    @Bean
    public CategoryPageHelper categoryPageHelper() {
        return new CategoryPageHelper();
    }
}
