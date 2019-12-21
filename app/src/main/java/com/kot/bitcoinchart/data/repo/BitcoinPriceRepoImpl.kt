package com.kot.bitcoinchart.data.repo

import com.kot.bitcoinchart.data.entities.MarketPriceResponse
import com.kot.bitcoinchart.data.remote.ApiServices
import com.kot.bitcoinchart.domain.repo.BitcoinChartRepository
import io.reactivex.Single
import javax.inject.Inject

class BitcoinPriceRepoImpl @Inject constructor(private val apiServices: ApiServices): BitcoinChartRepository {

    override fun getBitcoinMarketPrice(): Single<MarketPriceResponse> {
        return apiServices.getMarketPrice()
    }
}