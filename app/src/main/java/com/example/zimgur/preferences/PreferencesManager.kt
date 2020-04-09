package com.example.zimgur.preferences

import android.content.SharedPreferences
import com.example.zimgur.extensions.edit

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    private companion object {

        private const val TOKEN = "token"
        private const val PREFERENCE_THEME = "preference_key_theme"

    }

    fun saveThemePreference(initiatorPhoneNumber: String) = savePrefs(PREFERENCE_THEME, initiatorPhoneNumber)

    fun themePreference() = getStoredString(PREFERENCE_THEME, "")


    fun getStoredString(key: String, defVal: String): String {
        return sharedPreferences.getString(key, defVal) ?: ""
    }

    fun savePrefs(key: String, value: String) {
        sharedPreferences.edit {
            putString(key, value) }
    }

    private fun getStoredBoolean(key: String, defVal: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defVal)
    }

    private fun savePrefs(key: String, value: Boolean) {
        sharedPreferences.edit {
            putBoolean(key, value)
        }
    }

}