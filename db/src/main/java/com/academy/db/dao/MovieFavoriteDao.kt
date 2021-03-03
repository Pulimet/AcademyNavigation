package com.academy.db.dao

import androidx.room.*
import com.academy.db.model.MovieFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieFavoriteDao {
    @Query("SELECT * from movies_favorite")
    fun getMovies(): Flow<List<MovieFavorite>>

    @Query("SELECT * from movies_favorite WHERE id = :movieId")
    fun getMovie(movieId: Int): Flow<MovieFavorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieFavorite)

    @Delete
    suspend fun delete(movie: MovieFavorite)
}