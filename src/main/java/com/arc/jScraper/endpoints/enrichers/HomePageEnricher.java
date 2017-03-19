package com.arc.jScraper.endpoints.enrichers;

import com.arc.jScraper.constants.Constants;
import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.models.page.HomePage;
import com.arc.jScraper.parsers.Parser;
import com.arc.jScraper.util.ParserHelper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class HomePageEnricher {
    private final ParserHelper parserHelper;
    private final Parser homePageParser;

    @ServiceActivator(inputChannel = "homePageEnricherInputChannel", outputChannel = "categoryPageEnricherRouterInputChannel")
    public ScraperChannelModel setHomePage(@NonNull final ScraperChannelModel scraperChannelModel) {
        if (parserHelper.initializeParser(homePageParser, Constants.HOMEPAGE_URL)) {
            HomePage homePage = homePageParser.parse();
            scraperChannelModel.setHomePage(homePage);
        }
        return scraperChannelModel;
    }
}
