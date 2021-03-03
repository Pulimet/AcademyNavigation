package com.academy.ui_favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.academy.db.dao.MovieFavoriteDao
import com.academy.ui_favorites.di.DiHolder

class FavoritesViewModel (private val favoritesRepo: FavoritesRepo) : ViewModel() {
    init {
        Log.w("Academy", "FavoritesViewModel init")
    }

    fun getMovies() = favoritesRepo.getMovies().asLiveData()

    override fun onCleared() {
        favoritesRepo.onCleared()
        DiHolder.favoritesInjector.clearFavoritesComponent()
        Log.w("Academy", "FavoritesViewModel onCleared")
    }
}