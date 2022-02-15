package com.example.moviecatalogue.ui.tv

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvViewModelTest{

    private lateinit var viewModel: TvViewModel

    @Before
    fun setUp() {
        viewModel = TvViewModel()
    }

    @Test
    fun testGetTvShows() {
        val tv = viewModel.getTvShows()
        Assert.assertNotNull(tv)
        Assert.assertEquals(10, tv.size)
    }
}