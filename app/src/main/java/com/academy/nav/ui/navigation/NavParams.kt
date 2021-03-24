package com.academy.nav.ui.navigation

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator

data class NavParams(
    val navDirections: NavDirections,
    val extras: FragmentNavigator.Extras? = null,
    val navOptions: NavOptions? = null
)

