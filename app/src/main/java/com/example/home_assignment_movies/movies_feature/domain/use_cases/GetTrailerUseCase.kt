package com.example.home_assignment_movies.movies_feature.domain.use_cases

import com.example.home_assignment_movies._core.util.Resource
import com.example.home_assignment_movies._core.util.UiText
import com.example.home_assignment_movies.movies_feature.domain.MoviesRepository

class GetTrailerUseCase(
    private val repository: MoviesRepository
) {
    /**
     * Fetches the trailer for a movie from the repository
     * @param movieId The id of the movie to fetch the trailer for
     */
    suspend operator fun invoke(movieId: Int): Resource<String> {
        return when (val result = repository.getTrailerForMovie(movieId)) {
            is Resource.Success -> Resource.Success(result.data!!)
            is Resource.Error -> Resource.Error(result.uiText ?: UiText.unknownError)
        }
    }
}