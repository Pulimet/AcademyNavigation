package com.academy.ui_favorites.di

import com.academy.ui_favorites.FavoritesFragment
import dagger.Subcomponent

@Subcomponent(modules = [FavoritesModule::class])
@FavoritesScope
interface FavoritesComponent {

    @Subcomponent.Builder
    interface Builder {
        fun favoritesModule(favoritesModule: FavoritesModule): Builder
        fun build(): FavoritesComponent
    }

    fun inject(favoritesFragment: FavoritesFragment)
}