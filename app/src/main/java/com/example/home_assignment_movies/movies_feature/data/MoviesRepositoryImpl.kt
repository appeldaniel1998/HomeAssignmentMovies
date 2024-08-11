package com.example.home_assignment_movies.movies_feature.data

import com.example.home_assignment_movies._core.domain.models.Movie
import com.example.home_assignment_movies._core.util.Resource
import com.example.home_assignment_movies._core.presentation.util.UiText
import com.example.home_assignment_movies.movies_feature.data.remote.MoviesAPI
import com.example.home_assignment_movies.movies_feature.domain.MoviesRepository
import com.example.homeassignmentmovies.R

/**
 * Implementation of [MoviesRepository]
 * @param api The API service - [MoviesAPI] - gathered from Hilt
 */
class MoviesRepositoryImpl(
    private val api: MoviesAPI
) : MoviesRepository {

    /**
     * Fetches the list of movies from the API
     * @param page The page number to fetch
     * @return A [Resource] of a list of [Movie]s
     */
    override suspend fun getMoviesList(page: Int): Resource<List<Movie>> {
        return try {
            val movieListResponse = api.getMoviesList(page = page)
            Resource.Success(movieListResponse.results.map { it.toMovie() })
        } catch (e: Exception) {
            Resource.Error(UiText.StringResource(R.string.check_your_internet))
        }
    }

    override suspend fun getTrailerForMovie(movieId: Int): Resource<String> {
        return try {
            val videoListResponse = api.getMovieVideos(movieId)
            val videos = videoListResponse.results
            val trailer = videos.find { it.type == "Trailer" && it.site == "YouTube" && it.official }
            if (trailer != null) {
                Resource.Success(trailer.key)
            } else {
                Resource.Error(UiText.StringResource(R.string.no_trailer_found))
            }
        } catch (e: Exception) {
            Resource.Error(UiText.StringResource(R.string.check_your_internet))
        }
    }
}