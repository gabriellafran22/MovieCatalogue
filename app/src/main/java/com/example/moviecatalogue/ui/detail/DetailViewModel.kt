package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvEntity
import com.example.moviecatalogue.utils.DummyData

class DetailViewModel : ViewModel(){

    private lateinit var movieId: String
    private lateinit var tvId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovie(): MovieEntity? {
        var movie: MovieEntity? = null
        for (movieEntity in DummyData.generateDummyMovies()) {
            if (movieEntity.movieId == movieId) {
                movie = movieEntity
            }
        }
        return movie
    }

    fun setSelectedTv(tvId: String) {
        this.tvId = tvId
    }

    fun getTv(): TvEntity? {
        var tv: TvEntity? = null
        for (tvEntity in DummyData.generateDummyTvShows()) {
            if (tvEntity.tvId == tvId) {
                tv = tvEntity
            }
        }
        return tv
    }
}