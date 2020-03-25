package com.example.zimgur.main.data


data class UserData(
    val accountId: String,
    val text: String,
    val createdAt: Long,
    var count: Int = 0
)