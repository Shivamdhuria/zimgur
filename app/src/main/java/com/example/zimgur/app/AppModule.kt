package com.example.zimgur.app

import android.content.ContentResolver
import android.content.Context
import android.preference.PreferenceManager
import com.example.zimgur.BuildConfig
import com.example.zimgur.utils.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal class AppModule {

    @Singleton
    @Provides
    fun providePreferences(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthenticationInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(1, TimeUnit.MINUTES)
        httpClient.connectTimeout(1, TimeUnit.MINUTES)
        httpClient.writeTimeout(1, TimeUnit.MINUTES)
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
    fun provideRetrofit(client: OkHttpClient): Retrofit = retrofitConfiguration(client)

    private fun retrofitConfiguration(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstant.baseUrl)
            .client(client)
//            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
    }



    @Provides
    @Singleton
    fun provideContentResolver(context: Context): ContentResolver = context.contentResolver


}