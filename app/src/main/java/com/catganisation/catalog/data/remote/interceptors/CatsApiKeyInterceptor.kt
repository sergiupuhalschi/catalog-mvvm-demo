package com.catganisation.catalog.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY_HEADER = "x-api-key"
private const val API_KEY_VALUE = "DEMO-API-KEY"

class CatsApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(API_KEY_HEADER, API_KEY_VALUE)
            .build()
        return chain.proceed(request)
    }
}