package com.kot.bitcoinchart.util

sealed class Results<out T: Any>(data: T? = null, message: String? = null) {
    class Success<out T : Any>(val data: T?) : Results<T>()
    class Loading : Results<Nothing>()
    class Error(throwable: Throwable) : Results<Nothing>(message = throwable.message)
}