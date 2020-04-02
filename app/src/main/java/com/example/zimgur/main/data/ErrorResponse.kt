package com.example.zimgur.main.data

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error") val error: String,
    @SerializedName("request") val request: String,
    @SerializedName("method") val method: String
    // Currently this is unused because Imgur API puts error messages inside data (?!?!?!?!)
)