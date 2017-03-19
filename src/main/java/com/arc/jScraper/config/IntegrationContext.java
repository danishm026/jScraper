package com.arc.jScraper.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@ComponentScan("com.arc.jScraper.endpoints")
@IntegrationComponentScan("com.arc.jScraper.endpoints")
@EnableIntegration
@Import(MessageChannels.class)
public class IntegrationContext {
}
