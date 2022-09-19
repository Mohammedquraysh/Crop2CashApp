package com.example.exhibitappcrop2cash.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Crop2CashApplication: Application() {
    companion object {
        @get:Synchronized
        lateinit var application: Crop2CashApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}
