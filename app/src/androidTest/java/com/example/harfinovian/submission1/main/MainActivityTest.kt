package com.example.harfinovian.submission1.main

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
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
        Thread.sleep(3000)
        onView(withId(R.id.event_list)).check(matches(isDisplayed()))

        Thread.sleep(1000)
        onView(withId(R.id.event_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.event_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        Thread.sleep(1000)
    }
    @Test
    fun playWithBottomNavigationView(){
        Thread.sleep(1000)
        onView(withId(R.id.next)).perform(click())

        Thread.sleep(1000)
        onView(withId(R.id.last)).perform(click())

        Thread.sleep(1000)
        onView(withId(R.id.favorites)).perform(click())

        Thread.sleep(1000)
    }

    @Test
    fun addRemoveFavorites() {

        Thread.sleep(1000)
        onView(withId(R.id.event_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        Thread.sleep(1000)
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())

        Thread.sleep(1000)
        Espresso.pressBack()

        Thread.sleep(1000)
        onView(withId(R.id.favorites)).perform(click())

        Thread.sleep(1000)
        onView(withId(R.id.event_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Thread.sleep(1000)
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())

        Thread.sleep(1000)
        Espresso.pressBack()

        Thread.sleep(1000)
    }

}
