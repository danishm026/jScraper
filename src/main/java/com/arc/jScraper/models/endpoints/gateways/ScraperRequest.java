package com.arc.jScraper.models.endpoints.gateways;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ScraperRequest {
    private final String name;
    private int [] pagesToParse;
    private final boolean scrapeImageURLIfNotAlreadyCached;
}
