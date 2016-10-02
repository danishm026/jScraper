package com.arc.jScraper.retriever;

/**
 * Created by danish on 1/10/16.
 */
public abstract class Retriever {
    protected String url;

    public Retriever(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public abstract Object getDocument() throws Exception;
}
