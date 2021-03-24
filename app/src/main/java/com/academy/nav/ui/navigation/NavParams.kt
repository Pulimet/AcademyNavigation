package com.academy.nav.ui.navigation

import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras

data class NavParams(
    val navDirections: NavDirections,
    val extras: FragmentNavigator.Extras = FragmentNavigatorExtras()
)

