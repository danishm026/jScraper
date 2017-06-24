package com.arc.jScraper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

@Configuration
@ComponentScan("com.arc.jScraper.endpoints")
@IntegrationComponentScan("com.arc.jScraper.endpoints")
@EnableIntegration
public class MessageChannels {
    @Bean
    @Description("Entry channel for Scraper gateway")
    public MessageChannel scraperRequestChannel() {
        return new DirectChannel();
    }

    @Bean
    @Description("Output Channel of Scraper")
    public MessageChannel scraperResponseChannel() {
        return new DirectChannel();
    }

    @Bean
    @Description("Output channel for ModelNameEnricher and input channel for BasePageUrlEnricher")
    public MessageChannel basePageUrlEnricherInputChannel() {
        return new DirectChannel();
    }

    @Bean
    @Description("Output Channel for scraper")
    public MessageChannel scraperOutputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel homePageEnricherInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel homePageEnricherRouterInputChannel() {
        return new DirectChannel();
    }
    @Bean
    public MessageChannel categoryPageEnricherRouterInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel categoryPageEnricherInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel basePageEnricherRouterInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel numberOfPagesEnricherRouterInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel numberOfPagesEnricherInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel numberOfImagesEnricherRouterInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel numberOfImagesEnricherInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel modelPageEnricherRouterInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel modelPageEnricherInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel imagePageURLEnricherRouterInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel imagePageURLEnricherInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel pagesToParseEnricherInputChannel() {
        return new DirectChannel();
    }
}
