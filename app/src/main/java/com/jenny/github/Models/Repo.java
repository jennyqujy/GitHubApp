package com.jenny.github.Models;

import com.jenny.github.Const.RepoType;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Jenny on 10/21/17.
 */

public class Repo {
    private String name;
    private RepoType type;
    private String url;
    private String language;
    private String description;

    public Repo(JSONObject reader) throws IOException, JSONException {
        this.type = (reader.get("private")=="true")?RepoType.PRIVATE:RepoType.PUBLIC;
        this.name = (String)reader.get("name");
        this.url = (String) reader.get("html_url");
        if (!reader.isNull("language")) {
            this.language = (String) reader.get("language");
        }
        if (!reader.isNull("description")) {
            this.description = (String) reader.get("description");
        }
    }

    public Repo(String name, RepoType type, String url, String language, String description){
        this.name = name;
        this.type = type;
        this.url = url;
        this.language = language;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public RepoType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(RepoType type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
