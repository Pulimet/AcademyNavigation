package com.academy.nav.ui.navigation

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import com.academy.db.model.Movie

// TODO Step 11 - Instead of existing parameters we will use the commented out.
//  (Remove line 11 and 12, and uncomment lines 13-15)
data class NavParams(
    val destination: Destination,
    val movie: Movie? = null
    /*val navDirections: NavDirections,
    val extras: FragmentNavigator.Extras? = null,
    val navOptions: NavOptions? = null*/
)

