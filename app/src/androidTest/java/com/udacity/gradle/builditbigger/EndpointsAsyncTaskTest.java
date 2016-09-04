package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static com.google.common.truth.Truth.assertThat;

/**
 * Joker GCE End point task class test
 */
@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    private CountDownLatch signal = null;

    @Before
    public void setUp() throws Exception {
        signal = new CountDownLatch(1);

    }

    @After
    public void tearDown() throws Exception {
        signal.countDown();

    }

    @Test
    public void asyncTaskTest() throws Exception {
        new EndpointsAsyncTask(new EndpointsAsyncTask.OnTaskListener() {
            @Override
            public void onComplete(String joke) {
                assertThat(joke).isNotEmpty();
                signal.countDown();
            }
        }).execute();

        signal.await();
    }
}
