package com.arc.jScraper.util;

import com.arc.jScraper.constants.Constants;
import lombok.NonNull;
import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class URLHelper {
    public static String getQueryParameter(URL url, String key) {
        String parameterValue = null;
        String query = url.getQuery();
        List<String> parameters = Arrays.asList(query.split(Constants.QUERY_PARAMETER_SEPERATOR));
        for(String parameter : parameters) {
            String parameterKey = parameter.split(Constants.QUERY_KEY_VALUE_SEPERATOR)[0];
            if (parameterKey.equals(key)) {
                parameterValue = parameter.split(Constants.QUERY_KEY_VALUE_SEPERATOR)[1];
                break;
            }
        }
        return parameterValue;
    }

    public static URL getUrlFromString(String urlString) {
        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException mue) {
            return null;
        }
        return url;
    }

    public static String addParameterToURL(@NonNull final String url, @NonNull final String parameter, @NonNull final String value) {
        try {
            return new URIBuilder(url).addParameter(parameter, value).build().toString();
        } catch (URISyntaxException mue) {
            return null;
        }
    }
}
