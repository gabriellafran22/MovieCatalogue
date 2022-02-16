package com.example.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.remote.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.remote.response.tv.TvResponse

class TvViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getAllTvs(): LiveData<TvResponse> = movieCatalogueRepository.getAllTvs()

}
