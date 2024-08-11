package com.example.home_assignment_movies.movies_feature.data.remote

import com.example.home_assignment_movies.movies_feature.data.remote.responses.MovieListResponse
import com.example.home_assignment_movies.movies_feature.data.remote.responses.VideoListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesAPI {

    /**
     * Get a list of movies from the API.
     */
    @GET("discover/movie")
    suspend fun getMoviesList(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): MovieListResponse

    /**
     * Get a list of videos for a specific movie.
     */
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US"
    ): VideoListResponse
}