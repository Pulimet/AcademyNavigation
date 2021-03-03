package com.academy.nav.ui.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.academy.nav.di.Injector
import com.academy.nav.repo.SettingsRepo
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepo: SettingsRepo) : ViewModel() {
    init {
        Log.w("Academy", "SettingsViewModel init")
    }

    // Min votes
    val minVotesLiveData = settingsRepo.getMinVotes.asLiveData()

    fun onBtnMinusVotesNumClick() {
        viewModelScope.launch {
            val current = minVotesLiveData.value as Int
            val minus = when {
                current > 300 -> 100
                current > 50 -> 50
                current > 10 -> 10
                else -> 5
            }
            if (current - minus > -1) settingsRepo.saveMinVotes(current - minus)
        }
    }

    fun onBtnPlusVotesNumClick() {
        viewModelScope.launch {
            val current = minVotesLiveData.value as Int
            val add = when {
                current > 250 -> 100
                current > 40 -> 50
                current > 5 -> 10
                else -> 5
            }
            settingsRepo.saveMinVotes(current + add)
        }
    }

    // Min rating
    val minRatingLiveData = settingsRepo.getMinRating.asLiveData()

    fun onBtnMinusRatingClick() {
        viewModelScope.launch {
            val current = minRatingLiveData.value as Int
            if (current > 0) settingsRepo.saveMinRating(current - 1)
        }
    }

    fun onBtnPlusRatingClick() {
        viewModelScope.launch {
            val current = minRatingLiveData.value as Int
            if (current < 9) settingsRepo.saveMinRating(current + 1)
        }
    }

    override fun onCleared() {
        Log.w("Academy", "SettingsViewModel onCleared")
        settingsRepo.onCleared()
        Injector.clearSettingsComponent()
    }
}