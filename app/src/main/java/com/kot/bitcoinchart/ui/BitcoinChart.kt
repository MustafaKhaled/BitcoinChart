package com.kot.bitcoinchart.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.kot.bitcoinchart.R
import com.kot.bitcoinchart.data.di.component.DaggerDataComponent
import com.kot.bitcoinchart.presentation.di.component.DaggerPresentationComponent
import com.kot.bitcoinchart.presentation.viewmodel.BitcoinMarketPriceViewModel
import com.kot.bitcoinchart.presentation.viewmodel.factory.ViewModelFactory
import com.kot.bitcoinchart.ui.di.DaggerBitcoinChartComponent
import com.kot.bitcoinchart.util.MyApplication
import javax.inject.Inject

class BitcoinChart : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var bitcoinMarketPriceViewModel: BitcoinMarketPriceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerBitcoinChartComponent.builder()
            .presentationComponent(
                DaggerPresentationComponent.builder()
                    .dataComponent(DaggerDataComponent.builder().appComponent(MyApplication.appComponent).build())
                    .build()
            ).build().inject(this)
        bitcoinMarketPriceViewModel =
            ViewModelProviders.of(this, viewModelFactory)[BitcoinMarketPriceViewModel::class.java]
        bitcoinMarketPriceViewModel.loadBitcoinMarketPriceChart()

        observeMarketPrice()
    }

    private fun observeMarketPrice() {
        bitcoinMarketPriceViewModel.getBitcoinMarketPriceChart().observe(this, Observer {
        })
    }


}