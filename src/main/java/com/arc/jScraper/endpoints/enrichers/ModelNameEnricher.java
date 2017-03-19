package com.arc.jScraper.endpoints.enrichers;

import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.models.endpoints.gateways.ScraperRequest;
import com.arc.jScraper.util.StringUtil;
import com.arc.jScraperDao.dto.application.Model;
import lombok.NonNull;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class ModelNameEnricher {
    @ServiceActivator(inputChannel = "scraperRequestChannel", outputChannel = "homePageEnricherRouterInputChannel")
    public ScraperChannelModel setName(@NonNull final ScraperRequest request) {
        ScraperChannelModel scraperChannelModel = new ScraperChannelModel();
        Model model = new Model();
        model.setName(StringUtil.sanitize(request.getName()));
        scraperChannelModel.setModel(model);
        scraperChannelModel.setScrapeImageURL(request.isScrapeImageURLIfNotAlreadyCached());
        return scraperChannelModel;
    }
}
