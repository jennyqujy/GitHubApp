package com.jenny.github.API;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jenny on 10/19/17.
 */

/**
 * Class that extends asyncTask to open a new thread for getting user profile image
 */

public class GetUserPhoto extends AsyncTask<String, Void, Bitmap> {

    private final GetPhotoInterface callback;

    public GetUserPhoto(GetPhotoInterface callback) {
        this.callback = callback;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            InputStream input = conn.getInputStream();
            Bitmap result = BitmapFactory.decodeStream(input);
            conn.disconnect();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if (result != null) {
            try {
                callback.onFinished(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
