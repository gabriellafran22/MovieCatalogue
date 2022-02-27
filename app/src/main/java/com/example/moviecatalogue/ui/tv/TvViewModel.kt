package com.example.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.vo.Resource

class TvViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getAllTvs(): LiveData<Resource<PagedList<TvEntity>>> {
        return movieCatalogueRepository.getAllTvs()
    }

}
