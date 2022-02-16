package com.example.moviecatalogue.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.remote.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.remote.response.movie.MovieResponse

class MovieViewModel (private val movieCatalogueRepository: MovieCatalogueRepository): ViewModel() {

    fun getAllMovies(): LiveData<MovieResponse> = movieCatalogueRepository.getAllMovies()

}