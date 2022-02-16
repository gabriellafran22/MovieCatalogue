package com.example.moviecatalogue.ui.tv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.TvEntity
import com.example.moviecatalogue.data.source.remote.ApiConfig
import com.example.moviecatalogue.data.source.remote.response.tv.TvResponse
import com.example.moviecatalogue.utils.DummyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvViewModel : ViewModel() {

    //    fun getTvShows(): List<TvEntity> = DummyData.generateDummyTvShows()
    private val _tvs = MutableLiveData<TvResponse>()
    val tvs: LiveData<TvResponse> = _tvs

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getAllTvs()
    }

    private fun getAllTvs() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getTvs()
        client.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _tvs.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }


    companion object {
        private const val TAG = "TvViewModel"
    }
}
