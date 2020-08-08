package com.example.zimgur.utils

import android.util.Log
import com.example.zimgur.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor () : Interceptor {

    private companion object {
        private const val HEADER_NAME = "Authorization"
    }

//    private val clientId = ""
//    private val clientSecret = ""

    override fun intercept(chain: Interceptor.Chain): Response {
        val initial = chain.request()
        val builder = chain.request().newBuilder()
        builder.addHeader(HEADER_NAME, "Client-ID ${BuildConfig.IMGUR_OAUTH2_CLIENT_ID}")
        val request = builder.build()
        Log.e("interceptor", request.toString())
        return chain.proceed(request)
    }
}