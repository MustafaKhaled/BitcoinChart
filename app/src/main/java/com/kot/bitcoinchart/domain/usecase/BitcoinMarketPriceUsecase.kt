package com.kot.bitcoinchart.domain.usecase

import com.kot.bitcoinchart.data.entities.MarketPriceResponse
import com.kot.bitcoinchart.domain.repo.BitcoinChartRepository
import com.movieapp.kotlin.domain.usecases.core.UseCasePrimary
import io.reactivex.Single
import javax.inject.Inject

class BitcoinMarketPriceUsecase @Inject constructor(private val bitcoinChartRepository: BitcoinChartRepository): UseCasePrimary<MarketPriceResponse>(){
    override fun buildUseCasePrimary(): Single<MarketPriceResponse> {
        return bitcoinChartRepository.getBitcoinMarketPrice()
    }

}