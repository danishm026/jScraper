package com.arc.jScraper.endpoints.enrichers;

import com.arc.jScraper.constants.Constants;
import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.parsers.ModelPageParser;
import com.arc.jScraper.util.ParserHelper;
import com.arc.jScraper.util.URLHelper;
import com.arc.jScraperDao.dto.application.ModelPage;
import com.arc.jScraperDao.dto.db.ErrorModelPage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class ModelPageEnricher {
    private final ParserHelper parserHelper;
    private final ModelPageParser modelPageParser;

    @ServiceActivator(inputChannel = "modelPageEnricherInputChannel", outputChannel = "imagePageURLEnricherRouterInputChannel")
    public ScraperChannelModel setModelPages(@NonNull final ScraperChannelModel scraperChannelModel) {
        List<ModelPage> modelPages = new ArrayList<>();
        String baseURL = scraperChannelModel.getModel().getBaseUrl();
        for (int pageNumber : scraperChannelModel.getPagesToParse()) {
            String currentModelPageURL = URLHelper.addParameterToURL(baseURL, Constants.PAGE_QUERY_PARAMETER, String.valueOf(pageNumber));
            if (parserHelper.initializeParser(modelPageParser, currentModelPageURL)) {
                log.info("Parsing model page {}", currentModelPageURL);
                ModelPage modelPage = modelPageParser.parse();
                modelPage.setPageNumber(pageNumber);
                modelPages.add(modelPage);
            } else {
                log.error("Failed to retrieve model page {}", currentModelPageURL);
                scraperChannelModel.getErrorModelPages().add(getErrorModelPage(scraperChannelModel.getModel().getName(), currentModelPageURL, pageNumber));
            }
        }
        scraperChannelModel.getModel().setModelPages(modelPages);
        return scraperChannelModel;
    }

    private ErrorModelPage getErrorModelPage(final String name, final String modelPageURL, final int pageNumber) {
        ErrorModelPage errorModelPage = new ErrorModelPage();
        errorModelPage.setName(name);
        errorModelPage.setModelPageURL(modelPageURL);
        errorModelPage.setPageNumber(pageNumber);
        return errorModelPage;
    }
}
