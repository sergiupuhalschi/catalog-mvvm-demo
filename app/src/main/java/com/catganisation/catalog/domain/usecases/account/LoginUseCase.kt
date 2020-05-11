package com.catganisation.catalog.domain.usecases.account

import com.catganisation.catalog.data.models.User
import com.catganisation.catalog.data.models.requests.LoginRequest
import com.catganisation.catalog.data.repositories.base.UserRepository
import com.catganisation.catalog.domain.models.holders.LoginData
import com.catganisation.catalog.domain.usecases.SingleUseCase
import com.catganisation.catalog.domain.usecases.applySchedulers
import com.catganisation.catalog.utils.providers.schedulers.SchedulerProvider
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository,
    private val schedulerProvider: SchedulerProvider
) : SingleUseCase<LoginData, User> {

    override fun perform(params: LoginData): Single<User> {
        return repository.login(params.toRequest())
            .applySchedulers(schedulerProvider)
    }

    private fun LoginData.toRequest() = LoginRequest(
        username = username,
        password = password
    )
}