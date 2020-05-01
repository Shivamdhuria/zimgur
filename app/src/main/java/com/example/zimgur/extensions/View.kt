package com.example.zimgur.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.zimgur.utils.SafeClickListener

fun ViewGroup.inflate(@LayoutRes layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener { onSafeClick(it) }
    setOnClickListener(safeClickListener)
}

fun View.setSafeOnClickListener(duration: Int, onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener(duration) { onSafeClick(it) }
    setOnClickListener(safeClickListener)
}