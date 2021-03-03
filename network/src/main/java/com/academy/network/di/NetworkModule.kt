package com.academy.network.di

import com.academy.network.services.TmdbApiService
import com.academy.network.utils.NetworkObjectsCreator
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        private const val TMDB_URL = "https://api.themoviedb.org/"
    }

    @Provides
    @Singleton
    fun getOkHttpClient(logger: HttpLoggingInterceptor.Logger) =
        NetworkObjectsCreator.createOkHttpClient(logger)

    @Provides
    @Singleton
    fun getApiService(okHttpClient: OkHttpClient) =
        NetworkObjectsCreator.createWebService<TmdbApiService>(okHttpClient, TMDB_URL)
}