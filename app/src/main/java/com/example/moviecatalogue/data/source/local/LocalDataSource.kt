package com.example.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.data.source.local.room.MovieCatalogueDao

class LocalDataSource private constructor(private val mMovieCatalogueDao: MovieCatalogueDao) {

    // movies
    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mMovieCatalogueDao.getAllMovies()

    fun getMovieDetail(id: Int): LiveData<MovieEntity> = mMovieCatalogueDao.getMovieDetail(id)

    fun updateMovie(movie: MovieEntity, isFav: Boolean) {
        movie.isFavorite = isFav
        mMovieCatalogueDao.updateMovie(movie)
    }

    fun insertMovies(movies: List<MovieEntity>) = mMovieCatalogueDao.insertMovies(movies)

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> =
        mMovieCatalogueDao.getFavoriteMovies()

    // tvs
    fun getAllTvs(): DataSource.Factory<Int, TvEntity> = mMovieCatalogueDao.getAllTvs()

    fun getTvDetail(id: Int): LiveData<TvEntity> = mMovieCatalogueDao.getTvDetail(id)

    fun insertTvs(tvs: List<TvEntity>) = mMovieCatalogueDao.insertTvs(tvs)

    fun updateTv(tv: TvEntity, isFav: Boolean) {
        tv.isFavorite = isFav
        mMovieCatalogueDao.updateTv(tv)
    }

    fun getFavoriteTvs(): DataSource.Factory<Int, TvEntity> = mMovieCatalogueDao.getFavoriteTvs()


    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieCatalogueDao: MovieCatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieCatalogueDao)

    }

}