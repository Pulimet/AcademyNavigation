package com.academy.nav.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.navOptions
import com.academy.db.model.Movie
import com.academy.nav.utils.SingleLiveEvent

class NavigationViewModel : ViewModel() {

    private var navEvent = SingleLiveEvent<NavParams>()
    fun getNavEvent(): LiveData<NavParams> = navEvent

    // HomeFragment Actions
    // TODO Step 12 - Add -- extras: FragmentNavigator.Extras -- as a second argument of onUserMovieClick() function
    // TODO Step 13 - As a first parameter of NavParams constructor pass generated function of class HomeFragmentDirections, actionHomeFragmentToDetailsFragment()
    //  actionHomeFragmentToDetailsFragment() itself should receive a movie as an argument.
    //  As a second parameter of NavParams constructor pass -- extras --
    fun onUserMovieClick(movie: Movie) {
        navEvent.value =  NavParams(Destination.DETAILS, movie)
    }

    // TODO Step 14 - As a parameter of NavParams constructor pass generated function of class HomeFragmentDirections, actionHomeFragmentToSettingsFragment()
    fun onSettingsClick() {
        navEvent.value = NavParams(Destination.SETTINGS)
    }

    // TODO Step 15 - As a first parameter of NavParams constructor pass generated function of class HomeFragmentDirections, actionHomeFragmentToFavoritesFragment()
    // TODO Step 16 - As a second parameter pass - navOptions, with transition animation. Use commented section below for reference.
    fun onFavoritesClick() {
        navEvent.value = NavParams(Destination.FAVORITES)

        /*navOptions = navOptions {
            anim {
                enter = android.R.animator.fade_in
                exit = android.R.animator.fade_out
                popEnter = android.R.animator.fade_in
                popExit = android.R.animator.fade_out
            }
        }*/
    }
}