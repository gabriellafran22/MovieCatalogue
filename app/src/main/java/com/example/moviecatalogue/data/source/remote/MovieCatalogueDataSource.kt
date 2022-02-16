package com.example.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.movie.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvDetailResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvResponse

interface MovieCatalogueDataSource {
    fun getAllMovies(): LiveData<MovieResponse>
    fun getMovieDetailDataFromAPI(id: Int): LiveData<MovieDetailResponse>
    fun getAllTvs(): LiveData<TvResponse>
    fun getTvDetailDataFromAPI(id: Int): LiveData<TvDetailResponse>
}