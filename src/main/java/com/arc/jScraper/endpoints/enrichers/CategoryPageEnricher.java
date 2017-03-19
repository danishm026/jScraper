package com.arc.jScraper.endpoints.enrichers;

import com.arc.jScraper.helpers.page.HomePageHelper;
import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.parsers.Parser;
import com.arc.jScraper.util.ParserHelper;
import com.arc.jScraper.util.StringUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CategoryPageEnricher {
    private final ParserHelper parserHelper;
    private final Parser categoryPageParser;
    private final HomePageHelper homePageHelper;

    @ServiceActivator(inputChannel = "categoryPageEnricherInputChannel", outputChannel = "basePageEnricherRouterInputChannel")
    public ScraperChannelModel setCategoryPage(@NonNull final ScraperChannelModel scraperChannelModel) {
        char nameStartingLetter = StringUtil.getFirstLetterAsCaps(scraperChannelModel.getModel().getName());
        String categoryPageURL = homePageHelper.getCategoryPageUrl(scraperChannelModel.getHomePage(), nameStartingLetter);
        if (parserHelper.initializeParser(categoryPageParser, categoryPageURL)) {
            scraperChannelModel.setCategoryPage(categoryPageParser.parse());
        }
        return scraperChannelModel;
    }
}
