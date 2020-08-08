package com.example.zimgur.app

import android.content.ContentResolver
import android.content.Context
import com.example.zimgur.BuildConfig
import com.example.zimgur.main.MainActivityApi
import com.example.zimgur.main.MainActivityRemoteRepository
import com.example.zimgur.main.MainActivityRepository
import com.example.zimgur.remote.ImgurResponseConverterFactory
import com.example.zimgur.utils.AuthenticationInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthenticationInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addNetworkInterceptor(StethoInterceptor())
        httpClient.readTimeout(10, TimeUnit.SECONDS)
        httpClient.connectTimeout(10, TimeUnit.SECONDS)
        httpClient.writeTimeout(10, TimeUnit.SECONDS)
        httpClient.addInterceptor(loggingInterceptor())
        httpClient.addInterceptor(authInterceptor)
        return httpClient.build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    @Provides
    @Singleton
    fun providesAuthenticationInterceptor(): AuthenticationInterceptor = AuthenticationInterceptor()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = retrofitConfiguration(client)

    private fun retrofitConfiguration(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(AppConstant.baseUrl)
                .addConverterFactory(ImgurResponseConverterFactory(Gson()))
                .client(client)
                .build()
    }

    @Provides
    @Singleton
    fun provideContentResolver(context: Context): ContentResolver = context.contentResolver


//    @Provides
//    @Singleton
//    internal fun providesApi(retrofit: Retrofit): LoginApi = retrofit.create(LoginApi::class.java)
//
//    @Provides
//    @Singleton
//    internal fun providesRepository(api: LoginApi): LoginRepository = LoginRemoteRepository(api)


    @Provides
    @Singleton
    fun provideMainActivityRepository(api: MainActivityApi): MainActivityRepository = MainActivityRemoteRepository(api)

    @Provides
    @Singleton
    internal fun providesApi(retrofit: Retrofit): MainActivityApi = retrofit.create(MainActivityApi::class.java)
}