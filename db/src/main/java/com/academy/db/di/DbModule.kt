package com.academy.db.di

import android.content.Context
import androidx.room.Room
import com.academy.db.MovieDatabase
import com.academy.db.dao.MovieDao
import com.academy.db.dao.MovieFavoriteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule(private val context: Context) {

    @Provides
    @Singleton
    fun getRoomDb(): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, "movies_database").build()

    @Provides
    @Singleton
    fun getMovieDao(db: MovieDatabase): MovieDao = db.movieDao()

    @Provides
    @Singleton
    fun getMovieFavoritesDao(db: MovieDatabase): MovieFavoriteDao = db.movieFavoriteDao()
}