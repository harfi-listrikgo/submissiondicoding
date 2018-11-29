package com.example.harfinovian.submission1.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.NavigationViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.harfinovian.submission1.R
import com.example.harfinovian.submission1.R.id.*
import com.example.harfinovian.submission1.view.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by root on 3/1/18.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        onView(withId(bottom_navigation))
                .perform(NavigationViewActions.navigateTo(R.id.next))
        activityRule.activity.supportFragmentManager.beginTransaction()

        onView(withId(event_list))
                .check(matches(isDisplayed()))
        onView(withId(event_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(event_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }

    @Test
    fun testAppBehaviour() {
//        onView(withId(spinner))
//                .check(matches(isDisplayed()))
//        onView(withId(spinner)).perform(click())
//        onView(withText("Spanish La Liga")).perform(click())
//
//        onView(withText("Barcelona"))
//                .check(matches(isDisplayed()))
//        onView(withText("Barcelona")).perform(click())
//
//        onView(withId(add_to_favorite))
//                .check(matches(isDisplayed()))
//        onView(withId(add_to_favorite)).perform(click())
//        onView(withText("Added to favorite"))
//                .check(matches(isDisplayed()))
//        pressBack()

        onView(withId(bottom_navigation))
                .check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())
    }

}
