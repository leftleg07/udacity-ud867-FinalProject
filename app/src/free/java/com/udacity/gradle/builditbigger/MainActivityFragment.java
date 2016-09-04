package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.jokeslib.JokeActivity;
import com.udacity.gradle.jokeslib.JokeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.OnTaskListener{

    @BindView(R.id.adView)
    AdView mAdView;

    @BindView(R.id.progressBar_loading)
    ProgressBar mProgressBar;

    private InterstitialAd mInterstitialAd;


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

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(getContext(), "ca-app-pub-3940256099942544~3347511713");

        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = new InterstitialAd(getContext());
        // Defined in res/values/strings.xml
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                buildInterstitialAd();
                mProgressBar.setVisibility(View.VISIBLE);
                new EndpointsAsyncTask(MainActivityFragment.this).execute();
            }
        });

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);
        buildInterstitialAd();
    }


    private void buildInterstitialAd() {
        // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }
    }


    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /**
     * Called before the activity is destroyed
     */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @OnClick(R.id.button_joke)
    public void tellJoke(View view) {
        showInterstitial();
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        boolean loaded = mInterstitialAd.isLoaded();
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(getContext(), "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete(String joke) {
        mProgressBar.setVisibility(View.GONE);
        Intent intent = new Intent(getContext(), JokeActivity.class);
        intent.putExtra(JokeFragment.ARG_JOKE, joke);
        getContext().startActivity(intent);
    }
}
