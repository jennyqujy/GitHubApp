package com.jenny.github.Models;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;

/**
 * Created by Jenny on 10/19/17.
 */

public class User {
    private String name;
    private String bio;
    private String avatorUrl;

    public User(JsonReader reader) throws IOException {
        while (reader.hasNext()) {
            String key = reader.nextName();
            if (key.equals("bio") && reader.peek() != JsonToken.NULL) {
                this.bio = reader.nextString();
            } else if (key.equals("name") && reader.peek() != JsonToken.NULL) {
                this.name = reader.nextString();
            } else if (key.equals("avatar_url") && reader.peek() != JsonToken.NULL) {
                this.avatorUrl = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
    }

    public User(String name, String bio, String avatorUrl){
        this.name = name;
        this.bio = bio;
        this.avatorUrl = avatorUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return this.bio;
    }

    public void setAvatorUrl(String avatorUrl) {
        this.avatorUrl = avatorUrl;
    }

    public String getAvatorUrl() {
        return this.avatorUrl;
    }

}
