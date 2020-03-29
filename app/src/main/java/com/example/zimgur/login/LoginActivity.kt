package com.example.zimgur.login

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.cloud.io.base.BaseActivity
import com.example.zimgur.R
import com.example.zimgur.login.data.Credentials
import com.example.zimgur.utils.GenericResult
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.*

class LoginActivity : BaseActivity() {

    private val clientId = "c8e063cdf1c29db"
    private val clientSecret = "8df5ab682be6a078bea3682c0a1a36ef66497558"
    private val redirectUrl = "elixer://callback"
    private val urlAuth = "https://api.imgur.com/oauth2/authorize"

    @Inject
    lateinit var credentials: Credentials

    @Inject
    internal lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy(NONE) {
        ViewModelProvider(this, factory).get(
            LoginViewModel::class.java
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.accessTokenStatus.observe(this) {
            when (it) {

                is GenericResult.Progress ->   Log.e("bnbnvn", it.toString())
                is GenericResult.Success<*> -> onSuccess(it.value as String)
                is GenericResult.GenericError ->   Log.e("bnbnvn", it.toString())
                is GenericResult.NetworkError ->   Log.e("bnbnvn", it.toString())
            }
        }
    }

    private fun onSuccess(s: String) {
        Log.e("vcgcgv", s)
    }


    override fun onResume() {
        super.onResume()
        intent.data?.let {
            val error = it.getQueryParameter("error")
            if (error == null) {
                var uriString = it.toString()
                if (uriString.contains("#")) {
                    // Imgur url parameters start with a hash instead of a question mark when auth
                    // is successful. [Uri.getQueryParameter] does not work in that case and we'd
                    // rather it does.
                    uriString = uriString.replaceFirst("#", "?")
                }
                val uri = Uri.parse(uriString)
                val accessToken = uri.getQueryParameter("access_token")
                val expiresIn = uri.getQueryParameter("expires_in")
                val tokenType = uri.getQueryParameter("token_type")
                val refreshToken = uri.getQueryParameter("refresh_token")
                val accountUsername = uri.getQueryParameter("account_username")
                val accountId = uri.getQueryParameter("account_id")
                Log.e("cred", it.toString())
                Toast.makeText(this, "bnmbmb m", Toast.LENGTH_LONG).show()
                credentials.let { c ->
                    c.accessToken = accessToken
                    c.expiresIn = expiresIn?.toIntOrNull() ?: -1
                    c.tokenType = tokenType
                    c.refreshToken = refreshToken
                    c.username = accountUsername
                    c.accountId = accountId?.toIntOrNull() ?: -1
                }

                Log.e("credential class", credentials.accessToken)
                viewModel.getAccessToken()
            }
        }
    }
}