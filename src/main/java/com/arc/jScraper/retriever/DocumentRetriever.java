package com.arc.jScraper.retriever;

import com.arc.jScraper.constants.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by danish on 1/10/16.
 */
public class DocumentRetriever extends Retriever{

    private Logger logger = LoggerFactory.getLogger(DocumentRetriever.class);

    @Override
    public Object getDocument(final String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).timeout(Constants.TIMEOUT).get();
        } catch (IOException ioe) {
            logger.error("Error while retrieving document with URL: {}",  url);
            logger.error("Error: {}", ioe.toString());
        }
        return document;
    }
}
