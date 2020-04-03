package com.example.zimgur.main.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SubredditImage(
        val id: String,
        val title: String,
        val createdAt: Long) : Parcelable