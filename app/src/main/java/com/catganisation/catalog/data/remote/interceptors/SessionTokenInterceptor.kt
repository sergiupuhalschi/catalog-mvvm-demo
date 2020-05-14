package com.catganisation.catalog.data.remote.interceptors

import com.catganisation.catalog.data.local.preferences.Persister
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val AUTHORIZATION_HEADER_KEY = "Authorization"

class SessionTokenInterceptor @Inject constructor(
    private val persister: Persister
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = persister.get(Persister.Key.SessionToken)?.let {
            chain.request()
                .newBuilder()
                .addHeader(AUTHORIZATION_HEADER_KEY, it)
                .build()
        } ?: chain.request()
        return chain.proceed(newRequest)
    }
}