package com.example.zimgur.main.data

data class ImgurResponse<T>(
    val data: T,
    val success: Boolean,
    val status: Int,
    // Currently this is unused because Imgur API puts error messages inside data (?!?!?!?!)
    // In the future, we could use a custom deserializer to insert the value here.
    val errorMessage: String
)