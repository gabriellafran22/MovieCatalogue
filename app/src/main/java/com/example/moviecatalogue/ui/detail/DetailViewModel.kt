package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvDetailResponse
import com.example.moviecatalogue.vo.Resource

class DetailViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    fun getMovieDetailData(id: Int): LiveData<Resource<MovieEntity>> =
        movieCatalogueRepository.getMovieDetail(id)

    fun getTvDetailData(id: Int): LiveData<Resource<TvEntity>> =
        movieCatalogueRepository.getTvDetailDataFromAPI(id)

}