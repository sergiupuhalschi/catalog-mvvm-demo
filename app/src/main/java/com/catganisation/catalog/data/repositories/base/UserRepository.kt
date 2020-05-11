package com.catganisation.catalog.data.repositories.base

import com.catganisation.catalog.data.models.User
import io.reactivex.rxjava3.core.Single

interface UserRepository {

    fun login(username: String, password: String): Single<User>

    fun logout(): Single<Boolean>

    fun isLoggedIn(): Single<Boolean>
}