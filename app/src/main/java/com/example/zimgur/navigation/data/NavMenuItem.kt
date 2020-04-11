package com.example.zimgur.navigation.data

import androidx.annotation.StringRes

/**
 * A class which represents a checkable, navigation destination such as 'Inbox' or 'Sent'.
 */
data class NavMenuItem(val id: Int, val titleRes: String, var checked: Boolean)
