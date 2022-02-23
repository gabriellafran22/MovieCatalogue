package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvEntity

object DummyData {

    val movie = MovieEntity(
        1,
        "originalTitle",
        "releaseDate",
        "overview",
        "posterPath",
        "genres",
        "102",
        false
    )

    val tv = TvEntity(
        1,
        "name",
        "firstAirDate",
        "overview",
        "posterPath",
        "genres",
        "102",
        false
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

//    fun generateDummyMovies(): MovieResponse {
//        val movieDates = MovieDates("2022-02-14", "2021-12-28")
//        val genreIds = arrayListOf(1, 2, 3)
//        val movieResult = MovieResultsItem(
//            "overview",
//            "originalLanguage",
//            "originalTitle",
//            false,
//            "title",
//            genreIds,
//            "posterPath",
//            "backdropPath",
//            "releaseDate",
//            10.0,
//            5.0,
//            1,
//            true,
//            12345
//        )
//        val movieResultList = arrayListOf(movieResult)
//        return MovieResponse(movieDates, 1, 77, movieResultList, 1525)
//
//    }

//    fun generateDummyTvShows(): TvResponse {
//        val genreIds = arrayListOf(1, 2, 3)
//        val originCountry = arrayListOf("US")
//        val tvResult = TvEntity(
//            "firstAirDate",
//            "overview",
//            "originalLanguage",
//            genreIds,
//            "posterPath",
//            originCountry,
//            "backdropPath",
//            "originalName",
//            10.0,
//            10.0,
//            "",
//            2,
//            1
//        )
//        val tvResultList = arrayListOf(tvResult)
//
//        return TvResponse(1, 1, tvResultList, 1)
//    }


//    fun generateDummyMovieDetail(): MovieDetailResponse {
//        val genresItem = arrayListOf(
//            GenresItem("Action", 28),
//            GenresItem("Adventure", 12),
//            GenresItem("Science Fiction", 878)
//        )
//
//        return MovieDetailResponse(
//            "originalLanguage",
//            "imdbId",
//            true,
//            "title",
//            "backdropPath",
//            1000,
//            genresItem,
//            10.0,
//            1,
//            12345,
//            10000000,
//            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
//            "Spider-Man: No Way Home",
//            148,
//            "/nogV4th2P5QWYvQIMiWHj4CFLU9.jpg",
//            "2021-12-15",
//            10.0,
//            "tagline",
//            false,
//            "homepage",
//            "status"
//        )
//    }
//
//    fun generateDummyTvDetail(): TvDetailResponse {
//        val genresItem = arrayListOf(TvGenresItem("genre1", 1))
//        val languages = arrayListOf("US")
//        val originCountry = arrayListOf("US", "ID")
//        val episodeRuntime = arrayListOf(1, 2, 3)
//
//        return TvDetailResponse(
//            "originalLanguage",
//            10,
//            "type",
//            "backdropPath",
//            genresItem,
//            10.0,
//            2,
//            12,
//            1,
//            "2019-06-16",
//            "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
//            languages,
//            "posterPath",
//            originCountry,
//            "originalName",
//            10.0,
//            "Euphoria",
//            "tagline",
//            episodeRuntime,
//            false,
//            false,
//            "lastAirDate",
//            "homepage",
//            "status"
//        )
//    }
}