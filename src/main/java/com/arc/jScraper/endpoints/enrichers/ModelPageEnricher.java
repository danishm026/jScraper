package com.arc.jScraper.endpoints.enrichers;

import com.arc.jScraper.constants.Constants;
import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.parsers.ModelPageParser;
import com.arc.jScraper.util.ParserHelper;
import com.arc.jScraper.util.URLHelper;
import com.arc.jScraperDao.dto.application.ModelPage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
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
                ModelPage modelPage = modelPageParser.parse();
                modelPage.setPageNumber(pageNumber);
                modelPages.add(modelPage);
            }
        }
        scraperChannelModel.getModel().setModelPages(modelPages);
        return scraperChannelModel;
    }
}
