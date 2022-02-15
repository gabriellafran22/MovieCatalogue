package com.example.moviecatalogue.ui.detail

import com.example.moviecatalogue.utils.DummyData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Assert.assertNotNull
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummmyMovie = DummyData.generateDummyMovies()[0]
    private val dummmyTv = DummyData.generateDummyTvShows()[0]

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun testGetMovie() {
        viewModel.setSelectedMovie(dummmyMovie.movieId)
        val movie = viewModel.getMovie()
        assertNotNull(movie)
        assertEquals(dummmyMovie.movieId, movie?.movieId)
        assertEquals(dummmyMovie.moviePoster, movie?.moviePoster)
        assertEquals(dummmyMovie.movieOverview, movie?.movieOverview)
        assertEquals(dummmyMovie.movieReleaseDate, movie?.movieReleaseDate)
        assertEquals(dummmyMovie.movieTitle, movie?.movieTitle)
    }

    @Test
    fun testGetTv() {
        viewModel.setSelectedTv(dummmyTv.tvId)
        val tv = viewModel.getTv()
        assertNotNull(tv)
        assertEquals(dummmyTv.tvId, tv?.tvId)
        assertEquals(dummmyTv.tvPoster, tv?.tvPoster)
        assertEquals(dummmyTv.tvOverview, tv?.tvOverview)
        assertEquals(dummmyTv.tvReleaseDate, tv?.tvReleaseDate)
        assertEquals(dummmyTv.tvTitle, tv?.tvTitle)
    }
}