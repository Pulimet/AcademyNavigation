package com.academy.nav.di.modules

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.academy.nav.di.scopes.SettingsScope
import com.academy.nav.repo.SettingsRepo
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SettingsModule {
    @Provides
    @SettingsScope
    fun getSettingsRepo(
        @Named("Votes") dataStoreVotes: DataStore<Preferences>,
        @Named("Rating") dataStoreRating: DataStore<Preferences>
    ) = SettingsRepo(dataStoreVotes, dataStoreRating)
}