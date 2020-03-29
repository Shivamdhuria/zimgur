package com.example.zimgur.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.zimgur.login.data.AuthResult
import com.example.zimgur.login.data.Credentials
import com.example.zimgur.utils.GenericResult
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

internal class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    public val clientId = "c8e063cdf1c29db"
    public val clientSecret = "8df5ab682be6a078bea3682c0a1a36ef66497558"
    public val redirectUrl = "elixer://callback"
    public val urlAuth = "https://api.imgur.com/oauth2/authorize"

    @Inject
    lateinit var credentials: Credentials

    private val loginInput = MutableLiveData<Int>()
    private val atomicInteger = AtomicInteger()

    internal val accessTokenStatus by lazy {
        loginInput.switchMap {
            repository.generateAccessToken(
                refreshToken = credentials.refreshToken ?: "",
                clientId = clientId,
                clientSecret = clientSecret,
                grantType = "refresh_token"
            )
        }
    }

    fun getAccessToken() {
        loginInput.value = atomicInteger.incrementAndGet()
    }
}
