package com.arc.jScraper.endpoints.gateways;

import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.models.endpoints.gateways.ScraperRequest;
import lombok.NonNull;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "scraperGateway", defaultRequestChannel = "scraperRequestChannel", defaultReplyChannel = "scraperOutputChannel")
public interface Scraper {
    ScraperChannelModel scrape(@NonNull final ScraperRequest request);
}
