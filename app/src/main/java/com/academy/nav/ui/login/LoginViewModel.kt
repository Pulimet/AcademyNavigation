package com.academy.nav.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class LoginViewModel : ViewModel() {
    init {
        Log.w("Academy", "LoginViewModel init")
    }

    private var isLoggedIn = false

    @Suppress("UNUSED_PARAMETER")
    fun login(username: String, password: String) = liveData {
        isLoggedIn = true
        emit(true)
    }

    fun user() = liveData {
        emit(isLoggedIn)
    }

    override fun onCleared() {
        Log.w("Academy", "LoginViewModel onCleared")
    }
}
