package com.kot.bitcoinchart.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kot.bitcoinchart.data.entities.MarketPriceResponse
import com.kot.bitcoinchart.domain.usecase.BitcoinMarketPriceUsecase
import javax.inject.Inject

class BitcoinMarketPriceViewModel @Inject constructor(val bitcoinMarketPriceUsecase: BitcoinMarketPriceUsecase) :
    ViewModel() {
    private val getAllPriceLiveData = MutableLiveData<MarketPriceResponse>()

    fun loadBitcoinMarketPriceChart() {
        bitcoinMarketPriceUsecase.execute(onSuccess = {
            getAllPriceLiveData.postValue(it)
        },
            onFailure = {
                it.printStackTrace()
            }

        )
    }

    fun getBitcoinMarketPriceChart(): LiveData<MarketPriceResponse> {
        return getAllPriceLiveData
    }
}