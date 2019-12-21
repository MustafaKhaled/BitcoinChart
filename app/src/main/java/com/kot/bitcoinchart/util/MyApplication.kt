package com.kot.bitcoinchart.util

import android.app.Application
import com.kot.bitcoinchart.di.component.AppComponent
import com.kot.bitcoinchart.di.component.DaggerAppComponent
import com.kot.bitcoinchart.di.module.ContextModule
import com.kot.bitcoinchart.di.module.OkHttpClientModule
import com.kot.bitcoinchart.di.module.RetrofitModule

class MyApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().contextModule(ContextModule(applicationContext))
            .okHttpClientModule(
                OkHttpClientModule()
            ).retrofitModule(RetrofitModule()).build()
    }


}