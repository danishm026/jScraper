package com.arc.jScraper.util;

import com.arc.jScraper.parsers.Parser;
import com.arc.jScraper.retriever.Retriever;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ParserHelper {
    private final Retriever documentRetriever;

    public boolean initializeParser(final Parser parser, final String url) {
        Document document = (Document) documentRetriever.getDocument(url);
        parser.setDocument(document);
        return document != null;
    }
}
