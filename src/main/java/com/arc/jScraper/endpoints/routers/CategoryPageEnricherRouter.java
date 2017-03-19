package com.arc.jScraper.endpoints.routers;

import com.arc.jScraper.models.channel.ScraperChannelModel;
import lombok.NonNull;
import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;

@Component
public class CategoryPageEnricherRouter {
    @Router(inputChannel = "categoryPageEnricherRouterInputChannel")
    public String route(@NonNull final ScraperChannelModel scraperChannelModel) {
        if (null == scraperChannelModel.getHomePage()) {
            return "scraperOutputChannel";
        }
        return "categoryPageEnricherInputChannel";
    }
}
