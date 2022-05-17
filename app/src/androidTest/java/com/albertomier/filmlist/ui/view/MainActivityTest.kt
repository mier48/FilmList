package com.albertomier.filmlist.ui.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.albertomier.filmlist.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    //this variable will global for all fun that we will create
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    //first: let's check(test) if our main activity layout is displayed or is visible to the user
    @Test
    fun checkActivityVisibility() {
        onView(withId(R.id.container)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //checking if Bottom Navigation View is visible
    @Test
    fun checkingBottomNavigationVisibility() {
        onView(withId(R.id.nav_view)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //checking if Search Bar layout is visible
    @Test
    fun checkingSearchBarVisibility() {
        onView(withId(R.id.searcherInputLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //checking if Recycler View is visible
    @Test
    fun checkingRecyclerViewVisibility() {
        onView(withId(R.id.filmList)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}