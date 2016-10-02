package com.arc.jScraper.retriever;

import com.arc.jScraper.constants.Constants;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by danish on 1/10/16.
 */
public class DocumentRetriever extends Retriever{

    public DocumentRetriever() {
        super("");
    }
    public DocumentRetriever(String url) {
        super(url);
    }

    @Override
    public Object getDocument() throws IOException{
        return Jsoup.connect(url).timeout(Constants.TIMEOUT).get();
    }
}
