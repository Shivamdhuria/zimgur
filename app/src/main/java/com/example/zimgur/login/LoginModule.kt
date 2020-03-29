package com.example.zimgur.login

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class LoginModule {

    @Provides
    internal fun providesApi(retrofit: Retrofit): LoginApi = retrofit.create(LoginApi::class.java)

    @Provides
    internal fun providesRepository(api: LoginApi): LoginRepository = LoginRemoteRepository(api)
}