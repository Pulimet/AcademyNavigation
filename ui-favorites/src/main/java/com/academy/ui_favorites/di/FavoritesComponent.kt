package com.academy.ui_favorites.di

import com.academy.navigation.BaseFavoritesComponent
import com.academy.navigation.DiHolder
import com.academy.ui_favorites.FavoritesFragment
import com.academy.ui_favorites.FavoritesRepo
import dagger.Subcomponent

@Subcomponent(modules = [FavoritesModule::class])
@FavoritesScope
interface FavoritesComponent : BaseFavoritesComponent {

    @Subcomponent.Builder
    interface Builder {
        fun favoritesModule(favoritesModule: FavoritesModule): Builder
        fun build(): FavoritesComponent
    }

    fun inject(favoritesFragment: FavoritesFragment)

    companion object {
        fun getFavoriteComponent() =
            DiHolder.baseInjector.getFavoritesComponent() as FavoritesComponent
    }
}