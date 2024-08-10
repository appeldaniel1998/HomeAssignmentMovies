package com.example.home_assignment_movies.di

import android.content.Context
import android.content.SharedPreferences
import com.example.home_assignment_movies.movies_feature.data.remote.RemoteConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                val modifiedRequest = chain.request().newBuilder()
//                    .addHeader("accept", "application/json")
//                    .addHeader("Authorization", "Bearer ${RemoteConstants.AUTH_TOKEN}")
//                    .build()
//
//                chain.proceed(modifiedRequest)
//            }
//            .addInterceptor(
//                HttpLoggingInterceptor().apply {
//                    level = HttpLoggingInterceptor.Level.BODY
//                }
//            )
//            .build()
//    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext applicationContext: Context): OkHttpClient {
        // Create a cache with a specific size
        val cacheSize = 10L * 1024 * 1024 // 10 MB
        val cache = Cache(File(applicationContext.cacheDir, "http-cache"), cacheSize)

        // Interceptor to log whether the response is from cache or network
        val cacheInterceptor = Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)

            // Log whether the response comes from the network or cache
            if (response.cacheResponse != null) {
                println("Response retrieved from cache")
            } else if (response.networkResponse != null) {
                println("Response retrieved from network")
            }

            // Modify the response to include Cache-Control header for 24 hours
            val maxAge = 60 * 60 * 24 // 24 hours in seconds
            response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .build()
        }

        return OkHttpClient.Builder()
            .cache(cache) // Add cache to OkHttpClient
            .addInterceptor { chain ->
                val modifiedRequest = chain.request().newBuilder()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer ${RemoteConstants.AUTH_TOKEN}")
                    .build()

                chain.proceed(modifiedRequest)
            }
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(cacheInterceptor) // Add the cache interceptor
            .build()
    }
}