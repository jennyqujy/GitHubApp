package com.jenny.github.API;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jenny on 10/23/17.
 */

public class APIUtil {

    static final private String base = "https://api.github.com";
    static final private boolean withAccessToken = true;
    static final private String accessToken = "90985fd9f0e7c4ad810219650e1295d1fdf0f073";

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
