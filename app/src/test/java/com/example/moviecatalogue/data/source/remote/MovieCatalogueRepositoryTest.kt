package com.example.moviecatalogue.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.movie.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvDetailResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvResponse
import com.example.moviecatalogue.utils.DummyData
import com.example.moviecatalogue.utils.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class MovieCatalogueRepositoryTest{

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieCatalogueRepository = FakeMovieCatalogueRepository(remote)

    private val dummyMoviesResponse = DummyData.generateDummyMovies()
    private val movieId = 1
    private val dummyMovieDetail = DummyData.generateDummyMovieDetail()
    private val dummyTvResponses = DummyData.generateDummyTvShows()
    private val tvId = 2
    private val dummyTvDetail = DummyData.generateDummyTvDetail()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testGetAllMovies() {
        val movies = MutableLiveData<MovieResponse>()
        movies.value = dummyMoviesResponse

        `when`(movieCatalogueRepository.getAllMovies()).thenReturn(movies)
        val movieEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getAllMovies())

        verify(remote).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(dummyMoviesResponse, movieEntities)
    }

    @Test
    fun testGetMovieDetailDataFromAPI() {
        val movieDetail = MutableLiveData<MovieDetailResponse>()
        movieDetail.value = dummyMovieDetail

        `when`(movieCatalogueRepository.getMovieDetail(movieId)).thenReturn(movieDetail)
        val movieDetailResult = LiveDataTestUtil.getValue(movieCatalogueRepository.getMovieDetail(movieId))

        verify(remote).getMovieDetailDataFromAPI(movieId)
        assertNotNull(movieDetailResult)
        assertEquals(dummyMovieDetail, movieDetailResult)
    }

    @Test
    fun testGetAllTvs() {
        val tvs = MutableLiveData<TvResponse>()
        tvs.value = dummyTvResponses

        `when`(movieCatalogueRepository.getAllTvs()).thenReturn(tvs)
        val tvEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getAllTvs())

        verify(remote).getAllTvs()
        assertNotNull(tvEntities)
        assertEquals(dummyTvResponses, tvEntities)
    }

    @Test
    fun testGetTvDetailDataFromAPI() {
        val tvDetail = MutableLiveData<TvDetailResponse>()
        tvDetail.value = dummyTvDetail

        `when`(movieCatalogueRepository.getTvDetailDataFromAPI(tvId)).thenReturn(tvDetail)
        val tvDetailResult = LiveDataTestUtil.getValue(movieCatalogueRepository.getTvDetailDataFromAPI(tvId))

        verify(remote).getTvDetailDataFromAPI(tvId)
        assertNotNull(tvDetailResult)
        assertEquals(dummyTvDetail, tvDetailResult)
    }
}