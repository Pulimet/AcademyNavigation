package com.academy.ui_favorites.di

interface FavoritesInjector {
    fun getFavoritesComponent(): FavoritesComponent
    fun clearFavoritesComponent()
}