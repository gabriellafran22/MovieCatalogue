package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.remote.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvDetailResponse
import com.example.moviecatalogue.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val movieId = 1
    private val tvId = 2
    private val dummyMovieDetail = DummyData.generateDummyMovieDetail()
    private val dummyTvDetail = DummyData.generateDummyTvDetail()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieDetailResponse>

    @Mock
    private lateinit var tvObserver: Observer<TvDetailResponse>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieCatalogueRepository)
    }

    @Test
    fun testGetMovieDetail() {
        val dummyMovieDetail2 = DummyData.generateDummyMovieDetail()
        val movieDetail = MutableLiveData<MovieDetailResponse>()
        movieDetail.value = dummyMovieDetail2

        Mockito.`when`(movieCatalogueRepository.getMovieDetailDataFromAPI(movieId))
            .thenReturn(movieDetail)

        val movieEntity = viewModel.getMovieDetailDataFromAPI(movieId).value
        verify(movieCatalogueRepository).getMovieDetailDataFromAPI(movieId)
        assertNotNull(movieEntity)

        viewModel.getMovieDetailDataFromAPI(movieId).observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(dummyMovieDetail2)

        assertEquals(dummyMovieDetail.id, movieEntity?.id)
        assertEquals(dummyMovieDetail.originalLanguage, movieEntity?.originalLanguage)
        assertEquals(dummyMovieDetail.originalTitle, movieEntity?.originalTitle)
        assertEquals(dummyMovieDetail.budget, movieEntity?.budget)
        assertEquals(dummyMovieDetail.adult, movieEntity?.adult)
        assertEquals(dummyMovieDetail.voteAverage, movieEntity?.voteAverage)
        assertEquals(dummyMovieDetail.voteCount, movieEntity?.voteCount)
    }

    @Test
    fun testGetTvDetail() {
        val dummyTvDetail2 = DummyData.generateDummyTvDetail()
        val tvDetail = MutableLiveData<TvDetailResponse>()
        tvDetail.value = dummyTvDetail2

        Mockito.`when`(movieCatalogueRepository.getTvDetailDataFromAPI(tvId)).thenReturn(tvDetail)

        val tvEntity = viewModel.getTvDetailDataFromAPI(tvId).value
        verify(movieCatalogueRepository).getTvDetailDataFromAPI(tvId)
        assertNotNull(tvEntity)

        viewModel.getTvDetailDataFromAPI(tvId).observeForever(tvObserver)
        Mockito.verify(tvObserver).onChanged(dummyTvDetail2)

        assertEquals(dummyTvDetail.id, tvEntity?.id)
        assertEquals(dummyTvDetail.originalLanguage, tvEntity?.originalLanguage)
        assertEquals(dummyTvDetail.adult, tvEntity?.adult)
        assertEquals(dummyTvDetail.voteAverage, tvEntity?.voteAverage)
        assertEquals(dummyTvDetail.voteCount, tvEntity?.voteCount)
        assertEquals(dummyTvDetail.episodeRunTime, tvEntity?.episodeRunTime)
        assertEquals(dummyTvDetail.homepage, tvEntity?.homepage)
        assertEquals(dummyTvDetail.nextEpisodeToAir, tvEntity?.nextEpisodeToAir)
        assertEquals(dummyTvDetail.createdBy, tvEntity?.createdBy)
    }
}