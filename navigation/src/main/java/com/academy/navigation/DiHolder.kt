package com.academy.navigation

object DiHolder {
    lateinit var baseInjector: BaseInjector
}

interface BaseInjector {
    fun getFavoritesComponent(): BaseFavoritesComponent
    fun clearFavoritesComponent()
}

interface BaseFavoritesComponent