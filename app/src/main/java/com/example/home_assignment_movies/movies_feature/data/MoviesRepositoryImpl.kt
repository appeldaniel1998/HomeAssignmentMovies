package com.example.home_assignment_movies.movies_feature.data

import com.example.home_assignment_movies._core.domain.models.Movie
import com.example.home_assignment_movies._core.util.Resource
import com.example.home_assignment_movies._core.util.UiText
import com.example.home_assignment_movies.movies_feature.data.remote.MoviesAPI
import com.example.home_assignment_movies.movies_feature.domain.MoviesRepository
import com.example.homeassignmentmovies.R

class MoviesRepositoryImpl(
    private val api: MoviesAPI
) : MoviesRepository {
    override suspend fun getMoviesList(page: Int): Resource<List<Movie>> {
        return try {
            val movieListResponse = api.getMoviesList(page = page)
            Resource.Success(movieListResponse.results.map { it.toMovie() })
        } catch (e: Exception) {
            Resource.Error(UiText.StringResource(R.string.check_your_internet))
        }
    }
}