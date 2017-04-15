package com.arc.jScraper.config;

import com.arc.jScraper.parsers.CategoryPageParser;
import com.arc.jScraper.parsers.HomePageParser;
import com.arc.jScraper.parsers.ImagePageParser;
import com.arc.jScraper.parsers.ModelPageParser;
import com.arc.jScraper.helpers.page.CategoryPageHelper;
import com.arc.jScraper.retriever.DocumentRetriever;
import com.arc.jScraper.retriever.Retriever;
import com.arc.jScraperDao.config.DataAccessConfig;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.arc.jScraper")
@Import({IntegrationContext.class, DataAccessConfig.class})
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
