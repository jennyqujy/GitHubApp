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
    private String followers;
    private String following;
    private String repos;

    public User(JsonReader reader) throws IOException {
        while (reader.hasNext()) {
            String key = reader.nextName();
            if (key.equals("bio") && reader.peek() != JsonToken.NULL) {
                this.bio = reader.nextString();
            } else if (key.equals("name") && reader.peek() != JsonToken.NULL) {
                this.name = reader.nextString();
            } else if (key.equals("avatar_url") && reader.peek() != JsonToken.NULL) {
                this.avatorUrl = reader.nextString();
            } else if (key.equals("followers") && reader.peek() != JsonToken.NULL) {
                this.followers = reader.nextString();
            } else if (key.equals("following") && reader.peek() != JsonToken.NULL) {
                this.following = reader.nextString();
            } else if (key.equals("public_repos") && reader.peek() != JsonToken.NULL) {
                this.repos = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
    }

    public User(String name, String bio, String avatorUrl, String followers, String following, String repos){
        this.name = name;
        this.bio = bio;
        this.avatorUrl = avatorUrl;
        this.followers = followers;
        this.following = following;
        this.repos = repos;
    }

    public User(){
        this.name = "";
        this.bio = "";
        this.avatorUrl = "";
        this.followers = "";
        this.following = "";
        this.repos = "";
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public String getRepos() {
        return repos;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public void setRepos(String repos) {
        this.repos = repos;
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
