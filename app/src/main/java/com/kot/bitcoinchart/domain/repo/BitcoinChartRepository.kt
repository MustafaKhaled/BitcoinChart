package com.kot.bitcoinchart.domain.repo

import com.kot.bitcoinchart.data.entities.MarketPriceResponse
import io.reactivex.Single

interface BitcoinChartRepository {
    fun getBitcoinMarketPrice(): Single<MarketPriceResponse>
}