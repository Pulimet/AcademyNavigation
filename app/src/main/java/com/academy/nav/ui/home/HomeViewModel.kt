package com.academy.nav.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.academy.nav.repo.MoviesRepo

class HomeViewModel(private val moviesRepo: MoviesRepo) : ViewModel() {
    init {
        Log.w("Academy", "HomeViewModel init")
    }

    var savedItemPosition = 0

    fun getMovies() = moviesRepo.getMovies().asLiveData()

    fun onUserRefreshedMain() {
        moviesRepo.fetchFreshMovies()
    }

    fun saveClickedItemPosition(position: Int?) {
        savedItemPosition = position ?: 0
    }

    fun saveFirstVisiblePosition(position: Int?) {
        savedItemPosition = position ?: 0
    }

    override fun onCleared() {
        moviesRepo.onCleared()
        Log.w("Academy", "HomeViewModel onCleared")
    }
}