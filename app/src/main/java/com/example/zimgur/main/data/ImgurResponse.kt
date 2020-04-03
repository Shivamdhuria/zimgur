package com.example.zimgur.main.data

data class ImgurResponse<T>(
        val data: T,
        val success: Boolean,
        val status: Int)