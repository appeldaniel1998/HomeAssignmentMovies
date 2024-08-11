package com.example.home_assignment_movies.movies_feature.data.remote.responses

import com.google.gson.annotations.SerializedName

/**
 * Response received from the API when fetching a list of movies.
 * @param page The current page of the response
 * @param results The list of movies
 * @param totalPages The total number of pages
 * @param totalResults The total number of results
 */
data class MovieListResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieResponse>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

