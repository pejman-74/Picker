package com.picker

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.After
import org.junit.Before
import org.junit.Test


class MainActivityTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    private fun launchMainActivity(): ActivityScenario<MainActivity>? {
        return ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun launchActivity() {
        launchMainActivity()
        onView(withId(R.id.fab_add)).check(matches(isDisplayed()))
    }
}