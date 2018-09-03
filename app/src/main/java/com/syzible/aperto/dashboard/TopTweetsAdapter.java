package com.syzible.aperto.dashboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syzible.aperto.R;

import java.util.ArrayList;
import java.util.List;

public class TopTweetsAdapter extends RecyclerView.Adapter<TopTweetsAdapter.ViewHolder> {
    private List<Tweet> tweets;

    TopTweetsAdapter() {
        this.tweets = new ArrayList<>();
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        String ranking = "#" + (position + 1);
        holder.ranking.setText(ranking);
        holder.hashtag.setText(tweet.getHashtag());
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clearTweets() {
        this.tweets = new ArrayList<>();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ranking, hashtag;

        ViewHolder(View itemView) {
            super(itemView);
            ranking = itemView.findViewById(R.id.tweet_ranking);
            hashtag = itemView.findViewById(R.id.tweet_content);
        }
    }
}
