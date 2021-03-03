package com.academy.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.academy.db.dao.MovieDao
import com.academy.db.dao.MovieFavoriteDao
import com.academy.db.model.Movie
import com.academy.db.model.MovieFavorite

@Database(entities = [Movie::class, MovieFavorite::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieFavoriteDao(): MovieFavoriteDao
}