package com.arc.jScraper.main;

import com.arc.jScraper.constants.Constants;
import com.arc.jScraper.endpoints.gateways.Scraper;
import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.models.endpoints.gateways.ScraperRequest;
import com.arc.jScraper.preferences.ScraperPreferences;
import com.arc.jScraperDao.dao.hsqldb.applicationDao.ScraperModelDao;
import com.arc.jScraperDao.dto.application.Model;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class Main {
    private final Scraper scraper;
    private final ScraperModelDao scraperModelDao;
    private final ObjectMapper objectMapper;

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(com.arc.jScraper.config.ApplicationContext.class);
        applicationContext.getBean(Main.class).run(args);
	}

	private void run(String[] args) throws IOException {
        String name = parseName(args);
        ScraperChannelModel scraperChannelModel = scraper.scrape(new ScraperRequest(name, true));
        persistModel(scraperChannelModel);
    }

    private String parseName(@NonNull final String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Name must be passed while running scraper");
        }
        return args[0];
    }

    private void persistModel(final ScraperChannelModel scraperChannelModel) throws IOException {
        Model model = scraperChannelModel.getModel();
        scraperModelDao.updateDBWithScraperModel(model, scraperChannelModel.getErrorModelPages(), scraperChannelModel.getErrorImagePages());
        saveToFile(model);
    }

    private void saveToFile(final Model model) throws IOException {
        String fileName = StringUtils.join(StringUtils.split(model.getName()), Constants.FILENAME_SEPARATOR);
        File outputFile = ScraperPreferences.DEFAULT_OUTPUT_PATH.resolve(fileName).resolve(fileName).toFile();
        outputFile.getParentFile().mkdirs();
        PrintWriter printWriter = new PrintWriter(outputFile);
        printWriter.println(objectMapper.writeValueAsString(model));
        printWriter.close();
    }
}
