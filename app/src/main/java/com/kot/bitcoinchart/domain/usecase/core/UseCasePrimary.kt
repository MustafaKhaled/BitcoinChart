package com.movieapp.kotlin.domain.usecases.core

import android.content.IntentSender
import android.hardware.camera2.CaptureFailure
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import io.reactivex.schedulers.Schedulers

abstract class UseCasePrimary<T> : UseCaseDisposables() {
    internal abstract fun buildUseCasePrimary() : Single<T>

    fun execute(
        onSuccess: ((t: T) -> Unit),
        onFailure: ((t: Throwable) -> Unit),
        onFinished: () -> Unit = {}
    ){
        disposeLast()
        lastDisposable = buildUseCasePrimary().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess, onFailure)


        lastDisposable?.let {
            compositeDisposable.add(it)
        }
    }
}