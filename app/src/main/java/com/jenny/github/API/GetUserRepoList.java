package com.jenny.github.API;

import android.os.AsyncTask;

import com.jenny.github.Models.Repo;

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
 * Created by Jenny on 10/21/17.
 */

public class GetUserRepoList extends AsyncTask<String, Void, List<Repo>> {

    private final GetRepoListInterface callback;

    public GetUserRepoList(GetRepoListInterface callback) {
        this.callback = callback;
    }

    @Override
    protected List<Repo> doInBackground(String... strings) {
        try {
            URL url = APIUtil.getEndpoint("/users/" + strings[0] + "/repos");
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
            JSONArray repoJsonList = new JSONArray(total.toString());
            List<Repo> repoList = new ArrayList<>();
            for (int i=0;i<repoJsonList.length();i++){
                JSONObject repo = repoJsonList.getJSONObject(i);
                repoList.add(new Repo(repo));
            }
            conn.disconnect();
            return repoList;
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
    protected void onPostExecute(List<Repo> repoList) {
        super.onPostExecute(repoList);
        if (repoList != null) {
            try {
                callback.onFinished(repoList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}