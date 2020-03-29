package com.example.zimgur.login

import com.example.zimgur.login.data.AuthResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

internal interface LoginApi {

    @POST("oauth2/token")
    @FormUrlEncoded
    fun getAccessToken(@Field("refresh_token") refreshToken: String, @Field("client_id") clientId: String,
                       @Field("client_secret") clientSecret: String, @Field("grant_type") grantType: String): AuthResult
}