package com.academy.nav.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.academy.db.model.Movie
import com.academy.nav.utils.SingleLiveEvent

class NavigationViewModel : ViewModel() {

    private var navEvent = SingleLiveEvent<NavParams>()
    fun getNavEvent(): LiveData<NavParams> = navEvent

    // HomeFragment Actions
    fun onUserMovieClick(movie: Movie) {
        navEvent.value =
            NavParams(Destination.DETAILS, movie)
    }

    fun onSettingsClick() {
        navEvent.value = NavParams(Destination.SETTINGS)
    }

    fun onFavoritesClick() {
        navEvent.value = NavParams(Destination.FAVORITES)
    }
}