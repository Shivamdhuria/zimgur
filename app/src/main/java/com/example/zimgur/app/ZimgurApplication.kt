package com.example.zimgur.app

import com.example.zimgur.preferences.Credentials
import com.example.zimgur.utils.ThemeManager
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

internal class ZimgurApplication : DaggerApplication() {

    @Inject
    lateinit var credentials: Credentials

    override fun onCreate() {
        super.onCreate()
        initTheme()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    private fun initTheme() {
        ThemeManager.applyTheme(credentials.themePreference())
    }
}