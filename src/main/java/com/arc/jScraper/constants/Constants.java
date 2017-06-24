package com.arc.jScraper.constants;

public class Constants {
    //Connection Settings
    public static final int TIMEOUT = 10 * 1000;
    public static final int MAX_RETRIES = 5;
    public static final int BACK_OFF_TIME = 5 * 000;

    //Site Constants
    public static final String HOMEPAGE_URL = "http://www.celebwallpaper.org";
    public static final String PAGE_QUERY_PARAMETER = "page";

    //Tags
    public static final String ANCHOR_TAG = "a";
    public static final String DIV_TAG = "div";
    public static final String IMG_TAG = "img";

    //Attributes
    public static final String ID = "id";
    public static final String ITEMPROP = "itemprop";

    //JSoup Constants
    public static final String ABSOLUTE_LINK = "abs:href";
    public static final String IMAGE_SOURCE = "src";

    //URL Constants
    public static final String QUERY_PARAMETER_SEPERATOR = "&";
    public static final String QUERY_KEY_VALUE_SEPERATOR = "=";

    //Regex
    public static final String WHITESPACE = "\\s+";
    public static final String FILENAME_SEPARATOR = "_";
    public static final String FILE_EXTENSION = "\\.(?=[^\\.]+$)";
    public static final char [] ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static final String OUTPUT_DIRECTORY = "output.directory";
    public static final String LOG_FILE = "log.filename";
}
