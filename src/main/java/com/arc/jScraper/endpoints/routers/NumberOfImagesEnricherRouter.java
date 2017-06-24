package com.arc.jScraper.endpoints.routers;

import com.arc.jScraper.models.channel.ScraperChannelModel;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;

@Component
public class NumberOfImagesEnricherRouter {
    @Router(inputChannel = "numberOfImagesEnricherRouterInputChannel")
    public String route(@NonNull final ScraperChannelModel scraperChannelModel) {
        if (StringUtils.isBlank(scraperChannelModel.getModel().getBaseUrl()) || scraperChannelModel.getModel().getNumberOfPages() <= 0) {
            return "scraperOutputChannel";
        }
        return "numberOfImagesEnricherInputChannel";
    }
}
