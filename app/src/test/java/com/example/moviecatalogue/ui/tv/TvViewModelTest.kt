package com.example.moviecatalogue.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.remote.response.tv.TvResponse
import com.example.moviecatalogue.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<TvResponse>

    @Before
    fun setUp() {
        viewModel = TvViewModel(movieCatalogueRepository)
    }

//    @Test
//    fun testGetTvs() {
//        val dummyTvs = DummyData.generateDummyTvShows()
//        val tvs = MutableLiveData<TvResponse>()
//        tvs.value = dummyTvs
//
//        Mockito.`when`(movieCatalogueRepository.getAllTvs()).thenReturn(tvs)
//
//        val tvEntities = viewModel.getAllTvs().value
//        verify(movieCatalogueRepository).getAllTvs()
//        Assert.assertNotNull(tvEntities)
//
//        viewModel.getAllTvs().observeForever(observer)
//        Mockito.verify(observer).onChanged(dummyTvs)
//        Assert.assertEquals(1, tvEntities?.results?.size)
//    }
}