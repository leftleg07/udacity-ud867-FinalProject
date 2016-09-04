package com.udacity.gradle.jokeslib;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * JokeActivity class test
 */
@RunWith(AndroidJUnit4.class)
public class TestJokeActivity {
    @Rule
    public final IntentsTestRule<JokeActivity> rule = new IntentsTestRule<>(JokeActivity.class, false, false);

    static final String JOKE_TEXT = "my joke";

    @Test
    public void testJokeActivity() throws Exception {
        Intent intent = new Intent();
        intent.putExtra(JokeFragment.ARG_JOKE, JOKE_TEXT);
        rule.launchActivity(intent);

        onView(withId(R.id.textView_joke)).check(matches(withText(JOKE_TEXT)));
    }
}
