package com.jenny.github.API;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jenny on 10/23/17.
 */

public class APIUtil {

    static final private String base = "https://api.github.com";
    static final private boolean withAccessToken = true;
    static final private String accessToken = "e8d044b8064853e40ba6391dcedaec63731bd105";

    static URL getEndpoint(String url) {
        try {
            String endpoint = base + url + (withAccessToken ? "?access_token=" + accessToken : "");
            return new URL(endpoint);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
