package com.mike.moviehubdemo.model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "tbl_movies")
@JsonClass(generateAdapter = true)
data class Movie(
    var isIconChanged: Boolean? = null,
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
) : Parcelable{
        constructor(parcel: Parcel) : this(
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readString(),
            TODO("genreIds"),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Double::class.java.classLoader) as? Double,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readValue(Double::class.java.classLoader) as? Double,
            parcel.readValue(Int::class.java.classLoader) as? Int
        ){
        }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(isIconChanged)
        parcel.writeValue(adult)
        parcel.writeString(backdropPath)
        parcel.writeValue(id)
        parcel.writeString(mediaType)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeString(overview)
        parcel.writeValue(popularity)
        parcel.writeString(posterPath)
        parcel.writeString(releaseDate)
        parcel.writeString(title)
        parcel.writeValue(video)
        parcel.writeValue(voteAverage)
        parcel.writeValue(voteCount)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}