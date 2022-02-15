package com.example.moviecatalogue.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("dates")
	val dates: MovieDates? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<MovieResultsItem?>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)




