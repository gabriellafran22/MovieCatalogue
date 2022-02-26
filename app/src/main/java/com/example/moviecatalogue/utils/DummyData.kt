package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvEntity

object DummyData {

    val movie = MovieEntity(
        1,
        "Uncharted",
        "2022-02-10",
        "A young street-smart, Nathan Drake and his wisecracking partner Victor “Sully” Sullivan embark on a dangerous pursuit of “the greatest treasure never found” while also tracking clues that may lead to Nathan’s long-lost brother.",
        "posterPath",
        "Action, Adventure",
        "116",
        true
    )

    val tv = TvEntity(
        2,
        "The Simpsons",
        "1989-12-17",
        "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
        "posterPath",
        "Family, Animation, Comedy",
        "22",
        true
    )

    fun generateDummyMovies(): List<MovieEntity> {
        return arrayListOf(movie)
    }

    fun generateDummyTvShows(): List<TvEntity> {
        return arrayListOf(tv)
    }

    fun generateDummyMovieDetail(): MovieEntity {
        return movie
    }

    fun generateDummyTvDetail(): TvEntity {
        return tv
    }

}