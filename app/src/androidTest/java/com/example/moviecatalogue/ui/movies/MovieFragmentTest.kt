package com.example.moviecatalogue.ui.movies

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.DummyData
import org.junit.Before
import org.junit.Test

class MovieFragmentTest {
    private val dummyMovies = DummyData.generateDummyMovies()

    @Before
    fun setup() {
        launchFragmentInContainer<MovieFragment>()
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.text_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_detail)).check(matches(withText(dummyMovies[0].movieTitle)))
        onView(withId(R.id.text_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date_detail)).check(matches(withText(dummyMovies[0].movieReleaseDate)))
        onView(withId(R.id.text_overview_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview_detail)).check(matches(withText(dummyMovies[0].movieOverview)))
    }

}