package com.academy.nav.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.academy.nav.repo.MoviesRepo
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val moviesRepo: MoviesRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return HomeViewModel(moviesRepo) as T
    }
}
