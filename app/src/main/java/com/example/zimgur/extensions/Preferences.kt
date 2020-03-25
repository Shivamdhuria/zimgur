package com.example.zimgur.extensions

import android.content.SharedPreferences
import android.preference.Preference
import androidx.annotation.StringRes

inline fun SharedPreferences.edit(block: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.block()
    editor.apply()
}