package com.academy.nav.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.academy.nav.repo.SettingsRepo
import javax.inject.Inject

class SettingsViewModelFactory @Inject constructor(private val settingsRepo: SettingsRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SettingsViewModel(settingsRepo) as T
    }
}