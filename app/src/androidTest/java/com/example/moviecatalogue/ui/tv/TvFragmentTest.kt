package com.example.moviecatalogue.ui.tv

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.DummyData
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class TvFragmentTest{

    private val dummyTvs = DummyData.generateDummyTvShows()
    private val dummyTvDetail = DummyData.generateDummyTvDetail()

    @Before
    fun setup() {
        launchFragmentInContainer<TvFragment>()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadTvs() {
        onView(ViewMatchers.withId(R.id.rv_tv_shows))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvs.results?.size ?: 0
            )
        )
    }

    @Test
    fun loadDetailTv() {
        onView(ViewMatchers.withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(ViewMatchers.withId(R.id.text_title_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.text_title_detail))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvDetail.name)))
        onView(ViewMatchers.withId(R.id.text_date_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.text_date_detail))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvDetail.firstAirDate)))
        onView(ViewMatchers.withId(R.id.text_overview_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.text_overview_detail))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvDetail.overview)))
    }
}