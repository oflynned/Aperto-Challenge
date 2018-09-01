package com.syzible.aperto.dashboard;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.syzible.aperto.R;

import java.util.List;
import java.util.Objects;

public class TopTweetsFragment extends Fragment implements TopTweetsView {

    private TopTweetsPresenter presenter;
    private RecyclerView recyclerView;
    private TopTweetsAdapter adapter;
    private ProgressBar progressBar;

    private View errorView;

    public TopTweetsFragment() {
    }

    public static TopTweetsFragment getInstance() {
        return new TopTweetsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.top_tweets_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = new ProgressBar(getActivity());
        progressBar.setIndeterminate(true);

        recyclerView = view.findViewById(R.id.top_tweets_recycler_view);
        errorView = view.findViewById(R.id.error_layout);

        setupRecyclerView();

        if (presenter == null)
            presenter = new TopTweetsPresenterImpl(this);

        presenter.fetchTweets(getActivity());
    }

    private void setupRecyclerView() {
        adapter = new TopTweetsAdapter();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showTweets(List<Tweet> tweets) {
        adapter.setTweets(tweets);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void hideTweets() {
        adapter.clearTweets();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        hideTweets();
        Snackbar.make(Objects.requireNonNull(getView()), error, Snackbar.LENGTH_LONG).show();
        errorView.setVisibility(View.VISIBLE);
    }
}
