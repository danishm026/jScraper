package com.arc.jScraper.endpoints.routers;

import com.arc.jScraper.models.channel.ScraperChannelModel;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;

@Component
public class NumberOfPagesEnricherRouter {
    @Router(inputChannel = "numberOfPagesEnricherRouterInputChannel")
    public String route(@NonNull final ScraperChannelModel scraperChannelModel) {
        return StringUtils.isBlank(scraperChannelModel.getModel().getBaseUrl()) ? "scraperOutputChannel" : "numberOfPagesEnricherInputChannel";
    }
}
