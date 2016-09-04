package com.udacity.gradle.jokeslib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Android Library that displays a joke
 */
public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if(savedInstanceState == null) {
            String joke= getIntent().getExtras().getString(JokeFragment.ARG_JOKE);

            JokeFragment fragment = JokeFragment.newInstance(joke);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.joke_container, fragment)
                    .commit();
        }
    }
}
