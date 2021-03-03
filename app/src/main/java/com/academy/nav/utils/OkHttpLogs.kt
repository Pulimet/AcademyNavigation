package com.academy.nav.utils

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

class OkHttpLogs : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.i("Academy", message)
    }
}