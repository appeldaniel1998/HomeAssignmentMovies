package com.example.home_assignment_movies.movies_feature.data.remote.responses

import com.example.home_assignment_movies._core.domain.models.Movie
import com.example.home_assignment_movies._core.util.toDate
import com.example.home_assignment_movies.movies_feature.data.remote.RemoteConstants
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("id") val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
) {
    fun toMovie(): Movie {
        return Movie(
            id = id,
            title = title,
            overview = overview,
            posterUrl = "${RemoteConstants.IMAGE_BASE_URL}/$posterPath",
            releaseDate = releaseDate.toDate(),
            voteAverage = voteAverage,
        )
    }
}

