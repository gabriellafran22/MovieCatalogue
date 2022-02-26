package com.example.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.DummyData
import com.example.moviecatalogue.utils.LiveDataTestUtil
import com.example.moviecatalogue.utils.PagedListUtil
import com.example.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MovieCatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieCatalogueRepository = FakeMovieCatalogueRepository(remote, local, appExecutors)

    private val dummyMoviesResponse = DummyData.generateDummyMovies()
    private val movieId = 1
    private val dummyMovieDetail = DummyData.generateDummyMovieDetail()
    private val dummyTvResponses = DummyData.generateDummyTvShows()
    private val tvId = 2
    private val dummyTvDetail = DummyData.generateDummyTvDetail()
    private val movieDataSourceFactory =
        mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
    val tvDataSourceFactory =
        mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>

    @Test
    fun testGetAllMovies() {
        `when`(local.getAllMovies()).thenReturn(movieDataSourceFactory)
        movieCatalogueRepository.getAllMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(dummyMoviesResponse))
        verify(local).getAllMovies()

        assertNotNull(movieEntities)
        assertNotNull(movieEntities.data)
        assertEquals(dummyMoviesResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun testGetMovieDetailData() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = dummyMovieDetail
        `when`(local.getMovieDetail(movieId)).thenReturn(dummyDetail)

        val movieDetailEntity =
            LiveDataTestUtil.getValue(movieCatalogueRepository.getMovieDetail(movieId))
        verify(local).getMovieDetail(movieId)
        assertNotNull(movieDetailEntity)
        assertEquals(dummyMovieDetail, movieDetailEntity.data)
    }

    @Test
    fun testGetAllTvs() {
        `when`(local.getAllTvs()).thenReturn(tvDataSourceFactory)
        movieCatalogueRepository.getAllTvs()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(dummyTvResponses))
        verify(local).getAllTvs()
        assertNotNull(tvShowEntities)
        assertEquals(dummyTvResponses.size, tvShowEntities.data?.size)
    }

    @Test
    fun testGetTvDetailData() {
        val dummyDetail = MutableLiveData<TvEntity>()
        dummyDetail.value = dummyTvDetail
        `when`(local.getTvDetail(tvId)).thenReturn(dummyDetail)

        val tvDetailEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getTvDetail(tvId))
        verify(local).getTvDetail(tvId)
        assertNotNull(tvDetailEntity)
        assertEquals(dummyTvDetail, tvDetailEntity.data)
    }

    @Test
    fun getFavoriteMovies() {
        `when`(local.getFavoriteMovies()).thenReturn(movieDataSourceFactory)
        movieCatalogueRepository.getFavoriteMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(dummyMoviesResponse))
        verify(local).getFavoriteMovies()

        assertNotNull(movieEntities)
        assertEquals(dummyMoviesResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun setFavoriteMovieTrue() {
        movieCatalogueRepository.setFavoriteMovie(dummyMovieDetail, true)
        verify(local).updateMovie(dummyMovieDetail, true)
    }

    @Test
    fun setFavoriteMovieFalse() {
        movieCatalogueRepository.setFavoriteMovie(dummyMovieDetail, false)
        verify(local).updateMovie(dummyMovieDetail, false)
    }

    @Test
    fun getFavoriteTvShows() {
        `when`(local.getFavoriteTvs()).thenReturn(tvDataSourceFactory)
        movieCatalogueRepository.getFavoriteTvs()

        val tvEntities =
            Resource.success(PagedListUtil.mockPagedList(dummyTvResponses))
        verify(local).getFavoriteTvs()

        assertNotNull(tvEntities)
        assertEquals(dummyTvResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun setFavoriteTvTrue() {
        movieCatalogueRepository.setFavoriteTv(dummyTvDetail, true)
        verify(local).updateTv(dummyTvDetail, true)
    }

    @Test
    fun setFavoriteTvFalse() {
        movieCatalogueRepository.setFavoriteTv(dummyTvDetail, false)
        verify(local).updateTv(dummyTvDetail, false)
    }
}