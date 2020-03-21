package com.emrexample.mobile.dagger.main

import com.emrexample.mobile.fragment.SelectionOfStarFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentsBuildersModule {
    @ContributesAndroidInjector
    abstract fun SelectionOfStarFragment(): SelectionOfStarFragment


}