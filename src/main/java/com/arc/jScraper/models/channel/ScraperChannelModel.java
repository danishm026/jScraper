package com.arc.jScraper.models.channel;

import com.arc.jScraper.models.page.CategoryPage;
import com.arc.jScraper.models.page.HomePage;
import com.arc.jScraperDao.dto.application.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScraperChannelModel {
    private Model model;
    private List<Integer> pagesToParse;
    private HomePage homePage;
    private CategoryPage categoryPage;
    private boolean scrapeImageURL;
}
