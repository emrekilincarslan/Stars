package com.emrexample.mobile.dagger

import com.emrexample.mobile.activity.MainActivity
import com.emrexample.mobile.dagger.main.MainActivityModule
import com.emrexample.mobile.dagger.main.MainFragmentsBuildersModule
import com.emrexample.mobile.dagger.main.MainViewModelsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainViewModelsModule::class, MainFragmentsBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity




}