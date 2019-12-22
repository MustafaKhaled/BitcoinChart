package com.kot.bitcoinchart.ui.di

import com.kot.bitcoinchart.presentation.di.component.PresentationComponent
import com.kot.bitcoinchart.presentation.di.scope.PresentationScope
import com.kot.bitcoinchart.ui.BitcoinChart
import dagger.Component
@BitcoinChartScope
@Component(dependencies = [PresentationComponent::class])
interface BitcoinChartComponent {
    fun inject(bitcoinChart: BitcoinChart)
}