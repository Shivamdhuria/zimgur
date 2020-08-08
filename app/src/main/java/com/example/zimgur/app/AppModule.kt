package com.example.zimgur.app

import android.content.Context
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
internal class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: ZimgurApplication): Context = application.applicationContext

//    @Singleton
//    @Provides
//    fun providePreferences(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)


}