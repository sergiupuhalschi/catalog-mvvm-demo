package com.catganisation.catalog.domain.usecases

import com.catganisation.catalog.utils.providers.schedulers.SchedulerProvider
import io.reactivex.rxjava3.core.Single

interface SingleUseCase<T, R> {

    fun perform(params: T = Unit as T): Single<R>
}

fun <T> Single<T>.applySchedulers(schedulerProvider: SchedulerProvider): Single<T> {
    return compose { resultObservable ->
        resultObservable
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.mainThread())
    }
}