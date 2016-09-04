package com.udacity.gradle.jokes;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;


/**
 * Joker class test
 */
public class TestJoker {
    @Test
    public void testJoker() throws Exception {
        String joke = new Joker().getJoke();
        assertThat(joke).isNotEmpty();
    }
}
