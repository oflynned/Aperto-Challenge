package com.syzible.aperto.dashboard;

import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {
    private String hashtag;

    Tweet(JSONObject o) throws JSONException {
        this.hashtag = o.getString("name");
    }

    public String getHashtag() {
        return hashtag;
    }
}
