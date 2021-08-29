package com.agromall.tellerium

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    /**
     * Set up timber for logging
     */
    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}