package com.academy.nav.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataStoreModule(private val context: Context) {
    private val Context.votes: DataStore<Preferences> by preferencesDataStore(name = "DiWorkshopVotes")
    private val Context.rating: DataStore<Preferences> by preferencesDataStore(name = "DiWorkshopRating")

    @Provides
    @Singleton
    @Named("Votes")
    fun getDataStoreVotes(): DataStore<Preferences> = context.votes

    @Provides
    @Singleton
    @Named("Rating")
    fun getDataStoreRating(): DataStore<Preferences> = context.rating
}