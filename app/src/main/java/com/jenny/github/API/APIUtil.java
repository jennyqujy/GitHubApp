package com.jenny.github.API;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jenny on 10/23/17.
 */

public class APIUtil {

    static final private String base = "https://api.github.com";
    static final private boolean withAccessToken = true;
    static final private String accessToken = "958f589d257ba0c45c23ece97cae5c9e59e73a97";

    static String appendAccessToken(String url) {
        return url + (withAccessToken ? "?access_token=" + accessToken : "");
    }

    static URL getEndpoint(String url) {
        try {
            String endpoint = appendAccessToken(base + url);
            return new URL(endpoint);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    static URL getSearchUrl(String query, boolean isUser) {
        try {
            String endpoint = base + "/search" + (isUser? "/users?q=" : "/repositories?q=") + query;
            return new URL(endpoint);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
