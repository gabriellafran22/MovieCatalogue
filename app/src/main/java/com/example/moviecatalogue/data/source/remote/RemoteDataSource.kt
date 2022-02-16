package com.example.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.remote.api.ApiConfig
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.movie.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvDetailResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvResponse
import com.example.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor() {
    companion object {

        private val TAG = "RemoteDataSource"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getAllMovies(): LiveData<MovieResponse> {
        EspressoIdlingResource.increment()
        val movies = MutableLiveData<MovieResponse>()
        val client = ApiConfig.getApiService().getMovies()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    movies.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "getMovies onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })

        return movies
    }


    fun getAllTvs(): LiveData<TvResponse> {
        EspressoIdlingResource.increment()
        val tvs = MutableLiveData<TvResponse>()
        val client = ApiConfig.getApiService().getTvs()
        client.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    tvs.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }

        })

        return tvs
    }

    fun getMovieDetailDataFromAPI(id: Int): LiveData<MovieDetailResponse> {
        EspressoIdlingResource.increment()
        val movieDetail = MutableLiveData<MovieDetailResponse>()
        val client = ApiConfig.getApiService().getMovieDetail(id)
        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        movieDetail.value = response.body()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }

        })

        return movieDetail
    }

    fun getTvDetailDataFromAPI(id: Int): LiveData<TvDetailResponse> {
        EspressoIdlingResource.increment()
        val tvDetail = MutableLiveData<TvDetailResponse>()
        val client = ApiConfig.getApiService().getTvDetail(id)
        client.enqueue(object : Callback<TvDetailResponse> {
            override fun onResponse(
                call: Call<TvDetailResponse>,
                response: Response<TvDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        tvDetail.value = response.body()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }

        })

        return tvDetail
    }
}