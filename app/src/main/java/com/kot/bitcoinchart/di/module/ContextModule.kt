package com.kot.bitcoinchart.di.module

import android.content.Context
import com.kot.bitcoinchart.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
@Module
class ContextModule constructor(context: Context) {
    lateinit var context: Context

    fun ContextModule(context: Context) {
        this.context = context
    }

    @ApplicationScope
    @Provides
    fun providesContext(): Context {
        return context
    }
}