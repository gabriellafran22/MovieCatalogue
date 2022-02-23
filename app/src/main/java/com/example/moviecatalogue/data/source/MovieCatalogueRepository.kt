package com.example.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.data.NetworkBoundResource
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.data.source.remote.ApiResponse
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.movie.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvDetailResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvResponse
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.movieGenreToString
import com.example.moviecatalogue.utils.tvGenreToString
import com.example.moviecatalogue.utils.tvRuntimeToString
import com.example.moviecatalogue.vo.Resource

class MovieCatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MovieCatalogueDataSource {

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, MovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4).setPageSize(4).build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                val res = data == null || data.isEmpty()
                Log.i("movieResp", data.toString())
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> {
                return remoteDataSource.getAllMovies()
            }

            override fun saveCallResult(data: MovieResponse) {
                val moviesList = ArrayList<MovieEntity>()
                for (movieResult in data.results!!) {
                    val movie = MovieEntity(
                        movieResult?.id ?: 0,
                        movieResult?.originalTitle ?: "",
                        movieResult?.releaseDate ?: "",
                        movieResult?.overview ?: "",
                        movieResult?.posterPath ?: "",
                        "",
                        "",
                        false,
                    )
                    moviesList.add(movie)
                }
                localDataSource.insertMovies(moviesList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getMovieDetail(id)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null || data.runtime == "" || data.genres == ""
            }

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> {
                return remoteDataSource.getMovieDetailDataFromAPI(id)
            }

            override fun saveCallResult(data: MovieDetailResponse) {
                val genre = movieGenreToString(data.genres)
                val movie = MovieEntity(
                    data.id ?: 0,
                    data.originalTitle ?: "",
                    data.releaseDate ?: "",
                    data.overview ?: "",
                    data.posterPath ?: "",
                    genre,
                    data.runtime.toString(),
                    false,
                )
                Log.i("movieRepo", movie.toString())

                localDataSource.updateMovie(movie, false)
            }

        }.asLiveData()
    }

    override fun getAllTvs(): LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, TvResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder().setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4).setPageSize(4).build()
                return LivePagedListBuilder(localDataSource.getAllTvs(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<TvResponse>> {
                return remoteDataSource.getAllTvs()
            }

            override fun saveCallResult(data: TvResponse) {
                val tvList = ArrayList<TvEntity>()
                for (tvResult in data.results!!) {
                    val tv = TvEntity(
                        tvResult?.id ?: 0,
                        tvResult?.name ?: "",
                        tvResult?.firstAirDate ?: "",
                        tvResult?.overview ?: "",
                        tvResult?.posterPath ?: "",
                        "",
                        "",
                        false,
                    )
                    tvList.add(tv)
                }
                localDataSource.insertTvs(tvList)
            }

        }.asLiveData()
    }

    override fun getTvDetail(id: Int): LiveData<Resource<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, TvDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvEntity> {
                return localDataSource.getTvDetail(id)
            }

            override fun shouldFetch(data: TvEntity?): Boolean {
                return data == null || data.episodeRunTime == "" || data.genres == ""
            }

            override fun createCall(): LiveData<ApiResponse<TvDetailResponse>> {
                return remoteDataSource.getTvDetailDataFromAPI(id)
            }

            override fun saveCallResult(data: TvDetailResponse) {
                val genre = tvGenreToString(data.genres)
                val runtime = tvRuntimeToString(data.episodeRunTime)
                val tv = TvEntity(
                    data.id ?: 0,
                    data.name ?: "",
                    data.firstAirDate ?: "",
                    data.overview ?: "",
                    data.posterPath ?: "",
                    genre,
                    runtime,
                    false,
                )

                localDataSource.updateTv(tv, false)
            }

        }.asLiveData()
    }

    override fun setFavoriteMovie(movieEntity: MovieEntity, isFav: Boolean) {
        return appExecutors.diskIO().execute {
            localDataSource.updateMovie(movieEntity, isFav)
        }
    }

    override fun setFavoriteTv(tvEntity: TvEntity, isFav: Boolean) {
        return appExecutors.diskIO().execute {
            localDataSource.updateTv(tvEntity, isFav)
        }
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4).setPageSize(4).build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvs(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4).setPageSize(4).build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvs(), config).build()
    }

    companion object {
        @Volatile
        private var instance: MovieCatalogueRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            executors: AppExecutors
        ): MovieCatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: MovieCatalogueRepository(remoteData, localData, executors)
            }
    }
}