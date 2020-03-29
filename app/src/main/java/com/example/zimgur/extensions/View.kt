package com.example.zimgur.extensions

import android.graphics.PorterDuff
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.example.zimgur.utils.SafeClickListener

fun ViewGroup.inflate(@LayoutRes layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}

fun ImageView.setTint(@ColorRes colorResource: Int) {
    val color = ContextCompat.getColor(context, colorResource)
    setColorFilter(color, PorterDuff.Mode.SRC_IN)
}

fun SearchView.onTextChange(function: (String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            function(query ?: "")
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            function(newText ?: "")
            return true
        }
    })
}

fun EditText.setMaxLength(length: Int) {
    val filterArray = arrayOfNulls<InputFilter>(1)
    filterArray[0] = InputFilter.LengthFilter(length)
    filters = filterArray
}

fun EditText.isNotEmpty(): Boolean {
    return this.text.trim().isNotEmpty()
}

fun EditText.onChange(cb: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {cb(s.toString())}
    })
}

fun EditText.setReadOnly(value: Boolean) {
    isFocusable = value
    isFocusableInTouchMode = value
}

fun EditText.setReadOnlyWithCursor(value: Boolean) {
    setReadOnly(value)
    requestFocus()
    setSelection(this.text.length)
    isCursorVisible = value
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener { onSafeClick(it) }
    setOnClickListener(safeClickListener)
}

fun View.setSafeOnClickListener(duration: Int, onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener(duration) { onSafeClick(it) }
    setOnClickListener(safeClickListener)
}