package com.example.moviecatalogue.ui.tv

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.TvEntity
import com.example.moviecatalogue.utils.DummyData

class TvViewModel : ViewModel() {

    fun getTvShows(): List<TvEntity> = DummyData.generateDummyTvShows()
}