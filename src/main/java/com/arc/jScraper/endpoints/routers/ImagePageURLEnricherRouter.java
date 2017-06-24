package com.arc.jScraper.endpoints.routers;

import com.arc.jScraper.models.channel.ScraperChannelModel;
import lombok.NonNull;
import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;

@Component
public class ImagePageURLEnricherRouter {
    @Router(inputChannel = "imagePageURLEnricherRouterInputChannel")
    public String route(@NonNull final ScraperChannelModel scraperChannelModel) {
        if (!scraperChannelModel.isScrapeImageURL()) {
            return "scraperOutputChannel";
        }
        return "imagePageURLEnricherInputChannel";
    }

}
