package com.arc.jScraper.models.endpoints.gateways;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ScraperRequest {
    private final String name;
    private List<Integer> pagesToParse;
    private final boolean scrapeImageURLIfNotAlreadyCached;
}
