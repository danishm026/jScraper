package com.arc.jScraper.main;

import com.arc.jScraper.constants.Constants;
import com.arc.jScraper.endpoints.gateways.Scraper;
import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.models.endpoints.gateways.ScraperRequest;
import com.arc.jScraper.util.StringUtil;
import com.arc.jScraperDao.dao.hsqldb.applicationDao.JdbcTemplateModelDao;
import com.arc.jScraperDao.dao.hsqldb.dbDao.ErrorImagePageDao;
import com.arc.jScraperDao.dao.hsqldb.dbDao.ErrorModelPageDao;
import com.arc.jScraperDao.dto.application.Model;
import com.arc.jScraperDao.dto.db.ErrorImagePage;
import com.arc.jScraperDao.dto.db.ErrorModelPage;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Main {
    private static final String OUTPUT_DIRECTORY = System.getProperty(Constants.OUTPUT_DIRECTORY);

    private Scraper scraper;
    private JdbcTemplateModelDao jdbcTemplateModelDao;
    private ErrorModelPageDao errorModelPageDao;
    private ErrorImagePageDao errorImagePageDao;
    private ObjectMapper objectMapper;

    public static void main(String[] args) throws IOException {
        java.security.Security.setProperty("networkaddress.cache.negative.ttl", "0");
        new Main().run(args);
	}

	public void run(String[] args) throws IOException {
        setUp();
        String name = parseName(args);
        ScraperChannelModel scraperChannelModel = scraper.scrape(new ScraperRequest(name, true));
        persistModel(scraperChannelModel.getModel());
        persistModelPageErrors(scraperChannelModel.getErrorModelPages());
        persistErrorImagePages(scraperChannelModel.getErrorImagePages());
    }

    private void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(com.arc.jScraper.config.ApplicationContext.class);
        scraper = applicationContext.getBean(Scraper.class);
        jdbcTemplateModelDao = applicationContext.getBean(JdbcTemplateModelDao.class);
        errorModelPageDao = applicationContext.getBean(ErrorModelPageDao.class);
        errorImagePageDao = applicationContext.getBean(ErrorImagePageDao.class);
        objectMapper = new ObjectMapper();
    }

    private String parseName(@NonNull final String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Name must be passed while running scraper");
        }
        return args[0];
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

    private void persistModelPageErrors(@NonNull final List<ErrorModelPage> errorModelPages) {
        errorModelPageDao.save(errorModelPages);
    }

    private void persistErrorImagePages(@NonNull final List<ErrorImagePage> errorImagePages) {
        errorImagePageDao.save(errorImagePages);
    }
}
