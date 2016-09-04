package com.udacity.gradle.builditbigger;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * MainActivity test
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testMainActivity() throws Exception {
        onView(withId(R.id.button_joke)).perform(click());
        onView(withId(R.id.textView_joke)).check(ViewAssertions.matches(withText(new BaseMatcher<String>() {
            @Override
            public void describeTo(Description description) {}

            @Override
            public boolean matches(Object item) {
                return !((String) item).isEmpty();
            }
        })));


    }
}
