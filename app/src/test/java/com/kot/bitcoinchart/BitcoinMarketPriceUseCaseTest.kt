package com.kot.bitcoinchart

import androidx.lifecycle.MutableLiveData
import com.kot.bitcoinchart.data.entities.MarketPriceResponse
import com.kot.bitcoinchart.data.entities.Values
import com.kot.bitcoinchart.data.remote.ApiServices
import com.kot.bitcoinchart.domain.repo.BitcoinChartRepository
import com.kot.bitcoinchart.domain.usecase.BitcoinMarketPriceUsecase
import com.kot.bitcoinchart.util.Results
import io.mockk.verify
import io.mockk.verifyAll
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.Answer

class BitcoinMarketPriceUseCaseTest {
    @Mock
    lateinit var apiService: ApiServices
    @Mock
    lateinit var repo: BitcoinChartRepository
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun should_return_data_from_repo(){
        Mockito.`when`(apiService.getMarketPrice()).thenReturn(Single.fromObservable(Observable.just(MarketPriceResponse("","","","","",
            ArrayList<Values>()))))
    }
}