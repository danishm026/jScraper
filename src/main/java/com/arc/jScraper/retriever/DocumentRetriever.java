package com.arc.jScraper.retriever;

import com.arc.jScraper.constants.Constants;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DocumentRetriever extends Retriever{

    private Logger logger = LoggerFactory.getLogger(DocumentRetriever.class);

    @Override
    public Object getDocument(final String url) {
        Document document = null;
        int tries = 0;
        while (document == null && tries < Constants.MAX_RETRIES) {
            try {
                document = Jsoup.connect(url).timeout(Constants.TIMEOUT).get();
            } catch(IOException ioe){
            tries++;
            logger.error("Error while retrieving document with URL: {}. Error {}. Retrying {} time", url, ioe.toString(), tries + 1);
           }
        }
        return document;
    }
}
