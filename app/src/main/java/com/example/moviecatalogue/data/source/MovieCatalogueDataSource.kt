package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.vo.Resource

interface MovieCatalogueDataSource {
    //movies
    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieDetail(id: Int): LiveData<Resource<MovieEntity>>
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>
    fun setFavoriteMovie(movieEntity: MovieEntity, isFav: Boolean)
    //tvs
    fun getAllTvs(): LiveData<Resource<PagedList<TvEntity>>>
    fun getTvDetail(id: Int): LiveData<Resource<TvEntity>>
    fun getFavoriteTvs():  LiveData<PagedList<TvEntity>>
    fun setFavoriteTv(tvEntity: TvEntity, isFav: Boolean)

}