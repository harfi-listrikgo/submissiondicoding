package com.example.harfinovian.submission1.main

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.harfinovian.submission1.R
import com.example.harfinovian.submission1.view.main.MainActivity
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
    fun testRecyclerViewBehaviour() {
        Thread.sleep(3000)
        onView(withId(R.id.event_list)).check(matches(isDisplayed()))

        Thread.sleep(1000)
        onData(allOf()).inAdapterView(withId(R.id.event_list))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))

        Thread.sleep(1000)
        onData(allOf()).inAdapterView(withId(R.id.event_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }
    @Test
    fun playWithBottomNavigationView(){
        onView(withId(R.id.next)).perform(click())
        onView(withId(R.id.last)).perform(click())
        onView(withId(R.id.favorites)).perform(click())
    }

    @Test
    fun addRemoveFavorites() {

        Thread.sleep(3000)
        onData(allOf()).inAdapterView(withId(R.id.event_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())

        Espresso.pressBack()

        Thread.sleep(2000)
        onView(withId(R.id.favorites)).perform(click())
        onData(allOf()).inAdapterView(withId(R.id.event_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())

        Espresso.pressBack()

    }

}
