package com.example.zimgur.app

import com.example.zimgur.login.LoginBuilderModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class,
        AppBuilderModule::class,
        LoginBuilderModule::class
    ]
)
internal interface AppComponent : AndroidInjector<ZimgurApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<ZimgurApplication>
}