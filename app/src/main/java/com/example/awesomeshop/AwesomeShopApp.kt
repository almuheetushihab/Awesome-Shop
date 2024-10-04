package com.example.awesomeshop

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AwesomeShopApp : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}
