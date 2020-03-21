package com.emrexample.mobile.dagger.main

import androidx.lifecycle.ViewModel
import com.emrexample.mobile.viewmodel.SelectionOfStarViewModel
import com.emrexample.mobile.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SelectionOfStarViewModel::class)
    internal abstract fun bindSelectionOfStarViewModel(viewModel: SelectionOfStarViewModel): ViewModel



}