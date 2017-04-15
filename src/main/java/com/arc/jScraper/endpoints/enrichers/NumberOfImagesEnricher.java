package com.arc.jScraper.endpoints.enrichers;

import com.arc.jScraper.constants.Constants;
import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.parsers.ModelPageParser;
import com.arc.jScraper.util.ParserHelper;
import com.arc.jScraper.util.URLHelper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class NumberOfImagesEnricher {
    private final ParserHelper parserHelper;
    private final ModelPageParser modelPageParser;

    @ServiceActivator(inputChannel = "numberOfImagesEnricherInputChannel", outputChannel = "pagesToParseEnricherInputChannel")
    public ScraperChannelModel setNumberOfImagse(@NonNull final ScraperChannelModel scraperChannelModel) {
        String basePageURL = scraperChannelModel.getModel().getBaseUrl();
        String numberOfPages = String.valueOf(scraperChannelModel.getModel().getNumberOfPages());
        String lastPageURL = URLHelper.addParameterToURL(basePageURL, Constants.PAGE_QUERY_PARAMETER, numberOfPages);
        if (parserHelper.initializeParser(modelPageParser, lastPageURL)) {
            scraperChannelModel.getModel().setNumberOfImages(modelPageParser.getLastImageNumber());
        }
        return scraperChannelModel;
    }
}
