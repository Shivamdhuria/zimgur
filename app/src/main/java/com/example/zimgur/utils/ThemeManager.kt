package com.example.zimgur.utils

import androidx.appcompat.app.AppCompatDelegate

object ThemeManager {

   const val LIGHT_MODE = "Light"
   const val DARK_MODE = "Dark"
  private const val AUTO_BATTERY_MODE = "Auto-battery"
  private const val FOLLOW_SYSTEM_MODE = "System"

  fun applyTheme(themePreference: String) {
    when (themePreference) {
      LIGHT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      DARK_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
      AUTO_BATTERY_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
      FOLLOW_SYSTEM_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
  }
}