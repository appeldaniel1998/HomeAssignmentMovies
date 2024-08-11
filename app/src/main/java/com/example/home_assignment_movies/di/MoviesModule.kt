package com.example.home_assignment_movies.di

import com.example.home_assignment_movies.movies_feature.data.MoviesRepositoryImpl
import com.example.home_assignment_movies.movies_feature.data.remote.MoviesAPI
import com.example.home_assignment_movies.movies_feature.data.remote.RemoteConstants
import com.example.home_assignment_movies.movies_feature.domain.MoviesRepository
import com.example.home_assignment_movies.movies_feature.domain.use_cases.GetMoviesUseCase
import com.example.home_assignment_movies.movies_feature.domain.use_cases.GetTrailerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {

    @Provides
    @Singleton
    fun providesMoviesApi(okHttpClient: OkHttpClient): MoviesAPI {
        return Retrofit.Builder()
            .baseUrl(RemoteConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(api: MoviesAPI): MoviesRepository {
        return MoviesRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(repository: MoviesRepository): GetMoviesUseCase {
        return GetMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTrailerUseCase(repository: MoviesRepository): GetTrailerUseCase {
        return GetTrailerUseCase(repository)
    }
}