package com.academy.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.academy.db.model.Movie
import com.academy.db.model.MovieFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * from movies")
    fun getMovies(): Flow<List<Movie>>

    @Query("SELECT * from movies WHERE id = :movieId")
    fun getMovie(movieId: Int): Flow<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: Collection<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}