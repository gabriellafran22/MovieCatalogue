package com.example.moviecatalogue.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.remote.ApiConfig
import com.example.moviecatalogue.data.source.remote.response.movie.MovieResponse
import com.example.moviecatalogue.utils.DummyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private val _movies = MutableLiveData<MovieResponse>()
    val movies: LiveData<MovieResponse> = _movies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getAllMovies()
    }

    private fun getAllMovies() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getMovies()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _movies.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }


    companion object {
        private const val TAG = "MovieViewModel"
    }
}