package com.kot.bitcoinchart.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kot.bitcoinchart.BuildConfig.BASE_URL
import com.kot.bitcoinchart.data.remote.ApiServices
import com.kot.bitcoinchart.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [OkHttpClientModule::class])
class RetrofitModule {
    @ApplicationScope
    @Provides
    fun retrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @ApplicationScope
    @Provides
    fun providesGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @ApplicationScope
    @Provides
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun createSettingsAPI(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

}
