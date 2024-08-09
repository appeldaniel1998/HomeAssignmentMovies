package com.example.home_assignment_movies.movies_feature.domain.use_cases

import com.example.home_assignment_movies._core.domain.models.Movie
import com.example.home_assignment_movies._core.util.Resource
import com.example.home_assignment_movies._core.util.UiText
import com.example.home_assignment_movies.movies_feature.domain.MoviesRepository

class GetMoviesUseCase(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(page: Int): Resource<List<Movie>> {
        return when (val result = repository.getMoviesList(page)) {
            is Resource.Success -> Resource.Success(result.data!!)
            is Resource.Error -> Resource.Error(result.uiText ?: UiText.unknownError())
        }
    }
}