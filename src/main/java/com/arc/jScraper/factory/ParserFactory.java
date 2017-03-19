package com.arc.jScraper.factory;

import com.arc.jScraper.parsers.Parser;
import com.arc.jScraper.retriever.Retriever;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by danish on 9/10/16.
 */
public class ParserFactory {
    private static final Logger logger = LoggerFactory.getLogger(ParserFactory.class);
    @Autowired
    private Retriever documentRetriever;
/*
    public Parser getParser(String url, Class<? extends Parser> parserType) {
        Parser parser = null;
        documentRetriever.setUrl(url);
        Document document = (Document) documentRetriever.getDocument();
        try {
            parser = parserType.newInstance();
            parser.setDocument(document);
        } catch (Exception exception) {
            logger.error("Failed to instantiate {}", parserType.getName());
            logger.error("Exception : {}", exception.toString());
        }
        return parser;
    }*/
}
