package com.arc.jScraper.main;

import com.arc.jScraper.endpoints.gateways.Scraper;
import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.models.endpoints.gateways.ScraperRequest;
import com.arc.jScraperDao.dao.hsqldb.applicationDao.JdbcTemplateModelDao;
import com.arc.jScraperDao.dto.application.Model;
import com.arc.jScraperDao.fileio.FileIO;
import lombok.NonNull;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().run(args);
	}

	public void run(String[] args) throws IOException {
        String name = parseName(args);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(com.arc.jScraper.config.ApplicationContext.class);
        Scraper scraper = applicationContext.getBean(Scraper.class);
        JdbcTemplateModelDao jdbcTemplateModelDao = applicationContext.getBean(JdbcTemplateModelDao.class);

        ScraperChannelModel model = scraper.scrape(new ScraperRequest(name, true));

        jdbcTemplateModelDao.save(model.getModel());
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(model));
    }

    private String parseName(@NonNull final String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Name must be passed while running scraper");
        }
        else {
            return args[0];
        }
    }
}
