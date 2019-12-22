package com.kot.bitcoinchart.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kot.bitcoinchart.data.entities.MarketPriceResponse
import com.kot.bitcoinchart.domain.usecase.BitcoinMarketPriceUsecase
import com.kot.bitcoinchart.util.Results
import javax.inject.Inject

class BitcoinMarketPriceViewModel @Inject constructor(val bitcoinMarketPriceUsecase: BitcoinMarketPriceUsecase) :
    ViewModel() {
    private val getAllPriceLiveData = MutableLiveData<Results<MarketPriceResponse>>()

    fun loadBitcoinMarketPriceChart() {
        getAllPriceLiveData.postValue(Results.Loading())
        bitcoinMarketPriceUsecase.execute(onSuccess = {
            getAllPriceLiveData.postValue(Results.Success(it))
        },
            onFailure = {
                getAllPriceLiveData.postValue(Results.Error(it))
            }
        )
    }

    fun getBitcoinMarketPriceChart(): LiveData<Results<MarketPriceResponse>> {
        return getAllPriceLiveData
    }
}