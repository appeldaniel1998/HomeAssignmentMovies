package com.example.home_assignment_movies.movies_feature.data.remote

import com.example.home_assignment_movies.movies_feature.data.remote.responses.MovieListResponse


interface MoviesAPI {

    suspend fun getMovies(): MovieListResponse
}