package com.syzible.aperto.dashboard;

import android.content.Context;

import com.syzible.aperto.R;
import com.syzible.aperto.common.helper.FileLoader;
import com.syzible.aperto.common.networking.Endpoints;
import com.syzible.aperto.common.networking.TweetCallback;
import com.syzible.aperto.common.networking.TweetRequest;
import com.syzible.aperto.common.networking.WoeidCallback;
import com.syzible.aperto.common.networking.WoeidRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public class TopTweetsInteractor {

    public void fetchWoeid(float lat, float lng, WoeidCallback callback) {
        String endpoint = Endpoints.createWoeidEndpoint(lat, lng);
        WoeidRequest woeidRequest = new WoeidRequest(callback);
        woeidRequest.execute(endpoint);
    }

    public void fetchTweets(Context context, int woeid, TweetCallback callback) throws IOException, JSONException {
        String endpoint = Endpoints.createTopTweetsEndpoint(woeid);
        TweetRequest tweetRequest = new TweetRequest(callback);
//        tweetRequest.execute(endpoint);

        JSONArray tweets = FileLoader.loadSampleResponse(context, R.raw.sample_tweet_response);
        tweets = tweets.getJSONObject(0).getJSONArray("trends");
        callback.onSuccess(tweets);
    }
}
