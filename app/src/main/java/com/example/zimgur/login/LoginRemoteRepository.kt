package com.example.zimgur.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.zimgur.extensions.safeApiCall
import com.example.zimgur.login.data.AuthResult
import com.example.zimgur.utils.GenericResult
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class LoginRemoteRepository @Inject constructor(private val api: LoginApi) : LoginRepository {

    override fun generateAccessToken(refreshToken: String, clientId: String, clientSecret: String, grantType: String): LiveData<GenericResult<AuthResult?>> = liveData {
        val apiResult = safeApiCall(Dispatchers.IO) {
            Log.e("bjmbmbh", "api result")
            api.getAccessToken(refreshToken, clientId, clientSecret, grantType)
        }
        emit(apiResult)
    }
}