package com.example.moviecatalogue.ui.movies

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.DummyData
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieFragmentTest {

    private val dummyMovies = DummyData.generateDummyMovies()
    private val dummyMovieDetail = DummyData.generateDummyMovieDetail()

    @Before
    fun setup() {
        launchFragmentInContainer<MovieFragment>()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.results?.size ?: 0
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

        onView(withId(R.id.img_poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_detail)).check(matches(withText(dummyMovieDetail.originalTitle)))
        onView(withId(R.id.text_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date_detail)).check(matches(withText(dummyMovieDetail.releaseDate)))
        onView(withId(R.id.text_overview_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview_detail)).check(matches(withText(dummyMovieDetail.overview)))
        onView(withId(R.id.text_genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre_detail)).check(matches(withText("Action, Adventure, Science Fiction")))
        onView(withId(R.id.text_runtime_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_runtime_detail)).check(matches(withText("${dummyMovieDetail.runtime} minutes")))
    }

}