package com.arc.jScraper.endpoints.routers;

import com.arc.jScraper.models.channel.ScraperChannelModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;

@Component
public class HomePageEnricherRouter {
    @Router(inputChannel = "homePageEnricherRouterInputChannel")
    public String route(final ScraperChannelModel scraperChannelModel) {
        if (StringUtils.isBlank(scraperChannelModel.getModel().getName())) {
            return "scraperOutputChannel";
        }
        return "homePageEnricherInputChannel";
    }
}
