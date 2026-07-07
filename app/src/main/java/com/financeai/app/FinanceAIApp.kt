package com.financeai.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FinanceAIApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeApp()
    }

    private fun initializeApp() {
        // Firebase initialization
        // Analytics
        // Logging
        // Future app-wide startup tasks
    }
}