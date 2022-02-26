package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvDetailResponse
import com.example.moviecatalogue.utils.DummyData
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
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
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvObserver: Observer<Resource<TvEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieCatalogueRepository)
    }

    @Test
    fun testGetMovieDetail() {
        val dummyMovieDetail2 = Resource.success(DummyData.generateDummyMovieDetail())
        val movieDetail = MutableLiveData<Resource<MovieEntity>>()
        movieDetail.value = dummyMovieDetail2

        Mockito.`when`(movieCatalogueRepository.getMovieDetail(movieId))
            .thenReturn(movieDetail)

        val movieEntity = viewModel.getMovieDetailData(movieId).value?.data
        verify(movieCatalogueRepository).getMovieDetail(movieId)
        assertNotNull(movieEntity)

        viewModel.getMovieDetailData(movieId).observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(dummyMovieDetail2)

        assertEquals(dummyMovieDetail.id, movieEntity?.id)
        assertEquals(dummyMovieDetail.originalTitle, movieEntity?.originalTitle)
        assertEquals(dummyMovieDetail.overview, movieEntity?.overview)
        assertEquals(dummyMovieDetail.posterPath, movieEntity?.posterPath)
        assertEquals(dummyMovieDetail.releaseDate, movieEntity?.releaseDate)
        assertEquals(dummyMovieDetail.runtime, movieEntity?.runtime)
        assertEquals(dummyMovieDetail.genres, movieEntity?.genres)
        assertEquals(dummyMovieDetail.isFavorite, movieEntity?.isFavorite)
    }

    @Test
    fun testGetTvDetail() {
        val dummyTvDetail2 = Resource.success(DummyData.generateDummyTvDetail())
        val tvDetail = MutableLiveData<Resource<TvEntity>>()
        tvDetail.value = dummyTvDetail2

        Mockito.`when`(movieCatalogueRepository.getTvDetail(tvId)).thenReturn(tvDetail)

        val tvEntity = viewModel.getTvDetailData(tvId).value?.data
        verify(movieCatalogueRepository).getTvDetail(tvId)
        assertNotNull(tvEntity)

        viewModel.getTvDetailData(tvId).observeForever(tvObserver)
        Mockito.verify(tvObserver).onChanged(dummyTvDetail2)

        assertEquals(dummyTvDetail.id, tvEntity?.id)
        assertEquals(dummyTvDetail.name, tvEntity?.name)
        assertEquals(dummyTvDetail.posterPath, tvEntity?.posterPath)
        assertEquals(dummyTvDetail.firstAirDate, tvEntity?.firstAirDate)
        assertEquals(dummyTvDetail.overview, tvEntity?.overview)
        assertEquals(dummyTvDetail.episodeRunTime, tvEntity?.episodeRunTime)
        assertEquals(dummyTvDetail.genres, tvEntity?.genres)
        assertEquals(dummyTvDetail.isFavorite, tvEntity?.isFavorite)
    }

    @Test
    fun testSetFavoriteMovie(){
        viewModel.setFavoriteMovie(DummyData.generateDummyMovieDetail(), true)
        verify(movieCatalogueRepository).setFavoriteMovie(DummyData.generateDummyMovieDetail(), true)
    }

    @Test
    fun testSetFavoriteTv(){
        viewModel.setFavoriteTv(DummyData.generateDummyTvDetail(), true)
        verify(movieCatalogueRepository).setFavoriteTv(DummyData.generateDummyTvDetail(), true)
    }
}