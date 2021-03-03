package com.academy.nav.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.academy.nav.repo.MoviesRepo
import javax.inject.Inject

class DetailsViewModelFactory @Inject constructor(private val moviesRepo: MoviesRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DetailsViewModel(moviesRepo) as T
    }
}
