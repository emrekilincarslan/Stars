package com.emrexample.mobile.dagger.main

import androidx.fragment.app.FragmentActivity
import com.emrexample.mobile.activity.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideMainActivity(activity: MainActivity): FragmentActivity



}