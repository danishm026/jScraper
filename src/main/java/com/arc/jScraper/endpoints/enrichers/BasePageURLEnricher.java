package com.arc.jScraper.endpoints.enrichers;

import com.arc.jScraper.helpers.page.CategoryPageHelper;
import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.models.page.CategoryPage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class BasePageURLEnricher {
    private final CategoryPageHelper categoryPageHelper;

    @ServiceActivator(inputChannel = "basePageURLEnricherInputChannel", outputChannel = "numberOfPagesEnricherRouterInputChannel")
    public ScraperChannelModel setBasePageURL(@NonNull final ScraperChannelModel scraperChannelModel) {
        String name = scraperChannelModel.getModel().getName();
        CategoryPage categoryPage = scraperChannelModel.getCategoryPage();
        String modelBasePageURL = categoryPageHelper.getModelBasePageUrlByName(categoryPage, name);
        scraperChannelModel.getModel().setBaseUrl(modelBasePageURL);
        return scraperChannelModel;
    }
}
