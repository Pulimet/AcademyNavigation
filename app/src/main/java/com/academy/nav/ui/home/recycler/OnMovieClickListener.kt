package com.academy.nav.ui.home.recycler

import androidx.navigation.fragment.FragmentNavigator
import com.academy.db.model.Movie

interface OnMovieClickListener {
    // TODO Step 7 - Uncomment additional argument of type FragmentNavigator.Extras, we
    //  will us it to support shared elements between HomeFragment and DetailsFragment.
    //  Shared element will be a movie cover image.
    fun onClick(movie: Movie, /*extras: FragmentNavigator.Extras,*/ position: Int)
}