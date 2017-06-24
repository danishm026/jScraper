package com.arc.jScraper.preferences;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ScraperPreferences {
    public static final Path DEFAULT_OUTPUT_PATH = Paths.get(System.getProperty("user.home"), ".scraper", "output");
}
