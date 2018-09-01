package com.syzible.aperto.dashboard;

import android.content.Context;

import com.syzible.aperto.common.helper.FileLoader;
import com.syzible.aperto.common.networking.TweetCallback;
import com.syzible.aperto.common.networking.WoeidCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public class TopTweetsInteractor {
    public void fetchWoeid(Context context, float lat, float lng, WoeidCallback callback) {

    }

    public void fetchTweets(Context context, int woeid, TweetCallback callback) throws IOException, JSONException {
        JSONArray tweets = FileLoader.loadSampleTweetResponse(context);
        tweets = tweets.getJSONObject(0).getJSONArray("trends");
        callback.onSuccess(tweets);
    }
}
