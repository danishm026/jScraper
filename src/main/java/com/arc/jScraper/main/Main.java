package com.arc.jScraper.main;

import com.arc.jScraper.constants.Constants;
import com.arc.jScraper.endpoints.gateways.Scraper;
import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.models.endpoints.gateways.ScraperRequest;
import com.arc.jScraperDao.dao.hsqldb.applicationDao.JdbcTemplateModelDao;
import com.arc.jScraperDao.dto.application.Model;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    private static final String OUTPUT_DIRECTORY = System.getProperty(Constants.OUTPUT_DIRECTORY);

    private Scraper scraper;
    private JdbcTemplateModelDao jdbcTemplateModelDao;
    private ObjectMapper objectMapper;

    public static void main(String[] args) throws IOException {
        new Main().run(args);
	}

	public void run(String[] args) throws IOException {
        setUp();
        String name = parseName(args);
        ScraperChannelModel model = scraper.scrape(new ScraperRequest(name, true));
        persistModel(model.getModel());
    }

    private void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(com.arc.jScraper.config.ApplicationContext.class);
        scraper = applicationContext.getBean(Scraper.class);
        jdbcTemplateModelDao = applicationContext.getBean(JdbcTemplateModelDao.class);
        objectMapper = new ObjectMapper();
    }

    private String parseName(@NonNull final String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Name must be passed while running scraper");
        }
        else {
            return args[0];
        }
    }

    private void persistModel(final Model model) throws IOException {
        jdbcTemplateModelDao.save(model);
        String fileName = StringUtils.join(StringUtils.split(model.getName()), Constants.FILENAME_SEPARATOR);
        File outputFile = new File(OUTPUT_DIRECTORY + "/" + fileName + "/" + fileName);
        outputFile.getParentFile().mkdirs();
        PrintWriter printWriter = new PrintWriter(outputFile);
        printWriter.println(objectMapper.writeValueAsString(model));
        printWriter.close();
    }
}
