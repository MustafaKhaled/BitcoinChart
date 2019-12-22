package com.kot.bitcoinchart.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.Anchor
import com.anychart.enums.MarkerType
import com.anychart.graphics.vector.Stroke
import com.google.android.material.snackbar.Snackbar
import com.kot.bitcoinchart.R
import com.kot.bitcoinchart.data.di.component.DaggerDataComponent
import com.kot.bitcoinchart.data.entities.Values
import com.kot.bitcoinchart.presentation.di.component.DaggerPresentationComponent
import com.kot.bitcoinchart.presentation.viewmodel.BitcoinMarketPriceViewModel
import com.kot.bitcoinchart.presentation.viewmodel.factory.ViewModelFactory
import com.kot.bitcoinchart.ui.di.DaggerBitcoinChartComponent
import com.kot.bitcoinchart.util.MyApplication
import com.kot.bitcoinchart.util.Results
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.math.round


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
            when (it) {
                is Results.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                    chart.visibility = View.GONE
                }
                is Results.Success -> {
                    progress_bar.visibility = View.GONE
                    chart.visibility = View.VISIBLE
                    var seriesData: ArrayList<DataEntry> = ArrayList()
                    for (value: Values in it.data!!.values) {
                        seriesData.add(CustomDataEntry(formatDate(value.x), round(value.y)))
                    }
                    drawChart(seriesData)
                }
                is Results.Error -> {
                    progress_bar.visibility = View.GONE
                    Snackbar.make(
                        this.progress_bar,
                        getString(R.string.failure_error_msg),
                        Snackbar.LENGTH_LONG
                    )
                        .show()
                }
            }
        })
    }

    fun formatDate(value: Long): String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = Date(value * 1000)
        return sdf.format(date)
    }

    private fun drawChart(list: ArrayList<DataEntry>) {
        val cartesian: Cartesian = AnyChart.line()
        cartesian.animation(true)
        cartesian.padding(10.0, 20.0, 5.0, 20.0)
        cartesian.crosshair().enabled(true)
        cartesian.crosshair()
            .yLabel(true)
            .yStroke(null as Stroke?, null, null, null as String?, null as String?)

        cartesian.title("Market Price (USD)")

        cartesian.yAxis(0).title("USD")
        cartesian.xAxis(0).labels().padding(5.0, 5.0, 5.0, 5.0)


        val setValue: Set = Set.instantiate()
        setValue.data(list)
        val series1Mapping: Mapping = setValue.mapAs("{ x: 'x', value: 'value' }")
        val line: com.anychart.core.cartesian.series.Line = cartesian.line(series1Mapping)
        line.hovered().markers().enabled(true)
        line.name(getString(R.string.usd_average_indicator))
        line.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        line.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)
        cartesian.legend().enabled(true)
        cartesian.legend().fontSize(13.0)
        cartesian.legend().padding(0.0, 0.0, 10.0, 0.0)

        chart.setChart(cartesian)

    }

    class CustomDataEntry(x: String?, value: Number?) : ValueDataEntry(x, value)

}