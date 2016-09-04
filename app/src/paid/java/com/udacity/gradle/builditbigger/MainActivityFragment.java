package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.udacity.gradle.jokeslib.JokeActivity;
import com.udacity.gradle.jokeslib.JokeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.OnTaskListener{

    @BindView(R.id.progressBar_loading)
    ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mProgressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.button_joke)
    public void tellJoke(View view) {
        mProgressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask(this).execute();
    }


    @Override
    public void onComplete(String joke) {
        mProgressBar.setVisibility(View.GONE);
        Intent intent = new Intent(getContext(), JokeActivity.class);
        intent.putExtra(JokeFragment.ARG_JOKE, joke);
        getContext().startActivity(intent);
    }
}
