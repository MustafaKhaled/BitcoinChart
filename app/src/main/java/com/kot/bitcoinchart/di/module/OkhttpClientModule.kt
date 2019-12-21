package com.kot.bitcoinchart.di.module

import android.content.Context
import com.kot.bitcoinchart.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class OkHttpClientModule {
    @ApplicationScope
    @Provides
    fun getOkHttpClient(
        cache: Cache?,
        httpLoggingInterceptor: HttpLoggingInterceptor?
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .cache(cache)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor!!)
            .build()
    }

    @ApplicationScope
    @Provides
    fun providesCache(cacheFile: File?): Cache {
        return Cache(cacheFile!!, 10 * 1000 * 1000)
    }

    @ApplicationScope
    @Provides
    fun providesFile(context: Context): File {
        val file = File(context.cacheDir, "HttpCache")
        file.mkdir()
        return file
    }
    @ApplicationScope
    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}