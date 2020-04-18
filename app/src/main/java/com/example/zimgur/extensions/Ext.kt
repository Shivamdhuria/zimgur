package com.example.zimgur.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Any.unitify() {
    /* do nothing */
}

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)
