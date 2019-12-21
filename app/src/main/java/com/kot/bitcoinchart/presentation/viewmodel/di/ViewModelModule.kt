package com.kot.bitcoinchart.presentation.viewmodel.di

import androidx.lifecycle.ViewModel
import com.kot.bitcoinchart.presentation.di.scope.PresentationScope
import com.kot.bitcoinchart.presentation.viewmodel.BitcoinMarketPriceViewModel
import com.kot.bitcoinchart.presentation.viewmodel.factory.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
@Module
interface ViewModelModule {
@Binds
@IntoMap
@ViewModelKey(BitcoinMarketPriceViewModel::class)
fun bindBitcoinMarketPriceViewModel(bitcoinMarketPriceViewModel: BitcoinMarketPriceViewModel): ViewModel

}