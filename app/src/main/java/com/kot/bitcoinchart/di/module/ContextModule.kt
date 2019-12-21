package com.kot.bitcoinchart.di.module

import android.content.Context
import com.kot.bitcoinchart.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
@Module
class ContextModule constructor(private val context: Context) {

    @ApplicationScope
    @Provides
    fun providesContext(): Context {
        return context
    }
}