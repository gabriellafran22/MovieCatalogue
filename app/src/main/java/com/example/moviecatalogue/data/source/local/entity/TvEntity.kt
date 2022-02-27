package com.example.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_entities")
class TvEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "firstAirDate")
    var firstAirDate: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @ColumnInfo(name = "genres")
    var genres: String,

    @ColumnInfo(name = "episodeRunTime")
    var  episodeRunTime: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
)