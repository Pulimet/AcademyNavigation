package com.academy.nav.di.modules

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.academy.db.dao.MovieDao
import com.academy.db.dao.MovieFavoriteDao
import com.academy.nav.repo.MoviesRepo
import com.academy.nav.utils.OkHttpLogs
import com.academy.network.services.TmdbApiService
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
class MoviesModule {
    @Provides
    @Singleton
    fun getMoviesRepo(
        movieDao: MovieDao,
        movieFavoriteDao: MovieFavoriteDao,
        tmdbApiService: TmdbApiService,
        @Named("Votes") dataStoreVotes: DataStore<Preferences>,
        @Named("Rating") dataStoreRating: DataStore<Preferences>
    ) = MoviesRepo(movieDao, movieFavoriteDao,  tmdbApiService, dataStoreVotes, dataStoreRating)

    @Provides
    @Singleton
    fun getOkHttpLogger(): HttpLoggingInterceptor.Logger = OkHttpLogs()
}