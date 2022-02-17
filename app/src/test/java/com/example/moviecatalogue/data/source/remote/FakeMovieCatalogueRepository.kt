package com.example.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.movie.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvDetailResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvResponse

class FakeMovieCatalogueRepository (private val remoteDataSource: RemoteDataSource) :
    MovieCatalogueDataSource {

    override fun getAllMovies(): LiveData<MovieResponse> {
        return remoteDataSource.getAllMovies()
    }

    override fun getMovieDetailDataFromAPI(id: Int): LiveData<MovieDetailResponse> {
        return remoteDataSource.getMovieDetailDataFromAPI(id)
    }

    override fun getAllTvs(): LiveData<TvResponse> {
        return remoteDataSource.getAllTvs()
    }

    override fun getTvDetailDataFromAPI(id: Int): LiveData<TvDetailResponse> {
        return remoteDataSource.getTvDetailDataFromAPI(id)
    }
}