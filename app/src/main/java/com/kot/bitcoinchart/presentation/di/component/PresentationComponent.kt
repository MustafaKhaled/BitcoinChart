package com.kot.bitcoinchart.presentation.di.component

import com.kot.bitcoinchart.data.di.component.DataComponent
import com.kot.bitcoinchart.presentation.di.scope.PresentationScope
import com.kot.bitcoinchart.presentation.viewmodel.factory.ViewModelFactory
import com.kot.bitcoinchart.presentation.viewmodel.factory.di.ViewModelFactoryModule
import dagger.Component
@PresentationScope
@Component(modules = [ViewModelFactoryModule::class], dependencies = [DataComponent::class])
interface PresentationComponent {
    fun exposeViewModel(): ViewModelFactory
}