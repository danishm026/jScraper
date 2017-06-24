package com.arc.jScraper.models.page;

import lombok.Data;

@Data
public abstract class Page {
    private String url;

    public Page(String url) {
        this.url = url;
    }
}
