package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.source.remote.response.movie.*
import com.example.moviecatalogue.data.source.remote.response.tv.*

object DummyData {

    fun generateDummyMovies(): MovieResponse {
        val movieDates = MovieDates("2022-02-14", "2021-12-28")
        val genreIds = arrayListOf(1, 2, 3)
        val movieResult = MovieResultsItem(
            "overview",
            "originalLanguage",
            "originalTitle",
            false,
            "title",
            genreIds,
            "posterPath",
            "backdropPath",
            "releaseDate",
            10.0,
            5.0,
            1,
            true,
            12345
        )
        val movieResultList = arrayListOf(movieResult)
        return MovieResponse(movieDates, 1, 77, movieResultList, 1525)

    }

    fun generateDummyTvShows(): TvResponse {
        val genreIds = arrayListOf(1, 2, 3)
        val originCountry = arrayListOf("US")
        val tvResult = TvResultsItem(
            "firstAirDate",
            "overview",
            "originalLanguage",
            genreIds,
            "posterPath",
            originCountry,
            "backdropPath",
            "originalName",
            10.0,
            10.0,
            "",
            2,
            1
        )
        val tvResultList = arrayListOf(tvResult)

        return TvResponse(1, 1, tvResultList, 1)
    }


    fun generateDummyMovieDetail(): MovieDetailResponse {
        val genresItem = arrayListOf(GenresItem("genre1", 1))
        val productionCompaniesItem =
            arrayListOf(ProductionCompaniesItem("logoPath", "name", 1, "originCountry"))
        val spokenLanguagesItem = arrayListOf(SpokenLanguagesItem("name", "iso6391", "englishName"))
        val productionCountriesItem = arrayListOf(ProductionCountriesItem("iso31661", "name"))

        return MovieDetailResponse(
            "originalLanguage",
            "imdbId",
            true,
            "title",
            "backdropPath",
            1000,
            genresItem,
            10.0,
            productionCountriesItem,
            1,
            12345,
            10000000,
            "overview",
            "originalTitle",
            102,
            "posterPath",
            spokenLanguagesItem,
            productionCompaniesItem,
            "releaseDate",
            10.0,
            "belongsToCollection",
            "tagline",
            false,
            "homepage",
            "status"
        )
    }

    fun generateDummyTvDetail(): TvDetailResponse {
        val genresItem = arrayListOf(TvGenresItem("genre1", 1))
        val tvProductionCompaniesItem =
            arrayListOf(TvProductionCompaniesItem("logoPath", "name", 1, "originCountry"))
        val spokenLanguagesItem =
            arrayListOf(TvSpokenLanguagesItem("name", "iso6391", "englishName"))
        val tvProductionCountriesItem = arrayListOf(TvProductionCountriesItem("iso31661", "name"))
        val tvNetworksItem = arrayListOf(TvNetworksItem("logoPath", "name", 1, "originCountry"))
        val languages = arrayListOf("US")
        val seasons =
            arrayListOf(TvSeasonsItem("airDate", "overview", 1, "name", 1, 1, "posterPath"))
        val tvCreatedByItem = arrayListOf(TvCreatedByItem(1, "creditId", "name", "profilePath", 1))
        val tvLastEpisodeToAir = TvLastEpisodeToAir(
            "productionCode",
            "airDate",
            "overview",
            10,
            2.1,
            "name",
            2,
            1,
            "stillPath",
            20
        )
        val originCountry = arrayListOf("US", "ID")
        val episodeRuntime = arrayListOf(1, 2, 3)
        val tvNextEpisodeToAir = TvNextEpisodeToAir(
            "productionCode",
            "airDate",
            "overview",
            10,
            2.1,
            "name",
            2,
            1,
            "stillPath",
            20
        )

        return TvDetailResponse(
            "originalLanguage",
            10,
            tvNetworksItem,
            "type",
            "backdropPath",
            genresItem,
            10.0,
            tvProductionCountriesItem,
            2,
            12,
            1,
            "firstAirDate",
            "overview",
            seasons,
            languages,
            tvCreatedByItem,
            tvLastEpisodeToAir,
            "posterPath",
            originCountry,
            spokenLanguagesItem,
            tvProductionCompaniesItem,
            "originalName",
            10.0,
            "name",
            "tagline",
            episodeRuntime,
            false,
            tvNextEpisodeToAir,
            false,
            "lastAirDate",
            "homepage",
            "status"
        )
    }
}