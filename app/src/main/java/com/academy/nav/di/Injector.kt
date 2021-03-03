package com.academy.nav.di

import android.content.Context
import com.academy.db.di.DbModule
import com.academy.nav.di.components.AppComponent
import com.academy.nav.di.components.DaggerAppComponent
import com.academy.nav.di.components.SettingsComponent
import com.academy.nav.di.modules.*
import com.academy.navigation.BaseInjector
import com.academy.navigation.DiHolder
import com.academy.network.di.NetworkModule
import com.academy.ui_favorites.di.FavoritesComponent
import com.academy.ui_favorites.di.FavoritesModule

object Injector: BaseInjector {
    lateinit var appComponent: AppComponent

    private var settingsComponent: SettingsComponent? = null
    private var favoritesComponent: FavoritesComponent? = null
    private lateinit var appContext: Context

    init {
        DiHolder.baseInjector = this
    }

    fun buildDaggerAppComponent(applicationContext: Context){
        appContext = applicationContext
        appComponent = DaggerAppComponent.builder()
            .moviesModule(MoviesModule())
            .dbModule(DbModule(appContext))
            .networkModule(NetworkModule())
            .dataStoreModule(DataStoreModule(appContext))
            .build()
    }

    // Settings
    fun getSettingsComponent(): SettingsComponent {
        if (settingsComponent == null) {
            settingsComponent = appComponent
                .addSettingsSubComponent()
                .settingsModule(SettingsModule())
                .build()
        }
        return settingsComponent as SettingsComponent
    }

    fun clearSettingsComponent() {
        settingsComponent = null
    }

    // Favorites
    override fun getFavoritesComponent(): FavoritesComponent {
        if (favoritesComponent == null) {
            favoritesComponent = appComponent
                .addFavoritesSubComponent()
                .favoritesModule(FavoritesModule())
                .build()
        }
        return favoritesComponent as FavoritesComponent
    }

    override fun clearFavoritesComponent() {
        favoritesComponent = null
    }
}