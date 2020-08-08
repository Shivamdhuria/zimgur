package com.example.zimgur.app

import android.app.Application
import com.example.zimgur.BuildConfig
import com.example.zimgur.preferences.PreferenceManager
import com.example.zimgur.utils.ThemeManager
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
internal class ZimgurApplication : Application() {

//    @Inject
//    lateinit var preferenceManager: PreferenceManager

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
//        initTheme()
    }

//    private fun initTheme() {
//        ThemeManager.applyTheme(preferenceManager.themePreference())
//    }
}