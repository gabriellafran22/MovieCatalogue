package com.example.moviecatalogue.ui.favorite.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.moviecatalogue.R
import com.example.moviecatalogue.ui.tv.TvFragment
import com.example.moviecatalogue.utils.DummyData
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test


class FavoriteTvFragmentTest {
    private val dummyTvs = DummyData.generateDummyTvShows()
    private val dummyTvDetail = DummyData.generateDummyTvDetail()

    @Before
    fun setup() {
        launchFragmentInContainer<FavoriteTvFragment>()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadFavoriteTvs() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_favorite))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_favorite)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvs.size
            )
        )
    }

    @Test
    fun loadDetailTv() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.img_poster_detail)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.fab_favorite_detail)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.text_title_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.text_title_detail))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvDetail.name)))
        Espresso.onView(ViewMatchers.withId(R.id.text_date_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.text_date_detail))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvDetail.firstAirDate)))
        Espresso.onView(ViewMatchers.withId(R.id.text_overview_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.text_overview_detail))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvDetail.overview)))
        Espresso.onView(ViewMatchers.withId(R.id.text_genre_detail)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.text_genre_detail)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Family, Animation, Comedy")
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.text_runtime_detail)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.text_runtime_detail)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("22 minutes")
            )
        )
    }
}