package com.academy.nav.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.navOptions
import com.academy.db.model.Movie
import com.academy.nav.ui.home.HomeFragmentDirections
import com.academy.nav.utils.SingleLiveEvent

class NavigationViewModel : ViewModel() {

    private var navEvent = SingleLiveEvent<NavParams>()
    fun getNavEvent(): LiveData<NavParams> = navEvent

    // HomeFragment Actions
    fun onUserMovieClick(movie: Movie, extras: FragmentNavigator.Extras) {
        navEvent.value =
            NavParams(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie), extras)
    }

    // TODO 20 - Remove this method
    fun onSettingsClick() {
        navEvent.value = NavParams(HomeFragmentDirections.actionHomeFragmentToSettingsFragment())
    }

    // TODO 21 - Remove this method
    fun onFavoritesClick() {
        navEvent.value = NavParams(
            HomeFragmentDirections.actionHomeFragmentToFavoritesFragment(),
            navOptions = navOptions {
                anim {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                    popEnter = android.R.animator.fade_in
                    popExit = android.R.animator.fade_out
                }
            }
        )
    }

    fun goToHomeFragmentFromSettings() {
        // TODO Step 16 - Uncomment the line below to allow the global navigation to HomeFragment on button click in SettingsFragment
        //navEvent.value = NavParams(SettingsFragmentDirections.actionGlobalHomeFragment())
    }
}