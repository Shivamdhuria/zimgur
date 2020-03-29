package com.example.zimgur.login

import androidx.lifecycle.ViewModel
import com.example.zimgur.app.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [LoginModule::class])
internal abstract class LoginBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun loginActivity(): LoginActivity

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel
}