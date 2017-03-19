package com.arc.jScraper.endpoints.enrichers;

import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.parsers.ModelPageParser;
import com.arc.jScraper.util.ParserHelper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class NumberOfPagesEnricher {
    private final ParserHelper parserHelper;
    private final ModelPageParser modelPageParser;

    @ServiceActivator(inputChannel = "numberOfPagesEnricherInputChannel", outputChannel = "numberOfImagesEnricherRouterInputChannel")
    public ScraperChannelModel setNumberOfPages(@NonNull final ScraperChannelModel scraperChannelModel) {
        if (parserHelper.initializeParser(modelPageParser, scraperChannelModel.getModel().getBaseUrl())) {
            scraperChannelModel.getModel().setNumberOfPages(modelPageParser.getNumberOfPages());
        }
        return scraperChannelModel;
    }
}
