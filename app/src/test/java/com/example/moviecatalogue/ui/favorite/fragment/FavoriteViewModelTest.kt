package com.example.moviecatalogue.ui.favorite.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.moviecatalogue.data.source.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.ui.detail.DetailViewModel
import com.example.moviecatalogue.ui.movies.MovieViewModelTest
import com.example.moviecatalogue.ui.tv.TvViewModelTest
import com.example.moviecatalogue.utils.DummyData
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest{

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var tvObserver: Observer<PagedList<TvEntity>>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(movieCatalogueRepository)
    }

    @Test
    fun testGetFavoriteMovies() {
        val movies = MoviePagedTestDataSources.snapshot(DummyData.generateDummyMovies())
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = movies

        Mockito.`when`(movieCatalogueRepository.getFavoriteMovies()).thenReturn(expected)

        viewModel.getFavoriteMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoriteMovies().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun testGetFavoriteTvs() {
        val tvs = TvPagedTestDataSources.snapshot(DummyData.generateDummyTvShows())
        val expected = MutableLiveData<PagedList<TvEntity>>()
        expected.value = tvs

        Mockito.`when`(movieCatalogueRepository.getFavoriteTvs()).thenReturn(expected)

        viewModel.getFavoriteTvs().observeForever(tvObserver)
        verify(tvObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoriteTvs().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.size, actualValue?.size)
    }

    class MoviePagedTestDataSources private constructor(private val items: List<MovieEntity>) : PositionalDataSource<MovieEntity>() {
        companion object {
            fun snapshot(items: List<MovieEntity> = listOf()): PagedList<MovieEntity> {
                return PagedList.Builder(MoviePagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MovieEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }

    class TvPagedTestDataSources private constructor(private val items: List<TvEntity>) : PositionalDataSource<TvEntity>() {
        companion object {
            fun snapshot(items: List<TvEntity> = listOf()): PagedList<TvEntity> {
                return PagedList.Builder(TvPagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TvEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}