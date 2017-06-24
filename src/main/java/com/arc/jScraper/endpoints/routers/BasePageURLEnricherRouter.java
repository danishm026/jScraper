package com.arc.jScraper.endpoints.routers;

import com.arc.jScraper.models.channel.ScraperChannelModel;
import lombok.NonNull;
import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;

@Component
public class BasePageURLEnricherRouter {
    @Router(inputChannel = "basePageEnricherRouterInputChannel")
    public String route(@NonNull final ScraperChannelModel scraperChannelModel) {
        return null == scraperChannelModel.getCategoryPage() ? "scraperOutputChannel" : "basePageURLEnricherInputChannel";
    }
}
