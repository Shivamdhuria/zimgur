package com.example.zimgur.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.zimgur.R
import com.example.zimgur.extensions.setSafeOnClickListener
import kotlinx.android.synthetic.main.activity_welcome_screen.*


/*
No need for this activity for this tutorial
 */

//class WelcomeScreenActivity : AppCompatActivity() {
//
//    public val redirectUrl = "elixer://callback"
//    public val urlAuth = "https://api.imgur.com/oauth2/authorize"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_welcome_screen)
//
//        loginButton.setSafeOnClickListener {
//            //            val intent = Intent(this, LoginActivity::class.java)
////            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
////            startActivity(intent)
////            login()
//        }
//    }
//
//
////    private fun login() {
////
////        /**
////         * Redirects to the Imgur connection page in order to retrieve
////         * data such as the access token, the client ID and the username
////         */
////        val url = urlAuth.toHttpUrlOrNull()
////                ?.newBuilder()
////                ?.addQueryParameter("client_id", BuildConfig.IMGUR_OAUTH2_CLIENT_ID)
////                ?.addQueryParameter("client_secret", BuildConfig.IMGUR_OAUTH2_CLIENT_SECRET)
////                ?.addQueryParameter("response_type", Config.IMGUR_OAUTH2_RESPONSE_TYPE)
////                ?.build()
////        if (url != null) {
////            val i = Intent(Intent.ACTION_VIEW)
////            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
////            i.data = Uri.parse(url.toUrl().toString())
////            startActivity(i)
////        }
////    }
//}