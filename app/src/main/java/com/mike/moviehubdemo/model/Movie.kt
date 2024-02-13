package com.mike.moviehubdemo.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "movies")
data class Movie(
    @Json(name = "adult")
    var adult: Boolean? =null,
    @Json(name = "backdrop_path")
    var backdropPath: String? = null,
    @Json(name = "genre_ids")
    var genreIds: List<Int>? = null,
    @Json(name = "id")
    @PrimaryKey
    var id: Int? = null,
    @Json(name = "media_type")
    var mediaType: String? = null,
    @Json(name = "original_language")
    var originalLanguage: String? = null,
    @Json(name = "original_title")
    var originalTitle: String? = null,
    @Json(name = "overview")
    var overview: String? = null,
    @Json(name = "popularity")
    var popularity: Double? = null,
    @Json(name = "poster_path")
    var posterPath: String? = null,
    @Json(name = "release_date")
    var releaseDate: String? = null,
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "video")
    var video: Boolean? = null,
    @Json(name = "vote_average")
    var voteAverage: Double? = null,
    @Json(name = "vote_count")
    var voteCount: Int? = null
)