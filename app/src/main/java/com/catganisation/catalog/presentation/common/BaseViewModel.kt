package com.catganisation.catalog.presentation.common

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

open class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    protected fun <T> Observable<T>.safeSubscribe(
        onErrorAction: ((Throwable) -> Unit)? = null,
        onSuccessAction: ((T) -> Unit)? = null
    ) {
        disposables.add(subscribe({ onSuccessAction?.invoke(it) }, {
            onErrorAction?.invoke(it)
            Timber.e(it)
        }))
    }

    protected fun <T> Single<T>.safeSubscribe(
        onSuccessAction: ((T) -> Unit)? = null,
        onErrorAction: ((Throwable) -> Unit)? = null
    ) {
        disposables.add(subscribe({ onSuccessAction?.invoke(it) }, {
            onErrorAction?.invoke(it)
            Timber.e(it)
        }))
    }
}