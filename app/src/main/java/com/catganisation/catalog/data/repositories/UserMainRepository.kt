package com.catganisation.catalog.data.repositories

import com.catganisation.catalog.data.local.preferences.Persister
import com.catganisation.catalog.data.models.User
import com.catganisation.catalog.data.models.requests.LoginRequest
import com.catganisation.catalog.data.repositories.base.UserRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserMainRepository @Inject constructor(
    private val persister: Persister
) : UserRepository {

    override fun login(request: LoginRequest): Single<User> {
        return Single.just(buildDummyUser(request.username))
            .delay(1, TimeUnit.SECONDS)
            .map { user ->
                persister.set(Persister.Key.SessionToken, user.sessionKey)
                persister.set(Persister.Key.User, user)
                user
            }
    }

    override fun logout(): Single<Boolean> {
        return Single.just(true)
            .doFinally { persister.clear() }
    }

    override fun isLoggedIn(): Single<Boolean> =
        Single.fromCallable { persister.has(Persister.Key.SessionToken) }

    private fun buildDummyUser(username: String) = User(
        id = UUID.randomUUID().toString(),
        username = username,
        fullName = "John Doe",
        sessionKey = UUID.randomUUID().toString()
    )
}