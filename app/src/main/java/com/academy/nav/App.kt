package com.academy.nav

import android.app.Application
import android.util.Log
import com.academy.nav.di.Injector

class App : Application() {
    init {
        Log.e("Academy", "App created")
    }

    override fun onCreate() {
        super.onCreate()
        Injector.buildDaggerAppComponent(applicationContext)
    }
}