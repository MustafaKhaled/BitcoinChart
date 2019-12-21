package com.movieapp.kotlin.domain.usecases.core

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCaseDisposables {
    protected var lastDisposable: Disposable? = null
    protected val compositeDisposable = CompositeDisposable()

    fun disposeLast() {
        lastDisposable?.let {
            if (!it.isDisposed){
                it.dispose()
            }
        }
    }

    fun disposeAll(){
        compositeDisposable.dispose()
    }
}