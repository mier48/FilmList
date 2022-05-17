package com.albertomier.filmlist.ui.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.albertomier.filmlist.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailActivityTest {

    //this variable will global for all fun that we will create
    @get:Rule
    var activityScenarioRule = activityScenarioRule<DetailActivity>()

    //first: let's check(test) if our detail activity layout is displayed or is visible to the user
    @Test
    fun checkActivityVisibility() {
        onView(ViewMatchers.withId(R.id.container)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //checking if Bottom Navigation View is visible
    @Test
    fun checkingImageViewVisibility() {
        onView(ViewMatchers.withId(R.id.filmImage)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //checking if Vote Average layout is visible
    @Test
    fun checkingVoteAverageLayoutVisibility() {
        onView(ViewMatchers.withId(R.id.voteAverageLayout)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }
}