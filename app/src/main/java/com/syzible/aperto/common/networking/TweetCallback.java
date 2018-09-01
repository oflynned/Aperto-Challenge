package com.syzible.aperto.common.networking;

import org.json.JSONArray;
import org.json.JSONException;

public interface TweetCallback extends NetworkCallback {
    void onSuccess(JSONArray tweets) throws JSONException;
}

