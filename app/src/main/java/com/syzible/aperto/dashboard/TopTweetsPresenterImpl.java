package com.syzible.aperto.dashboard;

import android.content.Context;

import com.syzible.aperto.common.networking.TweetCallback;
import com.syzible.aperto.common.networking.WoeidCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TopTweetsPresenterImpl implements TopTweetsPresenter, WoeidCallback, TweetCallback {

    private TopTweetsInteractor topTweetsInteractor;
    private TopTweetsView topTweetsView;
    private List<Tweet> topTweetsList;
    private Context context;

    TopTweetsPresenterImpl(TopTweetsView topTweetsView) {
        this.topTweetsView = topTweetsView;
        this.topTweetsList = new ArrayList<>();
        this.topTweetsInteractor = new TopTweetsInteractor();
    }

    @Override
    public void fetchTweets(Context context) {
        this.context = context;
        if (topTweetsView != null) {
            topTweetsView.showProgressBar();
        }

        topTweetsInteractor.fetchWoeid(52, -7, this);
    }

    @Override
    public void onSuccess(JSONArray tweets) throws JSONException {
        if (topTweetsList == null)
            topTweetsList = new ArrayList<>();

        for (int i = 0; i < tweets.length(); i++) {
            JSONObject o = tweets.getJSONObject(i);
            Tweet tweet = new Tweet(o);
            topTweetsList.add(tweet);
        }

        if (topTweetsView != null) {
            topTweetsView.showTweets(topTweetsList);
        }
    }

    @Override
    public void onSuccess(int woeid) {
        try {
            topTweetsInteractor.fetchTweets(context, woeid, this);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            if (topTweetsView != null)
                topTweetsView.showError("Something went wrong");
        }
    }

    @Override
    public void onFailure(int statusCode, JSONObject error) {
        try {
            if (topTweetsView != null)
                topTweetsView.showError(error.getString("error"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
