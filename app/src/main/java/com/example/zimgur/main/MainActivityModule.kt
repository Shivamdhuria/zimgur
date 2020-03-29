package com.example.zimgur.main

import com.example.zimgur.login.LoginApi
import com.example.zimgur.login.LoginRemoteRepository
import com.example.zimgur.login.LoginRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class MainActivityModule {

    @Provides
    fun provideMainActivityRepository(api: MainActivityApi): MainActivityRepository = MainActivityRemoteRepository(api)

    @Provides
    internal fun providesApi(retrofit: Retrofit): MainActivityApi = retrofit.create(MainActivityApi::class.java)
}