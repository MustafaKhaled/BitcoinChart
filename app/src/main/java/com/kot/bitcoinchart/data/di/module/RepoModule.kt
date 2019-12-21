package com.kot.bitcoinchart.data.di.module

import com.kot.bitcoinchart.data.repo.BitcoinPriceRepoImpl
import com.kot.bitcoinchart.domain.repo.BitcoinChartRepository
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {
@Binds
 fun bindsBitcoinPriceRepo(bitcoinPriceRepoImpl: BitcoinPriceRepoImpl): BitcoinChartRepository
}