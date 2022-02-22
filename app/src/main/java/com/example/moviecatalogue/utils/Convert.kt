package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.source.remote.response.movie.GenresItem
import com.example.moviecatalogue.data.source.remote.response.tv.TvGenresItem

fun movieGenreToString(genresItem: List<GenresItem?>?): String{
    var genre = ""
    var counter = 0

    genresItem?.forEach {
        genre += it?.name.toString()
        counter++
        if (counter != genresItem.size) {
            genre += ", "
        }
    }
    return genre
}

fun tvGenreToString(genresItem: List<TvGenresItem?>?): String{
    var genre = ""
    var counter = 0

    genresItem?.forEach {
        genre += it?.name.toString()
        counter++
        if (counter != genresItem.size) {
            genre += ", "
        }
    }
    return genre
}

fun tvRuntimeToString(episodeRuntime: List<Int?>?): String{
    var runtime = ""

    if (episodeRuntime?.size == 1) {
        runtime = episodeRuntime[0].toString()
    } else {
        var counter = 0
        episodeRuntime?.forEach {
            counter++
            runtime += it.toString()
            if (counter != episodeRuntime.size) {
                runtime += ", "
            }
        }
    }

    return runtime
}

