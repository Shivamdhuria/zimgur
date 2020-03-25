package com.example.zimgur.utils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class AuthenticationInterceptor @Inject constructor(private val preferences: PreferencesManager) : Interceptor {

    private companion object {
        private const val HEADER_NAME = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val initial = chain.request()
        val builder = chain.request().newBuilder()
//        builder.addHeader(TEMP_HEADER_NAME, preferences.token())
        val request = builder.build()
        return chain.proceed(request)
    }
}