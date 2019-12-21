package com.kot.bitcoinchart.presentation.viewmodel.factory.di

import androidx.lifecycle.ViewModelProvider
import com.kot.bitcoinchart.presentation.viewmodel.di.ViewModelModule
import com.kot.bitcoinchart.presentation.viewmodel.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelModule::class])
interface ViewModelFactoryModule {
    @Binds
    fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
