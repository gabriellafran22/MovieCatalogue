package com.example.moviecatalogue.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieDates(

    @field:SerializedName("maximum")
    val maximum: String? = null,

    @field:SerializedName("minimum")
    val minimum: String? = null
)