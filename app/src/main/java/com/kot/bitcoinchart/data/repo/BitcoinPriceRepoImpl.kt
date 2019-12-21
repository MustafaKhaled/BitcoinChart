package com.kot.bitcoinchart.data.repo

import com.kot.bitcoinchart.data.entities.MarketPriceResponse
import com.kot.bitcoinchart.domain.repo.BitcoinChartRepository
import io.reactivex.Single

class BitcoinPriceRepoImpl: BitcoinChartRepository {
    override fun getBitcoinMarketPrice(): Single<MarketPriceResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}