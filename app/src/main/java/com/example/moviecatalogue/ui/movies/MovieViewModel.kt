package com.example.moviecatalogue.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.remote.response.movie.MovieResponse
import com.example.moviecatalogue.vo.Resource

class MovieViewModel (private val movieCatalogueRepository: MovieCatalogueRepository): ViewModel() {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> = movieCatalogueRepository.getAllMovies()

}