package com.example.moviecatalogue.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvEntity
import com.example.moviecatalogue.data.source.remote.ApiConfig
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.utils.DummyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetailResponse>()
    val movieDetail: LiveData<MovieDetailResponse> = _movieDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getMovieDetailDataFromAPI(id: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getMovieDetail(id)
        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) _movieDetail.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object {
        private val TAG = "DetailVieModel"
    }


//    private lateinit var movieId: String
//    private lateinit var tvId: String
//
//    fun setSelectedMovie(movieId: String) {
//        this.movieId = movieId
//    }
//
//    fun getMovie(): MovieEntity? {
//        var movie: MovieEntity? = null
//        for (movieEntity in DummyData.generateDummyMovies()) {
//            if (movieEntity.movieId == movieId) {
//                movie = movieEntity
//            }
//        }
//        return movie
//    }
//
//    fun setSelectedTv(tvId: String) {
//        this.tvId = tvId
//    }
//
//    fun getTv(): TvEntity? {
//        var tv: TvEntity? = null
//        for (tvEntity in DummyData.generateDummyTvShows()) {
//            if (tvEntity.tvId == tvId) {
//                tv = tvEntity
//            }
//        }
//        return tv
//    }
}