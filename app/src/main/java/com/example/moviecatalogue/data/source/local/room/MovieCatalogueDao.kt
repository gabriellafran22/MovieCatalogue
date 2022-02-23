package com.example.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvEntity

@Dao
interface MovieCatalogueDao {

    // movies
    @Query("SELECT * FROM movie_entities")
    fun getAllMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE id = :id")
    fun getMovieDetail(id: Int): LiveData<MovieEntity>

    @Update
    fun updateMovie(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieEntity: List<MovieEntity>)

    @Query("SELECT * FROM movie_entities WHERE isFavorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    // tvs
    @Query("SELECT * FROM tv_entities")
    fun getAllTvs(): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM tv_entities WHERE id = :id")
    fun getTvDetail(id: Int): LiveData<TvEntity>

    @Update
    fun updateTv(tvEntity: TvEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvs(tvEntity: List<TvEntity>)

    @Query("SELECT * FROM tv_entities WHERE isFavorite = 1")
    fun getFavoriteTvs(): DataSource.Factory<Int, TvEntity>

}