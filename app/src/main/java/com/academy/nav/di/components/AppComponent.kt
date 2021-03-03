package com.academy.nav.di.components

import com.academy.db.di.DbModule
import com.academy.nav.di.modules.DataStoreModule
import com.academy.nav.di.modules.MoviesModule
import com.academy.nav.ui.details.DetailsFragment
import com.academy.nav.ui.home.HomeFragment
import com.academy.network.di.NetworkModule
import com.academy.ui_favorites.di.FavoritesComponent
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DbModule::class, NetworkModule::class, MoviesModule::class, DataStoreModule::class])
@Singleton
interface AppComponent {
    fun addSettingsSubComponent(): SettingsComponent.Builder
    fun addFavoritesSubComponent(): FavoritesComponent.Builder

    fun inject(homeFragment: HomeFragment)
    fun inject(detailsFragment: DetailsFragment)
}