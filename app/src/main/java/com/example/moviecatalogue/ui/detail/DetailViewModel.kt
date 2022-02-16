package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.remote.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvDetailResponse

class DetailViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    fun getMovieDetailDataFromAPI(id: Int): LiveData<MovieDetailResponse> =
        movieCatalogueRepository.getMovieDetailDataFromAPI(id)

    fun getTvDetailDataFromAPI(id: Int): LiveData<TvDetailResponse> =
        movieCatalogueRepository.getTvDetailDataFromAPI(id)

}