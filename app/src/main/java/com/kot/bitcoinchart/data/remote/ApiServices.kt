package com.kot.bitcoinchart.data.remote

import com.kot.bitcoinchart.data.entities.MarketPriceResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("market-price/?format=json")
    fun getMarketPrice(): Single<MarketPriceResponse>
}