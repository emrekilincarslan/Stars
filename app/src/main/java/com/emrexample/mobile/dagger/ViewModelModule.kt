package com.emrexample.mobile.dagger

import androidx.lifecycle.ViewModelProvider
import com.emrexample.mobile.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}