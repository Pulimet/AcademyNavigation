package com.academy.nav.di.components

import com.academy.nav.di.modules.SettingsModule
import com.academy.nav.di.scopes.SettingsScope
import com.academy.nav.ui.settings.SettingsFragment
import dagger.Subcomponent

@Subcomponent(modules = [SettingsModule::class])
@SettingsScope
interface SettingsComponent {

    @Subcomponent.Builder
    interface Builder {
        fun settingsModule(settingsModule: SettingsModule): Builder
        fun build(): SettingsComponent
    }

    fun inject(settingsFragment: SettingsFragment)
}