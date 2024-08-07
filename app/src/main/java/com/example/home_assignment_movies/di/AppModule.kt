package com.example.home_assignment_movies.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(sharedPreferences: SharedPreferences): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor {
//                val modifiedRequest = it.request().newBuilder()
//                    .addHeader("Authorization", "Bearer $token")
//                    .build()
//                it.proceed(modifiedRequest)
//            }
//            .addInterceptor(
//                HttpLoggingInterceptor().apply {
//                    level = HttpLoggingInterceptor.Level.BODY
//                }
//            )
//            .build()
//    }


}