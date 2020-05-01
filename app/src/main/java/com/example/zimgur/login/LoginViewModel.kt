package com.example.zimgur.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zimgur.preferences.PreferenceManager
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

internal class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    @Inject
    lateinit var preferenceManager: PreferenceManager

    private val loginInput = MutableLiveData<Int>()
    private val atomicInteger = AtomicInteger()

    internal val accessTokenStatus by lazy {
//        loginInput.switchMap {
//            repository.generateAccessToken(
//                refreshToken = preferenceManager.refreshToken ?: "",
//                clientId = clientId,
//                clientSecret = clientSecret,
//                grantType = "refresh_token"
//            )
//        }
    }

    fun getAccessToken() {
        loginInput.value = atomicInteger.incrementAndGet()
    }
}
