package com.riluq.footballmatchschedule

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(Main2Activity::class.java)

    @Test
    fun testAppBehaviour() {

        Thread.sleep(5000)
        val spinner = onView(
                allOf<View>(withId(R.id.spinner),
                        childAtPosition(
                                withParent(withId(R.id.view_pager)),
                                0),
                        isDisplayed()))
        spinner.perform(click())

        Thread.sleep(5000)
        val appCompatCheckedTextView = onData(anything())
                .inAdapterView(withClassName(`is`<String>("android.widget.ListPopupWindow\$DropDownListView")))
                .atPosition(1)
        appCompatCheckedTextView.perform(click())

        val viewPager = onView(
                allOf<View>(withId(R.id.view_pager),
                        childAtPosition(
                                allOf<View>(withId(R.id.main_content),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()))
        viewPager.perform(swipeLeft())

        Thread.sleep(5000)
        val actionMenuItemView = onView(
                allOf<View>(withId(R.id.searchMenu), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()))
        actionMenuItemView.perform(click())

        Thread.sleep(5000)
        val searchAutoComplete = onView(
                allOf<View>(withId(R.id.search_src_text),
                        childAtPosition(
                                allOf<View>(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()))
        searchAutoComplete.perform(replaceText("barcelona"), closeSoftKeyboard())

        Thread.sleep(5000)
        val searchAutoComplete2 = onView(
                allOf<View>(withId(R.id.search_src_text), withText("barcelona"),
                        childAtPosition(
                                allOf<View>(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()))
        searchAutoComplete2.perform(pressImeActionButton())

        Thread.sleep(5000)
        val recyclerView = onView(
                allOf<View>(withId(R.id.rv_search),
                        childAtPosition(
                                withClassName(`is`<String>("android.widget.RelativeLayout")),
                                0)))
        recyclerView.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Thread.sleep(5000)
        val actionMenuItemView2 = onView(
                allOf<View>(withId(R.id.add_to_favorite), withContentDescription("Favorites"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView2.perform(click())


        val appCompatImageButton = onView(
                allOf<View>(withContentDescription("Navigasi naik"),
                        childAtPosition(
                                allOf<View>(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton.perform(click())

        val appCompatImageButton2 = onView(
                allOf<View>(withContentDescription("Navigasi naik"),
                        childAtPosition(
                                allOf<View>(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton2.perform(click())

        val appCompatImageButton3 = onView(
                allOf<View>(withContentDescription("Ciutkan"),
                        childAtPosition(
                                allOf<View>(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.appbar),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton3.perform(click())

        val bottomNavigationItemView = onView(
                allOf<View>(withId(R.id.navigation_next_match), withContentDescription("Teams"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation_dua),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView.perform(click())

        Thread.sleep(5000)
        val _LinearLayout = onView(
                allOf<View>(childAtPosition(
                        allOf<View>(withId(R.id.list_team),
                                childAtPosition(
                                        withClassName(`is`<String>("org.jetbrains.anko._RelativeLayout")),
                                        0)),
                        0),
                        isDisplayed()))
        _LinearLayout.perform(click())

        Thread.sleep(5000)
        val tabView = onView(
                allOf<View>(withContentDescription("Players"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()))
        tabView.perform(click())


        Thread.sleep(5000)
        val cardView = onView(
                allOf<View>(childAtPosition(
                        allOf<View>(withId(R.id.list_player),
                                childAtPosition(
                                        withClassName(`is`<String>("org.jetbrains.anko._LinearLayout")),
                                        0)),
                        0),
                        isDisplayed()))
        cardView.perform(click())

        val appCompatImageButton4 = onView(
                allOf<View>(withContentDescription("Navigasi naik"),
                        childAtPosition(
                                allOf<View>(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton4.perform(click())

        val actionMenuItemView3 = onView(
                allOf<View>(withId(R.id.add_to_favorite), withContentDescription("Favorites"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView3.perform(click())

        val appCompatImageButton5 = onView(
                allOf<View>(withContentDescription("Navigasi naik"),
                        childAtPosition(
                                allOf<View>(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.appbar),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton5.perform(click())

        val bottomNavigationItemView2 = onView(
                allOf<View>(withId(R.id.navigation_favorite), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation_dua),
                                        0),
                                2),
                        isDisplayed()))
        bottomNavigationItemView2.perform(click())


        Thread.sleep(5000)
        val cardView2 = onView(
                allOf<View>(childAtPosition(
                        allOf<View>(withId(R.id.rv_prev_match),
                                childAtPosition(
                                        withClassName(`is`<String>("org.jetbrains.anko._RelativeLayout")),
                                        0)),
                        0),
                        isDisplayed()))
        cardView2.perform(click())

        Thread.sleep(5000)
        val actionMenuItemView4 = onView(
                allOf<View>(withId(R.id.add_to_favorite), withContentDescription("Favorites"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView4.perform(click())

        val appCompatImageButton6 = onView(
                allOf<View>(withContentDescription("Navigasi naik"),
                        childAtPosition(
                                allOf<View>(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton6.perform(click())

        val tabView2 = onView(
                allOf<View>(withContentDescription("Teams"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()))
        tabView2.perform(click())

        Thread.sleep(5000)
        val _LinearLayout2 = onView(
                allOf<View>(childAtPosition(
                        allOf<View>(withId(R.id.rv_prev_match),
                                childAtPosition(
                                        withClassName(`is`<String>("org.jetbrains.anko._RelativeLayout")),
                                        0)),
                        0),
                        isDisplayed()))
        _LinearLayout2.perform(click())

        Thread.sleep(5000)
        val actionMenuItemView5 = onView(
                allOf<View>(withId(R.id.add_to_favorite), withContentDescription("Favorites"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView5.perform(click())

        val appCompatImageButton7 = onView(
                allOf<View>(withContentDescription("Navigasi naik"),
                        childAtPosition(
                                allOf<View>(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.appbar),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton7.perform(click())

        val bottomNavigationItemView3 = onView(
                allOf<View>(withId(R.id.navigation_next_match), withContentDescription("Teams"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation_dua),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView3.perform(click())


        val actionMenuItemView6 = onView(
                allOf<View>(withId(R.id.searchMenu), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        0),
                                0),
                        isDisplayed()))
        actionMenuItemView6.perform(click())

        val searchAutoComplete3 = onView(
                allOf<View>(withId(R.id.search_src_text),
                        childAtPosition(
                                allOf<View>(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()))
        searchAutoComplete3.perform(replaceText("b"), closeSoftKeyboard())

        val searchAutoComplete4 = onView(
                allOf<View>(withId(R.id.search_src_text), withText("b"),
                        childAtPosition(
                                allOf<View>(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()))
        searchAutoComplete4.perform(pressImeActionButton())

        Thread.sleep(5000)
        val recyclerView2 = onView(
                allOf<View>(withId(R.id.rv_search),
                        childAtPosition(
                                withClassName(`is`<String>("android.widget.RelativeLayout")),
                                0)))
        recyclerView2.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        Thread.sleep(5000)

//        Thread.sleep(10000)
//
//        onView(withId(rv_prev_match))
//                .check(matches(isDisplayed()))
//        onView(withId(rv_prev_match))
//                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
//
//        Thread.sleep(3000)
//
//        onView(withId(add_to_favorite))
//                .check(matches(isDisplayed()))
//        onView(withId(add_to_favorite)).perform(click())
//        onView(withText("Added to favorite"))
//                .check(matches(isDisplayed()))
//        pressBack()
//
//        onView(withId(rv_prev_match))
//                .perform(swipeDown())
//
//        Thread.sleep(3000)
//
//        onView(withId(rv_prev_match))
//                .perform(scrollToPosition<RecyclerView.ViewHolder>(10))
//        onView(withId(rv_prev_match))
//                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
//
//        Thread.sleep(3000)
//
//        onView(withId(add_to_favorite))
//                .check(matches(isDisplayed()))
//        onView(withId(add_to_favorite)).perform(click())
//        onView(withText("Added to favorite"))
//                .check(matches(isDisplayed()))
//        pressBack()
//
//        onView(withId(navigation))
//                .check(matches(isDisplayed()))
//        onView(withId(navigation_next_match))
//                .perform(click())
//
//        onView(withId(rv_prev_match))
//                .check(matches(isDisplayed()))
//        onView(withId(rv_prev_match))
//                .perform(scrollToPosition<RecyclerView.ViewHolder>(5))
//        onView(withId(rv_prev_match))
//                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
//
//        Thread.sleep(3000)
//
//        onView(withId(add_to_favorite))
//                .check(matches(isDisplayed()))
//        onView(withId(add_to_favorite)).perform(click())
//        onView(withText("Added to favorite"))
//                .check(matches(isDisplayed()))
//        pressBack()
//
//        Thread.sleep(3000)
//
//        onView(withId(navigation_favorite))
//                .perform(click())
//
//        onView(withId(rv_prev_match))
//                .check(matches(isDisplayed()))
//        onView(withId(rv_prev_match))
//                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
//
//        Thread.sleep(3000)
//
//        onView(withId(add_to_favorite))
//                .check(matches(isDisplayed()))
//        onView(withId(add_to_favorite)).perform(click())
//        onView(withText("Removed to favorite"))
//                .check(matches(isDisplayed()))
//        pressBack()
//        onView(withId(rv_prev_match))
//                .perform(swipeDown())
//        Thread.sleep(3000)

    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }

}