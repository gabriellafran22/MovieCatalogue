package com.example.moviecatalogue.ui.movies

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.utils.DummyData

class MovieViewModel : ViewModel() {

    fun getMovies(): List<MovieEntity> = DummyData.generateDummyMovies()
}