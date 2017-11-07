package com.jenny.github.API;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jenny on 11/6/17.
 */

public class GetSearched extends AsyncTask<String, Void, List<String>> {

    private final GetSearchedInterface callback;

    public GetSearched(GetSearchedInterface callback) {
        this.callback = callback;
    }

    @Override
    protected List<String> doInBackground(String... strings) {
        try {
            boolean isUser = strings[1] == "user";
            URL url = APIUtil.getSearchUrl(strings[0], isUser);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
            JSONObject searchResult = new JSONObject(total.toString());
            JSONArray JsonList = searchResult.getJSONArray("items");
            List<String> list = new ArrayList<>();
            for (int i=0;i<JsonList.length();i++){
                JSONObject object = JsonList.getJSONObject(i);
                if (object.has("login")) {
                    list.add(object.getString("login"));
                }
                else if (object.has("full_name")){
                    list.add(object.getString("full_name"));
                }
            }
            conn.disconnect();
            return list;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<String> userList) {
        super.onPostExecute(userList);
        if (userList != null) {
            try {
                callback.onFinished(userList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

