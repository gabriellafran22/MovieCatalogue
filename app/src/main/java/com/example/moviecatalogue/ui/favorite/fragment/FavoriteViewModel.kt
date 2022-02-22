package com.example.moviecatalogue.ui.favorite.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity

class FavoriteViewModel(private val movieCatalogueRepository: MovieCatalogueRepository): ViewModel() {

    fun getFavoriteMovies(): LiveData<List<MovieEntity>> = movieCatalogueRepository.getFavoriteMovies()
}