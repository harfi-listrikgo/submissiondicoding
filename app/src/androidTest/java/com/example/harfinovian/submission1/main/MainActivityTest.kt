package com.example.harfinovian.submission1.main

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
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
        onView(withId(view_pager))
                .check(matches(isDisplayed()))
        delay()

        onView(allOf(withId(spinner_league), isDisplayed())).check(matches(isDisplayed()))
        onView(allOf(withId(spinner_league), isDisplayed())).perform(click())

        delay()
        onView(allOf(withText("Spanish La Liga"), isDisplayed())).perform(click())

        delay()
        onView(allOf(withId(event_list), isDisplayed())).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(allOf(withId(event_list), isDisplayed())).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))

        delay()
        onView(withId(add_to_favorite)).perform(click())

        Espresso.pressBack()
        delay()

        // Teams
        onView(withId(team)).perform(click())
        delay()

        onView(allOf(withId(team_list), isDisplayed())).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(allOf(withId(team_list), isDisplayed())).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))

        delay()
        onView(allOf(withId(view_pager), isDisplayed())).perform(ViewActions.swipeLeft())

        delay()
        onView(allOf(withId(player_list), isDisplayed())).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(allOf(withId(player_list), isDisplayed())).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))

        delay()
        delay()
        Espresso.pressBack()

        delay()
        onView(withId(add_to_favorite)).perform(click())

        Espresso.pressBack()
        delay()

        // Favorite
        onView(withId(favorites)).perform(click())
        delay()

        onView(allOf(withId(event_list), isDisplayed())).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(allOf(withId(event_list), isDisplayed())).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        delay()
        onView(withId(add_to_favorite)).perform(click())

        Espresso.pressBack()
        delay()

        onView(allOf(withId(refreshLayout), isDisplayed())).perform(ViewActions.swipeDown())

        delay()
        onView(withId(match)).perform(click())

        delay()
        onView(withId(actionSearch)).perform(click())

        delay()
        onView(withId(android.support.design.R.id.search_src_text))
                .perform(typeText("Arsenal"), closeSoftKeyboard())

        delay()
        onView(withId(android.support.design.R.id.search_src_text)).perform(pressImeActionButton())

        delay()
        onView(withId(football_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(football_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        delay()
        Espresso.pressBack()

        delay()
        Espresso.pressBack()
    }

    private fun delay(){
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}
