package com.example.home_assignment_movies.movies_feature.domain

import com.example.home_assignment_movies._core.domain.models.Movie
import com.example.home_assignment_movies._core.util.Resource

/**
 * Repository for the movies feature
 */
interface MoviesRepository {
    suspend fun getMoviesList(page: Int): Resource<List<Movie>>
}