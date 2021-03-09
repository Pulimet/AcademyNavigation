package com.academy.nav.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.academy.db.model.Movie
import com.academy.db.model.MovieFavorite
import com.academy.nav.repo.MoviesRepo
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetailsViewModel(private val moviesRepo: MoviesRepo) : ViewModel() {

    private var isMovieInFavorites = false

    fun getMovie(movieId: Int): LiveData<Movie> =
        moviesRepo.getMovie(movieId).asLiveData()

    fun getMovieFromFavorites(movieId: Int): LiveData<Boolean> =
        moviesRepo.getMovieFromFavorites(movieId)
            .map { movieFavorite: MovieFavorite? ->
                isMovieInFavorites = movieFavorite != null
                isMovieInFavorites
            }.asLiveData()

    fun onFavoriteImageClick(movie: Movie) {
        viewModelScope.launch {
            moviesRepo.addOrRemoveMovieFromFavorites(movie, isMovieInFavorites)
        }
    }
}