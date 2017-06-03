package com.arc.jScraper.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadHelper {
    public static final Logger logger = LoggerFactory.getLogger(ThreadHelper.class);

    public static void sleep(final long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            logger.warn("Sleep Interrupted. Resuming");
        }
    }
}
