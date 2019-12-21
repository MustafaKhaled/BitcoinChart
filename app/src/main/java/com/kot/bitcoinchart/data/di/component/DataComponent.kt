package com.kot.bitcoinchart.data.di.component

import com.kot.bitcoinchart.data.di.module.RepoModule
import com.kot.bitcoinchart.data.di.scope.DataComponentScope
import com.kot.bitcoinchart.di.component.AppComponent
import com.kot.bitcoinchart.domain.repo.BitcoinChartRepository
import dagger.Component
@DataComponentScope
@Component(modules = [RepoModule::class], dependencies = [AppComponent::class])
interface DataComponent {
fun bitcoinRepo(): BitcoinChartRepository
}