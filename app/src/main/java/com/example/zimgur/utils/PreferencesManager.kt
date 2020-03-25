package com.example.zimgur.utils

import android.content.SharedPreferences
import com.example.zimgur.extensions.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    private companion object {
    }

    fun getStoredString(key: String, defVal: String): String {
        return sharedPreferences.getString(key, defVal) ?: ""
    }

    fun savePrefs(key: String, value: String) {
        sharedPreferences.edit { putString(key, value) }
    }

    private fun getStoredBoolean(key: String, defVal: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defVal)
    }


    fun resetCredentials() {
    }
}