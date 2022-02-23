package com.example.moviecatalogue.ui.tv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.data.source.remote.response.tv.TvResponse
import com.example.moviecatalogue.vo.Resource

class TvViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getAllTvs(): LiveData<Resource<PagedList<TvEntity>>> {
        Log.i("tvFragment1", movieCatalogueRepository.getAllTvs().toString())
        return movieCatalogueRepository.getAllTvs()
    }

}
