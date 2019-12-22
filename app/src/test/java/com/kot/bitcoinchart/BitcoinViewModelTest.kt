package com.kot.bitcoinchart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kot.bitcoinchart.data.entities.MarketPriceResponse
import com.kot.bitcoinchart.domain.usecase.BitcoinMarketPriceUsecase
import com.kot.bitcoinchart.presentation.viewmodel.BitcoinMarketPriceViewModel
import com.kot.bitcoinchart.util.Results
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class BitcoinViewModelTest {
    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    lateinit var bitcoinMarketPriceUsecase: BitcoinMarketPriceUsecase
    private lateinit var viewModel: BitcoinMarketPriceViewModel
    private val observer: Observer<Results<MarketPriceResponse>> = mock()

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = BitcoinMarketPriceViewModel(bitcoinMarketPriceUsecase)
        viewModel.getBitcoinMarketPriceChart().observeForever(observer)
    }

    @Test
    fun should_return_results(){
        viewModel.loadBitcoinMarketPriceChart()
        val  result = viewModel.getBitcoinMarketPriceChart().value
        Assert.assertNotNull(result)
    }

}