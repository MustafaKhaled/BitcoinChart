package com.kot.bitcoinchart.di.component

import android.content.Context
import com.kot.bitcoinchart.di.module.ContextModule
import com.kot.bitcoinchart.di.module.OkHttpClientModule
import com.kot.bitcoinchart.di.module.RetrofitModule
import com.kot.bitcoinchart.di.scope.ApplicationScope
import dagger.Component
import retrofit2.Retrofit

@ApplicationScope
@Component(modules = [OkHttpClientModule::class,RetrofitModule::class,ContextModule::class])
public interface AppComponent {

    fun exposeRetrofit(): Retrofit
    fun exposeContext():Context
}