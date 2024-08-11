package com.example.home_assignment_movies.movies_feature.data.remote.responses

data class VideoListResponse(
    val id: Int,
    val results: List<VideoResponse>
)