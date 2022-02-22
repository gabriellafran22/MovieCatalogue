package com.example.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.data.source.local.room.MovieCatalogueDao

class LocalDataSource private constructor(private val mMovieCatalogueDao: MovieCatalogueDao) {

    // movies
    fun getAllMovies(): LiveData<List<MovieEntity>> = mMovieCatalogueDao.getAllMovies()

    fun getMovieDetail(id: Int): LiveData<MovieEntity> = mMovieCatalogueDao.getMovieDetail(id)

    fun updateMovie(movie: MovieEntity, isFav: Boolean) {
        movie.isFavorite = isFav
        mMovieCatalogueDao.updateMovie(movie)
    }

    fun insertMovies(movies: List<MovieEntity>) = mMovieCatalogueDao.insertMovies(movies)

    fun getFavoriteMovies(): List<MovieEntity> = mMovieCatalogueDao.getFavoriteMovies()

    // tvs
    fun getAllTvs(): LiveData<List<TvEntity>> = mMovieCatalogueDao.getAllTvs()

    fun getTvDetail(id: Int): LiveData<TvEntity> = mMovieCatalogueDao.getTvDetail(id)

    fun insertTvs(tvs: List<TvEntity>) = mMovieCatalogueDao.insertTvs(tvs)

    fun updateTv(tv: TvEntity, isFav: Boolean) {
        tv.isFavorite = isFav
        mMovieCatalogueDao.updateTv(tv)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieCatalogueDao: MovieCatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieCatalogueDao)
    }

}