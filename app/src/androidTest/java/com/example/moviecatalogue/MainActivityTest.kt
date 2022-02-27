package com.example.moviecatalogue

import android.view.Gravity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.moviecatalogue.utils.DummyData
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val dummyMovies = DummyData.generateDummyMovies()
    private val dummyMovieDetail = DummyData.generateDummyMovieDetail()
    private val dummyTvs = DummyData.generateDummyTvShows()
    private val dummyTvDetail = DummyData.generateDummyTvDetail()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
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

        onView(withId(R.id.img_poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_detail)).check(matches(withText(dummyMovieDetail.originalTitle)))
        onView(withId(R.id.text_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date_detail)).check(matches(withText(dummyMovieDetail.releaseDate)))
        onView(withId(R.id.text_overview_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview_detail)).check(matches(withText(dummyMovieDetail.overview)))
        onView(withId(R.id.text_genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre_detail)).check(matches(withText("Action, Adventure")))
        onView(withId(R.id.text_runtime_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_runtime_detail)).check(matches(withText("${dummyMovieDetail.runtime} minutes")))
    }

    @Test
    fun loadTvs() {
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())
        onView(withId(R.id.nav_tv)).perform(click())

        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvs.size
            )
        )
    }

    @Test
    fun loadDetailTv() {
        //go to fragment tv
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())
        onView(withId(R.id.nav_tv)).perform(click())
        //click on first item
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        //check
        onView(withId(R.id.img_poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_detail)).check(matches(withText(dummyTvDetail.name)))
        onView(withId(R.id.text_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date_detail)).check(matches(withText(dummyTvDetail.firstAirDate)))
        onView(withId(R.id.text_overview_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview_detail)).check(matches(withText(dummyTvDetail.overview)))
        onView(withId(R.id.text_genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre_detail)).check(matches(withText("Family, Animation, Comedy")))
        onView(withId(R.id.text_runtime_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_runtime_detail)).check(matches(withText("22 minutes")))
    }

    @Test
    fun loadFavoriteMovies() {
        //go to favorite activity
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())
        onView(withId(R.id.nav_favorite)).perform(click())
        //check
        onView(withId(R.id.rv_movie_favorite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_favorite)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadFavoriteDetailMovie() {
        //make favorite first by going to detail movie
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab_favorite_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite_detail)).perform(click())
        onView(withId(R.id.fab_favorite_detail)).perform(pressBack())
        //go to favorite page
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())
        onView(withId(R.id.nav_favorite)).perform(click())
        //check favorite detail
        onView(withId(R.id.rv_movie_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        //check
        onView(withId(R.id.img_poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_detail)).check(matches(withText(dummyMovieDetail.originalTitle)))
        onView(withId(R.id.text_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date_detail)).check(matches(withText(dummyMovieDetail.releaseDate)))
        onView(withId(R.id.text_overview_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview_detail)).check(matches(withText(dummyMovieDetail.overview)))
        onView(withId(R.id.text_genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre_detail)).check(matches(withText("Action, Adventure")))
        onView(withId(R.id.text_runtime_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_runtime_detail)).check(matches(withText("${dummyMovieDetail.runtime} minutes")))
    }

    @Test
    fun loadFavoriteTvs() {
        //go to favorite activity
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())
        onView(withId(R.id.nav_favorite)).perform(click())
        //go to tv shows tab
        onView(withText(R.string.menu_tv_shows)).perform(click())
        //check
        onView(withId(R.id.rv_tv_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_favorite)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvs.size
            )
        )
    }

    @Test
    fun loadFavoriteDetailTv() {
        //go to tv shows fragment
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())
        onView(withId(R.id.nav_tv)).perform(click())
        //make favorite first by going to detail tv
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab_favorite_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite_detail)).perform(click())
        onView(withId(R.id.fab_favorite_detail)).perform(pressBack())
        //go to favorite page
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())
        onView(withId(R.id.nav_favorite)).perform(click())
        //go to tv shows tab
        onView(withText(R.string.menu_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        //check
        onView(withId(R.id.img_poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_detail)).check(matches(withText(dummyTvDetail.name)))
        onView(withId(R.id.text_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date_detail)).check(matches(withText(dummyTvDetail.firstAirDate)))
        onView(withId(R.id.text_overview_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview_detail)).check(matches(withText(dummyTvDetail.overview)))
        onView(withId(R.id.text_genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre_detail)).check(matches(withText("Family, Animation, Comedy")))
        onView(withId(R.id.text_runtime_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.text_runtime_detail)).check(matches(withText("22 minutes")))
    }
}