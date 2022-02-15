package com.example.moviecatalogue.data.source.remote

import com.example.moviecatalogue.BuildConfig.API_KEY
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.movie.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    fun getMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<MovieDetailResponse>
}