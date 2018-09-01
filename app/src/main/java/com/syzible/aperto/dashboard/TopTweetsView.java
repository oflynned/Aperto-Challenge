package com.syzible.aperto.dashboard;

import java.util.List;

public interface TopTweetsView {
    void showTweets(List<Tweet> tweets);

    void hideTweets();

    void showProgressBar();

    void hideProgressBar();

    void showError(String error);
}
