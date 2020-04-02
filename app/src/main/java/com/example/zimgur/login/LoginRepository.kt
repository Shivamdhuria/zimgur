package com.example.zimgur.login

import androidx.lifecycle.LiveData
import com.example.zimgur.login.data.AuthResult
import com.example.zimgur.utils.GenericResult
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Field

internal interface LoginRepository {

//    fun generateAccessToken(@Field("refresh_token") refreshToken: String, @Field("client_id") clientId: String,
//                          @Field("client_secret") clientSecret: String, @Field("grant_type")
//                            grantType: String): LiveData<GenericResult<AuthResult?>>
}