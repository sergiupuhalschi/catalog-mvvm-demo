package com.catganisation.catalog.data.repositories.base

import com.catganisation.catalog.data.models.User
import com.catganisation.catalog.data.models.requests.LoginRequest
import io.reactivex.rxjava3.core.Single

interface UserRepository {

    fun login(request: LoginRequest): Single<User>

    fun logout(): Single<Boolean>

    fun isLoggedIn(): Single<Boolean>
}