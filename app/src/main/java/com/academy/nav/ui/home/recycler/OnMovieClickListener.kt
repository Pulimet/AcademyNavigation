package com.academy.nav.ui.home.recycler

import com.academy.db.model.Movie

interface OnMovieClickListener {
    fun onClick(movie: Movie, position: Int)
}