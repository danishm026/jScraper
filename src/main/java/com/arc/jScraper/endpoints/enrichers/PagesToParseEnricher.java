package com.arc.jScraper.endpoints.enrichers;

import com.arc.jScraper.models.channel.ScraperChannelModel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class PagesToParseEnricher {
    @ServiceActivator(inputChannel = "pagesToParseEnricherInputChannel", outputChannel = "modelPageEnricherRouterInputChannel")
    public ScraperChannelModel setPagesToParse(@NonNull final ScraperChannelModel scraperChannelModel) {
        List<Integer> allPagesToParse = IntStream.rangeClosed(1, scraperChannelModel.getModel().getNumberOfPages()).boxed().collect(Collectors.toList());
        if (null == scraperChannelModel.getPagesToParse() || scraperChannelModel.getPagesToParse().size() == 0) {
            scraperChannelModel.setPagesToParse(allPagesToParse);
        } else {
            List<Integer> pagesToPrarse = scraperChannelModel.getPagesToParse()
                    .stream()
                    .filter(page -> page >= 1 && page <= scraperChannelModel.getModel().getNumberOfPages())
                    .collect(Collectors.toList());
            scraperChannelModel.setPagesToParse(pagesToPrarse);
        }
        return scraperChannelModel;
    }
}
