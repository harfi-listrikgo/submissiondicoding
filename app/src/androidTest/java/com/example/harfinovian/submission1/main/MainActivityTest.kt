package com.example.harfinovian.submission1.main

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.harfinovian.submission1.R
import com.example.harfinovian.submission1.*
import com.example.harfinovian.submission1.R.id.*
import com.example.harfinovian.submission1.view.main.MainActivity
import kotlinx.android.synthetic.main.fragment_nested_match.view.*
import org.hamcrest.core.AllOf.allOf
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
    fun mainActivityTest() {

        delay()
        onView(withId(event_list))
                .check(matches(isDisplayed()))
        delay()
        onView(withId(event_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(event_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))
        delay()

        onView(withId(add_to_favorite)).perform(click())

        Espresso.pressBack()
        delay()

        onView(withId(next)).perform(click())

        delay()
        onView(withId(event_list))
                .check(matches(isDisplayed()))
        delay()
        onView(withId(event_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(event_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))
        delay()

        onView(withId(add_to_favorite)).perform(click())

        Espresso.pressBack()
        delay()

        onView(withId(favorites)).perform(click())

        delay()
        onView(withId(event_list))
                .check(matches(isDisplayed()))

        onView(withId(event_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(event_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delay()

        onView(withId(add_to_favorite)).perform(click())

        Espresso.pressBack()
        delay()

        onView(withId(refreshLayout)).perform(ViewActions.swipeDown())

        delay()
    }

    private fun delay(){
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}
