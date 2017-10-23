package com.jenny.github.API;

import android.os.AsyncTask;
import android.util.JsonReader;

import com.jenny.github.Models.User;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jenny on 10/19/17.
 */

public class GetUserTask extends AsyncTask<String, Void, User> {

    private final GetUserInterface callback;

    public GetUserTask(GetUserInterface callback) {
        this.callback = callback;
    }

    @Override
    protected User doInBackground(String... strings) {
        try {
            URL url = new URL("https://api.github.com/users/" + strings[0] + "?access_token=3f5e86e7831ccec9a07b0603e4f978c19821f865");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            JsonReader reader = new JsonReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            reader.beginObject();
            User user = new User(reader);
            reader.endObject();
            conn.disconnect();
            return user;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
        if (user != null) {
            try {
                callback.onFinished(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}


