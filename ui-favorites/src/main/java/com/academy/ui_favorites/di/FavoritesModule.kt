package com.academy.ui_favorites.di

import com.academy.db.dao.MovieFavoriteDao
import com.academy.ui_favorites.FavoritesRepo
import dagger.Module
import dagger.Provides

@Module
class FavoritesModule {

    @Provides
    @FavoritesScope
    fun getFavoritesRepo(movieFavoriteDao: MovieFavoriteDao) = FavoritesRepo(movieFavoriteDao)
}