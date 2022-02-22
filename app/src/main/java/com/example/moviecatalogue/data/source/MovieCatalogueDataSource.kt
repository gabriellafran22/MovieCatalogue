package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.movie.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvDetailResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvResponse
import com.example.moviecatalogue.vo.Resource

interface MovieCatalogueDataSource {
    fun getAllMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getMovieDetail(id: Int): LiveData<Resource<MovieEntity>>
    fun getAllTvs(): LiveData<Resource<List<TvEntity>>>
    fun getTvDetailDataFromAPI(id: Int): LiveData<Resource<TvEntity>>
    fun setFavoriteMovie(movieEntity: MovieEntity, isFav: Boolean)
    fun setFavoriteTv(tvEntity: TvEntity, isFav: Boolean)
    fun getFavoriteMovies(): LiveData<List<MovieEntity>>
    fun getFavoriteTvs():  LiveData<List<TvEntity>>
}