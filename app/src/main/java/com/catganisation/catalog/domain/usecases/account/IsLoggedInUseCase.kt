package com.catganisation.catalog.domain.usecases.account

import com.catganisation.catalog.data.repositories.base.UserRepository
import com.catganisation.catalog.domain.usecases.SingleUseCase
import com.catganisation.catalog.domain.usecases.applySchedulers
import com.catganisation.catalog.utils.providers.schedulers.SchedulerProvider
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class IsLoggedInUseCase @Inject constructor(
    private val repository: UserRepository,
    private val schedulerProvider: SchedulerProvider
) : SingleUseCase<Unit, Boolean> {

    override fun perform(params: Unit): Single<Boolean> {
        return repository.isLoggedIn()
            .onErrorReturn { false }
            .applySchedulers(schedulerProvider)
    }
}