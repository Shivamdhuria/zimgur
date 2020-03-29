package com.example.zimgur.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class AuthenticationInterceptor @Inject constructor(private val preferences: PreferencesManager) : Interceptor {

    private companion object {
        private const val HEADER_NAME = "Authorization"
    }

    private val clientId = "c8e063cdf1c29db"
    private val clientSecret = "8df5ab682be6a078bea3682c0a1a36ef66497558"

    override fun intercept(chain: Interceptor.Chain): Response {
        val initial = chain.request()
        val builder = chain.request().newBuilder()
        builder.addHeader(HEADER_NAME, "Client-ID $clientId")
        val request = builder.build()
        Log.e("interceptor", request.toString())
        return chain.proceed(request)
    }
}