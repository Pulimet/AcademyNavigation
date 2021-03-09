package com.academy.nav.ui.navigation

import com.academy.db.model.Movie

data class NavParams(
    val destination: Destination,
    val movie: Movie? = null
)
