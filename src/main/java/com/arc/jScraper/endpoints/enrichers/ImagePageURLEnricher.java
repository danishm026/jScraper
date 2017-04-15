package com.arc.jScraper.endpoints.enrichers;

import com.arc.jScraper.models.channel.ScraperChannelModel;
import com.arc.jScraper.models.page.ImagePage;
import com.arc.jScraper.parsers.Parser;
import com.arc.jScraper.util.ParserHelper;
import com.arc.jScraperDao.dto.application.ImageData;
import com.arc.jScraperDao.dto.application.ModelPage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ImagePageURLEnricher {
    private final ParserHelper parserHelper;
    private final Parser imagePageParser;

    @ServiceActivator(inputChannel = "imagePageURLEnricherInputChannel", outputChannel = "scraperOutputChannel")
    public ScraperChannelModel setImageURL(@NonNull final ScraperChannelModel scraperChannelModel) {
        List<ModelPage> modelPages = scraperChannelModel.getModel().getModelPages();
        for (int i=0; i<modelPages.size(); i++) {
            ModelPage currentModelPage = modelPages.get(i);
            for (int j=0; j<currentModelPage.getImageDataList().size(); j++) {
                ImageData currentImageData = currentModelPage.getImageDataList().get(j);
                if (parserHelper.initializeParser(imagePageParser, currentImageData.getImagePageURL())) {
                    currentImageData.setImageUrl(((ImagePage)imagePageParser.parse()).getImageURL());
                }
            }
        }
        return scraperChannelModel;
    }
}
